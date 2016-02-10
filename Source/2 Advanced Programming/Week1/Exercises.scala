// Advanced Programming, Exercises by A. Wąsowski, IT University of Copenhagen
//
// AUTHOR1: Jan Vium Enghoff (jaen@itu.dk) 
// AUTHOR2: Søren Harrison (soeh@itu.dk)
//
// Write names and ITU email addresses of both group members that contributed to
// the solution of the exercise (in alphabetical order by family name).
//
// You should work with the file by following the associated exercise sheet
// (available in PDF from the course website).
//
// The file is meant to be compiled as follows:
//
// scalac Exercises.scala
//
// or
//
// fsc Exercises.scala
//
// To run the compiled file do "scala Exercises"
//
// To load the file int the REPL start the 'scala' interpreter and issue the
// command ':load Exercises.scala'. Now you can interactively experiment with
// your code.
//
// Continue solving exercises in the order presented in the PDF file. Large
// parts of the file are commented in order to make sure that the exercise
// compiles.  Uncomment and complete fragments as you proceed.  The file shall
// always compile and run after you are done with each exercise (if you do them
// in order).  Please compile and test frequently.

// The extension of App allows writing statements at class top level (the so
// called default constructor). For App objects they will be executed as if they
// were placed in the main method in Java.

object Exercises extends App {

  // Exercise 3

  def power(x: Double, n: Int) : Double = {
     if(n == 0) 1  
     else if(n % 2 == 0 && n > 0) { power(x, n/2) * power(x, n/2) }
     else if(n % 2 != 0 && n > 0) { x * power(x, n-1) }
     else { 1 / power(x, -1 * n) }
  }
  // A few tests, uncomment when your implementation is ready.

  // assert (power (2.0, 2) == 4.0)
  // assert (power (1.0, 42) == 1.0)
  //
  // The above assertions should pass when you call "scala Exercises".
  //
  // The following one should fail. Uncomment to check that assert works as
  // expected:
  //
  // assert (power (1.0, 42) == 2.0)

  // add 2-3 more tests:
  //
  // ...

  // Exercise 4

  def fib (n: Int) : Int = {
      def loop(n1: Int, n2: Int, n: Int) : Int = {
        if(n == 0) n1
        else loop(n2, n1 + n2, n-1) 
      }
      loop(0, 1, n)
  }
  // def fib (n: Int) : Int = ...

  // some tests (uncomment, add more):

  // assert (fib (1) == 0)
  // ...

  // Exercise 5

  // A simple object describing a cost line; implemented imperatively, Java
  // style (this way until we learn more Scala)
  class Expense {

    // A constructor definition
    def this (tag :String, price :Int) = {
      this()
      this.tag = tag
      this.price = price
    }

    var tag   :String = "" // a tag line in the accounting system
    var price :Int    = 0 // the price is in cents
  }

  // computes the total of expenses in cents
  def total (expenses: Array[Expense]) :Int = {
	   @annotation.tailrec
	   def loop(a : Array[Expense], i: Int, total: Int): Int = {
	     if(a.length-1 == i) total+a(i).price
	     else loop(a, i+1, total+a(i).price)
	   }
  	return loop(expenses, 0, 0);
  }

  def totalTest() : Unit = {

      val testcase1 = Array[Expense](
    new Expense("Coffee", 450),
    new Expense("Cake", 350) )
      val testcase2 = Array[Expense](
    new Expense("Chocolate", 100),
    new Expense("Milk", 150) )

    assert(total(testcase1) == 800)
    assert(total(testcase2) == 800)
  }


  // Exercise 6

  def isSorted[A] (as: Array[A], ordered: (A,A) =>  Boolean) : Boolean = {
    @annotation.tailrec
    def loop(as: Array[A], i: Int, ordered: (A,A) =>  Boolean) : Boolean = {
      if(i == as.length-2)  ordered(as(i), as(i+1))
      else if(ordered(as(i), as(i+1))) loop(as, i+1, ordered)
      else false
    }
    loop(as, 0, ordered)
  }

  def isSortedTest() : Unit = {
    assert ( isSorted (Array(1,2,3,4,5,6), (a: Int, b: Int)=> a <= b))
    assert (!isSorted (Array(6,2,3,4,5,6), (a: Int, b: Int)=> a <= b))
    assert (!isSorted (Array(1,2,3,4,5,1), (a: Int, b: Int)=> a <= b))
  }


  // add two tests with another type, for example an Array[String]

  // Exercise 7: a curried version of solution to exercise 3

  def power1(x: Double) (n: Int) : Double = {
    if(n == 0) 1  
    else if(n % 2 == 0 && n > 0) { power(x, n/2) * power(x, n/2) }
    else if(n % 2 != 0 && n > 0) { x * power(x, n-1) }
    else { 1 / power(x, -1 * n) }
  }

  def power1Test() : Unit = {
    assert(power1(2)(2) == 4)
    assert(power1(2)(3) == 8)
    assert(power1(-2)(2) == 4)
    assert(power1(-2)(3) == -8)
  }

  // Exercise 8

   def curry[A,B,C] (f: (A,B)=>C) : A => (B => C) = {
   		A => (B => f(A,B))
   }

   def curryTest(): Unit= {
   		val function1 = curry[Double, Int, Double](power(_,_))
   		val function2 = function1(2)
   		val result = function2(2)
   		println(result)
   }
  //
  // test if it type checks by currying power automatically:

  // val power_curried: Double => Int => Double = ...

  // Exercise 9

  def uncurry[A,B,C] (f: A => B => C) : (A,B) => C = {
    (A,B) => f(A)(B)
  }

  def uncurryTest(): Unit = {
    val function1 = uncurry[Double, Int, Double](power1)
    val result = function1(2,2)
    println(result)
  }
  // val power_uncurried: (Double,Int) => Double =

  // Exercise 10

  def compose[A,B,C] (f: B => C, g: A => B) : A => C = {
    (A) => f(g(A))
  }

  def composeTest1(): Unit = {
    //A => B
    def function1(i: Int): Double = {
      i*2
    } 
    //B => C
    def function2(d: Double): String = {
      s"Your anwser is $d"
    }
    val result = compose[Int, Double, String](function2(_), function1(_))

    println(result(5))
  }
}
