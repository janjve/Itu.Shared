// Advanced Programming, Exercises by A. Wąsowski, IT University of Copenhagen
//
// AUTHOR1:
// AUTHOR2:
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

// An ADT of Lists
sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List {

  // override function application to provide a factory of lists (convenience)

  def apply[A](as: A*): List[A] = // Variadic function
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // Exercise 2

  def tail[A] (as: List[A]) :List[A] = as match {
  	case Nil => Nil
  	case Cons(_, xs) => xs
  }

  // Exercise 3

  def setHead[A] (as: List[A], newHead: A) : List[A] = as match {
  	  	case Nil => Nil
  	case Cons(x, xs) => Cons(newHead, xs)
  }

  // Exercise 4

  def drop[A] (l: List[A], n: Int) : List[A] = l match {
  	  	case Nil => Nil
  		case Cons(x, xs) => if(n > 0) drop[A](xs, n-1) 
  		else l
  }

  // Exercise 5

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match{
  	  	case Nil => Nil
  		case Cons(x, xs) => if(f(x)) dropWhile[A](xs, f)
  		else l
  }

  // Exercise 6

  def init[A](l: List[A]): List[A] = l match {
  	case Nil => Nil
  	case Cons(x, xs) => if(xs == Nil) Nil
  	else Cons(x, init(xs))
  }

  // Exercise 7 is in the bottom of the file


  // Exercise 8

  def foldRight[A,B] (as :List[A], z: B) (f : (A,B)=> B) :B = as match {
    case Nil => z
    case Cons (x,xs) => f (x, foldRight (xs,z) (f))
  }

  def length[A] (as: List[A]): Int = as match{
    case Nil => 0
  	 case Cons(x, xs) => foldRight[A, Int](as, 0) (
  	 	(a, b) => b+1)
  }

  // Exercise 9
  def foldLeft[A,B] (as: List[A], z: B) (f: (B, A) => B) : B = as match {
  	    case Nil => z
    case Cons (x,xs) => foldLeft(xs, f(z, x))(f)
  }

  // Exercise 10

  def sum (as : List[Int]) : Int = {
  	foldLeft[Int, Int](as, 0)(_ + _)
  }

  def product (as :List[Int]) : Int = {
  	foldLeft[Int, Int](as, 1)(_ * _)
  }

  def length1 (as :List[Int]) : Int = {
  	foldLeft[Int, Int](as, 0)((b, _) => b+1)
  }

  // Exercise 11

  def reverse[A] (as :List[A]) :List[A] = as match {
  	case Nil => Nil
  	case Cons(_,_) => foldLeft[A, List[A]](as, Nil)((b, a) => Cons(a, b))
  }

  // Exercise 12

  // def foldRight1[A,B] (as: List[A], z: B) (f: (A, B) => B) : B = ...

  // def foldLeft1[A,B] (as: List[A], z: B) (f: (B,A) => B) : B = ...

  // Exercise 13

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h,t) => Cons(h, append(t, a2))
  }

  def concat[A] (as: List[List[A]]) :List[A] = as match {
  	case Nil => Nil
    case Cons(h,t) => foldLeft[List[A], List[A]](as, List()) ((b,a) => append(b,a))
  }

  // Exercise 14
  // Tail recursive?
  def map[A,B] (a :List[A]) (f :A => B) : List[B] = a match {
   	case Nil => Nil
   	case Cons(h, t) => {
   		Cons(f(h), map[A, B](t)(f))
   	}
  }

  // Exercise 15 (no coding)

  // Exercise 16

  def filter[A] (as: List[A]) (f: A => Boolean) : List[A] = as match{
  	case Nil => Nil
  	case Cons(h, t) => {
  		val filterPredicate = (h: A, b: List[A]) => {
  			if(f(h)) Cons(h, b) 
  			else b
  		}
  		foldRight[A, List[A]](as, List()) (filterPredicate)
  	}
  }

  // Fold right example
  // 1,2,3
  // i => i*2
  // z = 1
  // <- foldright([1,2,3], 1) (f)
  // f(1, foldright([2,3], 1)(f))
  // f(1, f(2, foldright([3], 1)(f)))
  // f(1, f(2, f(3, foldRight(Nil, 1)(f))))
  // f(1, f(2, f(3, 1)))
  // f(1, f(2, 3*1)
  // f(1, 2*(3*1))
  // 1*(2*(3*1))
  // 6

  /* FOLD LEFT EXAMPLE
  1,2,3
  i => i*2
  z = 1
  <- foldleft([2,3], f(1, 1))(f)
  foldleft([2,3], 1)(f)
  foldleft([3], 2)
  foldleft(Nil, f(2, 3))
  foldleft(Nil, 6)
  6
  */

  // Exercise 17

  def flatMap[A,B] (as: List[A]) (f: A => List[B]) : List[B] = as match {
  	case Nil => Nil
  	case Cons(h,t) => {
  		val appendMap = (b: List[B], h: A) => {
  			append[B](b, f(h))
  		}
  		foldLeft[A,List[B]](as, List())(appendMap)
  	}
  }

  // Exercise 18

  // def filter1[A] (l: List[A]) (p: A => Boolean) :List[A] = ...

  // Exercise 19

  // def add (l: List[Int]) (r: List[Int]): List[Int] = ...

  // Exercise 20

  // def zipWith[A,B,C] (f : (A,B)=>C) (l: List[A], r: List[B]) : List[C] = ...

  // Exercise 21

  // def hasSubsequence[A] (sup: List[A], sub: List[A]) :Boolean = ...

  // Exercise 22

  // def pascal (n :Int) : List[Int] = ...

  // a test: pascal (4) = Cons(1,Cons(3,Cons(3,Cons(1,Nil))))

  // Assert Exercises works
  def testAll() {
  	// Exercise 13
	val testcase1_13 = Cons(1, Cons(2, Nil))
	val testcase2_13 = Cons(3, Cons(4, Nil))
	val expected_13 = Cons(1, Cons(2, Cons(3, Cons(4,  Nil))))
	val actual_13 = concat[Int](List(testcase1_13, testcase2_13))
	//println(s"Exercise 13 Actual result: $actual_13")
	assert (actual_13 == expected_13, "Exercise 13 error")

	  // Exercise 14
    val testcase1_14 = List(1,2,3)
	val expected_14 = List(1,4,9)
	val actual_14 = map[Int, Int](testcase1_14)((x: Int) => x*x)
	//println(s"Exercise 14 Actual result: $actual_14")
  	assert(actual_14 == expected_14, "Exercise 14 error")

  	// Exercise 16
  	val testcase1_16 = List(1,2,3,4)
  	val expected_16 = List(2,4)
  	val actual_16 = filter[Int](testcase1_16)((x: Int) => x%2 == 0)
  	//println(s"Exercise 16 Actual result: $actual_16")
  	assert(actual_16 == expected_16, "Exercise 16 error")

  	// Exercise 17
  	val testcase1_17 = List(1,2,3)
  	val expected_17 = List(1,0.5, 2, 1.0, 3, 1.5)
  	val actual_17 = flatMap[Int, Double](testcase1_17)((x: Int) => List(x, x/2.0))

  	//println(s"Exercise 17 Actual result: $actual_17")
  	assert(actual_17 == expected_17, "Exercise 17 error")

  }

  def loadTest(){
  	println("Loaded")
  }

}




// Exercise 7

object Exercise7 {

  case class SalaryLine(name: String, amount: Integer)

  // Borrow foldRight method from Exercise 8
  def foldRight[A,B] (as :List[A], z: B) (f : (A,B)=> B) :B = as match {
    case Nil => z
    case Cons (x,xs) => f (x, foldRight (xs,z) (f))
  }

  def maximumSalary (salaries: List[SalaryLine]) : Integer = salaries match {
  	 case Nil => -1
  	 case Cons(x, xs) => foldRight[SalaryLine, Int](salaries, -1) ((a, b) => if(a.amount > b) a.amount else b)
  }

  val test_case = List( SalaryLine("John",41),
    SalaryLine("Alice", 42),
    SalaryLine("Bob",40),
    SalaryLine("Bob",85),
  SalaryLine("Bob",32))
}
