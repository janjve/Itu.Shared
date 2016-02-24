// Advanced Programming 2015
// Andrzej Wąsowski, IT University of Copenhagen
//
// A script meant to be loaded into REPL (scala -i Main.scala)

import fpinscala.laziness._
import fpinscala.laziness.Stream._

// this is how we do simple interactive testing

def to (n :Int) :Stream[Int] = {
    if(n == 1) Cons(() => n, () => Empty)
    else Cons(() => n, () => to(n-1))
}

def from (n :Int) :Stream[Int] = {
    Cons(() => n, () => from(n+1))
}

val l1 :Stream[Int] = Empty
val l2 :Stream[Int] = empty

val l3 :Stream[Int]= cons(1, cons(2, cons (3, empty)))

println (l1.headOption)
println (l2.headOption)
println (l3.headOption)

// Take test
val stream1 = to(10)
val stream1take5 = stream1.take(5)
println(stream1take5.toList);


