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
  	//val rng = Simple(42)
  	val (n1, rng2) = rng.nextInt
  	if(n1 == Int.MinValue) nonNegativeInt(rng2)
  	else if(n1 < 0) (-n1, rng2)
  	else (n1, rng2)
  }

  // Exercise 2 (CB 6.2)

  def double(rng: RNG): (Double, RNG) = { 
  	val (n1, rng2) = rng.nextInt
  	val maxInt = Int.MaxValue
  	val doubleMaxInt = maxInt.toDouble
  	(n1/doubleMaxInt, rng2)
  }

  // Exercise 3 (CB 6.3)

  def intDouble(rng: RNG): ((Int, Double), RNG) = { 
  	val (n1, rng2) = nonNegativeInt(rng)
  	val (n2, rng3) = double(rng2)
  	((n1, n2), rng3)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = { 
  	val (n1, rng2) = double(rng)
  	val (n2, rng3) = nonNegativeInt(rng2)
  	((n1,n2), rng3)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
  	val (n1, rng2) = double(rng)
  	val (n2, rng3) = double(rng2)
  	val (n3, rng4) = double(rng3)
  	((n1, n2, n3), rng4)
  }

  /*def boolean(rng: RNG): (Boolean, RNG) =
  rng.nextInt match { case (i,rng2) => (i%2==0,rng2) }*/

  // Exercise 4 (CB 6.4)

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

  	def inner(count: Int)(rng: RNG)(list: List[Int]): (List[Int], RNG) = {
  		val (n1, rng2) = rng.nextInt
  		if(count > 0) {
  			inner(count-1)(rng2)(n1 :: list)
  		}
  		else {
  			(list, rng)
  		}
  	}
  	inner(count)(rng)(Nil)
  
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

  // val _double: Rand[Double] =

  // Exercise 6 (CB 6.6)

  // def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =

  // this is given in the book

  // def both[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A,B)] =
  //  map2(ra, rb)((_, _))

  // val randIntDouble: Rand[(Int, Double)] = both(int, double)

  // val randDoubleInt: Rand[(Double, Int)] = both(double, int)

  // Exercise 7 (6.7)

  // def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =

  // def _ints(count: Int): Rand[List[Int]] = ...

  // Exercise 8 (6.8)

  // def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] = ...

  // def nonNegativeLessThan(n: Int): Rand[Int] = { ...

  // Exercise 9 (6.9)

  // def _map[A,B](s: Rand[A])(f: A => B): Rand[B] =

  // def _map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
}

import State._

case class State[S, +A](run: S => (A, S)) {

  // Exercise 10 (6.10)

  // def map[B](f: A => B): State[S, B] = ...

  // def map2[B,C](sb: State[S, B])(f: (A, B) => C): State[S, C] = ...

  // def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => { ...

}

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  // Exercise 10 (6.10) continued

  // def sequence[S,A](sas: List[State[S, A]]): State[S, List[A]] = ...
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
