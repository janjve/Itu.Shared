# Alternate solutions to lecture exercises
 1) The exercise doesn't state that the numbers should be consecutively even.
 
    a)  
    def even_from(n:Int) Stream[Int] = 
        if(n%2==0) Cons(()=>n, ()=>even(n+2))
        else
            Cons(()=>n+1, ()=>even(n+3))

    b)     
    def even_from(n:Int) Stream[Int] = Cons(m, even_from(n*2)) 
    
 2)

    def flatten[Stream[A]] : Stream[A] = this.foldRight[Stream[A]] (Empty) ( (a,b) => a.append(b))

# Topic: State
 State is a monad, similar to Option.
 

def rollDie : Int = {
    val rng = new scala.util.random
    rng.nextInt()
 }
 
 ##Question:  
 We observe that rolldie == 5.
  
 What is the result of (rollDie + rollDie)?
 
 ###Answer:
 
 The function rollDie is not pure / referentially transparent. So we don't know what the 
 result of (rollDie + rollDie) would be.

_____
A referentially Transparent RNG

// Function returning a tuple containing a value and a state.

    trait RNG { 
        def nextInt : (Int, RNG)
    }

State => (Output, State) 

case class State[S, +A] (run: S => (S, A))

##Automaton:
                "a"    "b"    "c"
  (Start) -> (1) -> (2) -> (3) -> (1)
  Regular Expression matching the language defined by the automaton: (abc)*
    
###In Java/C the automaton would be implemented as follows:
Non-Referentially transparent example:

    var state = 1
    while(true){
        if(state==1) {
            print "a";
            state = 2;
        }
        else if (state == 2) {
            printf "b";
            state = 3;
        }
        else if (state == 3) {
            print "c";
            state = 1;
        }
    }

###In a functional fashion the automaton would be implemented as follows:
Referentially transparent example:
    
    def transition (state : Int) : (String, Int) = {
        state match {
            case 1 => ("a", 2)
            case 2 => ("b", 3)
            case 3 => ("c", 1)
        }
    }
    
##Question:
Rewrite pseudo code in a functional fashion:

    x = 0
    while(true) {
        println(x)
        x += 1
    }

###Answer:

    State[String, Int]
    def myfunc (state : Int) : (String, Int) = (state.toString, state + 1)
   
Regular Expression matching the language defined by the automaton: (012345.....n)

##Question: 
Given 
State[Bool, Bool] (b => (b,!b)) 

What is the stream unfolded starting with initial state false?

###Answer: false, true, false, true,....

________
type Rand[A] = State[RNG, A]

(RNG)- a -> (RNG') - ->

val i : Rand[Int] = State(_ nextInt)


Assume: SimpleRng : Rand[Int]
Correctly: val i = SimpleRNG(42)

Get a random number
...


def map[B] ( s: State[S, A] ) ( f: A => B ) : State[S, B] 

Stream of random even numbers:

    Rand[Int].map(_*2) 

Stream of random boolean values:

    Rand[Int].map(_%2==0)

def flatMap[B] ( s: Rand[A] ) ( f: A => Rand[B] ) : Rand[B]

def map2[A,B,C] ( sa: Rand[A] ) ( sb: Rand[B] ) ( f: (A,B) => C ) : Rand[C]

...

def flatMap[A, B] ( r: Rand[A] ) ( f: A => Rand[B] ) : Rand[B]

val int : Rand[Int]) = ....

// Function producing a list of random integers, presumably of the input size.
val ints : Int => Rand[list[Int]]

val ns : Rand[list[Int]] = int.flatMap (n => ints(n))

return a list, with the size of n, of random even integers: 
val ns : Rand[list[Int]] = int.flatMap (n => ints(n).map( ns => ns.map(_*2)))

int.flatmap gets a random integer, and binds it to n.
using n as parameter to get a list of size n,.....


using for-comprehensions:
```scala
    val ns : Rand[list[Int]] = for {
        n <- int
        ns <- ints(n)
    } yield ns.map(_*2)
```










        