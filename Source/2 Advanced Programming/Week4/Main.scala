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

def fib(): Stream[Int] =  {
	def inner(n1: Int, n2: Int): Stream[Int] = {
		 cons(n1+n2 ,inner(n1+n2))
	}
	inner(0,1)
}

def test0(): Unit = {
    val l1 :Stream[Int] = Empty
    val l2 :Stream[Int] = empty

    val l3 :Stream[Int]= cons(1, cons(2, cons (3, empty)))

    println (l1.headOption)
    println (l2.headOption)
    println (l3.headOption)
}

def test1 (): Unit = {
    val stream1 = to(10)    // Finite
    val stream2 = from(10)  // Infinite
    
    println(stream2.take(5).toList)
    println(stream1.drop(5).take(5).toList)
    println(stream2.take(1000000000).drop(41).take(10).toList.toList)
}

def test2 (): Unit = {
    val neutrals = from(1)  // Infinite
    
    println(neutrals.takeWhile(_<1000000000).drop(100).take(50).toList)
}

def test3 (): Unit = {
    val neutrals = from(1)                  // Infinite
    val to10 = to(10)                       // Finite
    
    println(neutrals.forAll(_ < 10))        // False
    println(to10.forAll(_ <= 10))           // true
    //println(neutrals.forAll(_ >= 0))      // Error
}

def test4 (): Unit = {
    val neutrals = from(10)  // Infinite
    
    println(neutrals.takeWhileFoldRight(_<1000000000).drop(100).take(50).toList)
}

def test5(): Unit = {
    val neutrals = from(1)                  // Infinite
    println(neutrals.headOptionFoldRight)
    println(neutrals.drop(5).headOptionFoldRight)
}

def test6() = {
    val neutrals = from(1)                  // Infinite

    println(neutrals.map (_*2).drop (30).take (50).toList)
    println(neutrals.drop(42).filter (_%2 == 0).take (30).toList)
    println(neutrals.take(10).append(neutrals).take(20).toList)
    println(neutrals.flatMap (x => from(1)).take (100).toList)
}

def test7() = {
    val neutrals = from(1)                  // Infinite

    println(neutrals.find(x => x == 1000000))
}

def test8() =  {
    val neutrals = from(1)
}