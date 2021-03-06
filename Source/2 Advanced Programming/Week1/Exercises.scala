// Advanced Programming, Exercises by A. Wąsowski, IT University of Copenhagen
//
// AUTHOR1: Jan Vium Enghoff (jaen@itu.dk)
// AUTHOR2: Søren Harrison (soeh@itu.dk)
// Group number: 23
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

  assert (power (2.0, 2) == 4.0)
  assert (power (1.0, 42) == 1.0)
  //
  // The above assertions should pass when you call "scala Exercises".
  //
  // The following one should fail. Uncomment to check that assert works as
  // expected:
  //
  // <jaen & soeh> Uncomment to validate.
  //assert (power (1.0, 42) == 2.0, "power (1.0, 42) == 2.0: This is expected to result in an assertion error.")

  // add 2-3 more tests:
  //
  // ...

  assert (power (2.0, 0) == 1.0)
  assert (power (2.5, 2) == 6.25)
  assert (power (-2.0, 2) == 4.0)

  // Exercise 4

  // Assuming n > 0 since the exercise does not specify expected result for invalid values of n.
  def fib (n: Int) : Int = {
      def loop(n1: Int, n2: Int, n: Int) : Int = {
        if(n == 1) n1
        else loop(n2, n1 + n2, n-1)
      }
      loop(0, 1, n)
  }

  // some tests (uncomment, add more):

  assert (fib (1) == 0)
  // ...
  assert (fib (2) == 1)
  assert (fib (3) == 1)
  assert (fib (5) == 3)


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

  val testcase1 = Array[Expense](
	new Expense("Coffee", 450),
	new Expense("Cake", 350) )

  assert (total (testcase1) == 800) // uncomment

  // Add one or two more tests
  // ...

  val testcaseTotalSingleElement = Array[Expense](
    new Expense("Chocolate", 200))
  assert(total(testcaseTotalSingleElement) == 200)

  val testcaseTotalMultipleElements = Array[Expense](
    new Expense("Chocolate", 200),
    new Expense("Cookie", 300),
    new Expense("Milk", 150))
  assert(total(testcaseTotalMultipleElements) == 650)

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

  // some tests (uncomment)

  assert ( isSorted (Array(1,2,3,4,5,6), (a: Int, b: Int)=> a <= b))
  assert (!isSorted (Array(6,2,3,4,5,6), (a: Int, b: Int)=> a <= b))
  assert (!isSorted (Array(1,2,3,4,5,1), (a: Int, b: Int)=> a <= b))

  // add two tests with another type, for example an Array[String]
  assert ( isSorted (Array("ab","bc","cd","de","f","ff"), (a: String, b: String)=> a <= b))
  assert ( !isSorted (Array("ff","ab","bc","de","e","f"), (a: String, b: String)=> a <= b))

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
  val power_curried: Double => Int => Double = curry(power(_,_))
  assert(power_curried(2.0)(2) == 4.0)


  // Exercise 9

  def uncurry[A,B,C] (f: A => B => C) : (A,B) => C = {
    (A,B) => f(A)(B)
  }

  def uncurryTest(): Unit = {
    val function1 = uncurry[Double, Int, Double](power1)
    val result = function1(2,2)
    println(result)
  }

  val power_uncurried: (Double,Int) => Double = uncurry(power1)
  // <jaen & soeh> Uncomment to validate.
  //assert(power_uncurried(2.0)(2) == 4.0, "This should not compile.")
  assert(power_uncurried(2.0, 2) == 4.0, "This should compile and run.")

  // Exercise 10

  def compose[A,B,C] (f: B => C, g: A => B) : A => C = {
    (A) => f(g(A))
  }

  // Test.
  // A => B
  val testcaseCompose1: (Int) => Double = (i) => i/2.0
  // B => C
  val testcaseCompose2: (Double) => String = (d) => s"Your answer is: $d"
  // Compose A => B => C to A => C
  val composedFunction = compose[Int, Double, String](testcaseCompose2(_), testcaseCompose1(_))

  assert(composedFunction(2) == "Your answer is: 1.0")
  assert(composedFunction(3) == "Your answer is: 1.5")
}
