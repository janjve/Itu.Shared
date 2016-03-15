
object test extends App{
    val s1 = global.State[Int,Int](s=>(s,s+1))
    val s2 = global.State[Int,Int](s=>(s,s*2))
    val s3 = global.State[Int,Int](s=>(s,s*5))
    println(s1.run(4))
    println(s2.run(3))
    val (a,b) = s1.run(1)
    println(s2.run(b))
    
    val ls = List(s1, s2, s3)
    val results = global.State.sequence(ls)
    println(results.run(1))
    println(results.run(10))
}
object global { // Fix because REPL is broken.... (like everything else related to JAVA)

trait RNG {
  def nextInt: (Int, RNG)
}


object RNG {
  // NB - this was called SimpleRNG in the book text

  case class Simple(seed: Long) extends RNG {
    def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.
    }
  }

  // Exercise 1 (CB 6.1)

  def nonNegativeInt(rng: RNG): (Int, RNG) = { 
    val (v, r) = rng.nextInt
    if(v >= 0)
        (v, r)
    else if(v == Int.MinValue)
        nonNegativeInt(r)
    else
        (-v, r)   
    } 

  // Exercise 2 (CB 6.2)

  def double(rng: RNG): (Double, RNG) = { 
    val (v,r) = nonNegativeInt(rng)
    if(v == Int.MaxValue)
        double(r)
    else
        (v.toDouble/Int.MaxValue, r)
    }  

  // Exercise 3 (CB 6.3)

  def intDouble(rng: RNG): ((Int, Double), RNG) = { 
    val (i, r1) = rng.nextInt
    val (d, r2) = double(r1) 
    ((i, d), r2)
    }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (d, r1) = double(rng) 
    val (i, r2) = r1.nextInt
    ((d, i), r2)
    }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = { 
    val (d1, r1) = double(rng) 
    val (d2, r2) = double(r1)
    val (d3, r3) = double(r2)  
    ((d1, d2, d3), r3)
    }
    
  // def boolean(rng: RNG): (Boolean, RNG) =
  //  rng.nextInt match { case (i,rng2) => (i%2==0,rng2) }

  // Exercise 4 (CB 6.4)

  def ints(count: Int) (rng: RNG): (List[Int], RNG) = {
  
    def ints2 (count:Int) (rng:RNG) (l:List[Int]) : (List[Int], RNG) = {
    if(count > 0) {
        val (i, r) = rng.nextInt
        ints2 (count-1) (r) (i::l)        
    }
    else
        (l, rng)
    }
    ints2(count) (rng) (Nil)
  }

  // There is something terribly repetitive about passing the RNG along
  // every time. What could we do to eliminate some of this duplication
  // of effort?

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A,B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  // def nonNegativeEven: Rand[Int] = map(nonNegativeInt)(i => i - i % 2)

  // Exercise 5 (CB 6.5)

  //val _double: Rand[Double] = map(nonNegativeInt) ( i=> (i.toDouble/Int.MaxValue) * ((Int.MaxValue-1).toDouble/Int.MaxValue) )
  val _double: Rand[Double] = map(nonNegativeInt) ( i => i / (Int.MaxValue.toDouble + 1))

  // Vigtig!!!!!! 
  type ABCD[A] = A => (A)

  def test (i:Int):ABCD[Int] = 
    abc => (abc+i)

  // Exercise 6 (CB 6.6)
  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) =>C): Rand[C] = 
    rng => {
        val (a, rnga) = ra(rng)
        val (b, rngb) = rb(rnga)
        (f(a,b), rngb)
    }   

  // this is given in the book

  // def both[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] =
  //  map2(ra, rb)((_, _))

  // val randIntDouble: Rand[(Int, Double)] = both(int, double)

  // val randDoubleInt: Rand[(Double, Int)] = both(double, int)

  // Exercise 7 (6.7)

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = {
    
    def sequence_aux(lsr : List[Rand[A]]) (lsa : List[A]) : Rand[List[A]] = lsr match{
        case Nil => rng => (lsa, rng) 
        case h::t => 
                rng => {
                    val (a,rnga) = h(rng)
                    sequence_aux(t) (a::lsa) (rnga)
                }
    }
    rng => {
        sequence_aux(fs) (Nil) (rng)
    }
  } 
  // RNG.sequence(List(RNG.int, RNG.int, RNG.int)) (RNG.Simple(500))

  // def _ints(count: Int): Rand[List[Int]] = ...

  // Exercise 8 (6.8)

  def flatMap[A,B] (f: Rand[A]) (g: A => Rand[B]): Rand[B] = {
        rng => {
            val (a, rng1) = f(rng)
            val ga = g(a) 
            ga(rng1)
       }
  }
 // RNG.flatMap (RNG.int) ((a: Int) => { rng => (a,rng)})  (RNG.Simple(1))
 
 def nonNegativeLessThan(n: Int): Rand[Int] = { 
    def nonNegativeLessThan_aux (x:Int) : Rand[Int] = { 
        rng =>{ 
            (((x.toDouble/(Int.MaxValue.toDouble+1))*n).toInt, rng)
        }
    }

    rng => {
        flatMap (nonNegativeInt) ((x:Int) => nonNegativeLessThan_aux(x)) (rng)  
    }
  }
  // RNG.nonNegativeLessThan(100000) (RNG.Simple(123))
  
  // Exercise 9 (6.9)
  def _map[A,B](s: Rand[A])(f: A => B): Rand[B] = {
    def _map_aux (a:A) : Rand[B] = rng => (f(a), rng)
    
    rng => {
            flatMap (s) (a=> _map_aux(a)) (rng)
    }
  }
  // RNG._map (RNG.int) (a=>a%2) (RNG.Simple(1231231))
 
  def _map2[A,B,C](ra: Rand[A], rb: Rand[B]) (f: (A, B) => C): Rand[C] = {    
    rng => {   
    
       val (a, rnga) = flatMap (ra) (a => rngx => (a, rngx)) (rng)
       val (b, rngb) = flatMap (rb) (b => rngy => (b, rngy)) (rnga)
       (f(a,b), rngb)          
    }
  } 
  // RNG._map2 (RNG.int, RNG.int) ((a,b)=>(a,b)) (RNG.Simple(123))
}

import State._

case class State[S, +A](run: S => (A, S)) {
  // Exercise 10 (6.10)

  def map[B](f: A => B): State[S, B] = {
    State(state => { 
        val (a,s) = this.run(state)
            (f(a), s ) 
        }) 
  } 

  def map2[B,C](sb: State[S, B])(f: (A, B) => C): State[S, C] = {
    State(state => { 
        val (a, s1) = this.run(state)
        val (b, s2) = sb.run(s1)
            (f(a, b), s2) 
        }) 
  }

   def flatMap[B](f: A => State[S, B]): State[S, B] = {
    State(state => { 
                val (a, s) = this.run(state) 
                f(a).run(s)
                })
    }
 
 }

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  // Exercise 10 (6.10) continued

  def sequence[S,A](sas: List[State[S, A]]): State[S, List[A]] = {
        
        def sequence_aux(lsr : List[State[S, A]]) (lsa : List[A]) : State[S, List[A]] = lsr match{
        case Nil => State(state => (lsa, state)) 
        case h::t => 
                State(state => {
                    val (a, sa) = h.run(state)
                    (sequence_aux(t) (a::lsa)).run(sa)
                })
    }

    sequence_aux(sas) (Nil)
  }
  //
  // This is given in the book:

  // def modify[S](f: S => S): State[S, Unit] = for {
  //   s <- get // Gets the current state and assigns it to `s`.
  //   _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
  // } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))


  def random_int :Rand[Int] =  State (_.nextInt)

  // Exercise 11

  // def state2stream[S,A] (s :State[S,A]) (seed :S) :Stream[A] = ...

  // Exercise 12

  // val random_integers = ...

}






sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Candy {

  // Exercise 13 (CB 6.11)

  // def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ...
}

// vim:cc=80:foldmethod=indent:foldenable
}