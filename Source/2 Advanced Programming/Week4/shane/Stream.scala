// Advanced Programming 2015
// Andrzej Wąsowski, IT University of Copenhagen
//
// AUTHOR1: Jan Vium Enghoff <jaen@itu.dk>
// AUTHOR2: Søren Harrison <soeh@itu.dk>
// GROUP NO.: 23

// meant to be compiled, for example: fsc Stream.scala

//package fpinscala.laziness
// Added test method instead because classpath yields some problems.

import Stream._

sealed trait Stream[+A] {

  def headOption () :Option[A] =
    this match {
      case Empty => None
      case Cons(h,t) => Some(h())
    }

  def tail :Stream[A] = this match {
      case Empty => Empty
      case Cons(h,t) => t()
  }

  def foldRight[B] (z : =>B) (f :(A, =>B) => B) :B = this match {
      case Empty => z
      case Cons (h,t) => f (h(), t().foldRight (z) (f))
      // Note 1. f can return without forcing the tail
      // Note 2. this is not tail recursive (stack-safe) It uses a lot of stack
      // if f requires to go deeply into the stream. So folds sometimes may be
      // less useful than in the strict case
    }

  // Note 1. eager; cannot be used to work with infinite streams. So foldRight
  // is more useful with streams (somewhat opposite to strict lists)
  def foldLeft[B] (z : => B) (f :(A, => B) => B) : B = this match {
      case Empty => z
      case Cons (h,t) => t().foldLeft (f (h(),z)) (f)
      // Note 2. even if f does not force z, foldLeft will continue to recurse
    }

  def exists (p : A => Boolean) : Boolean = this match {
      case Empty => false
      case Cons (h,t) => p(h()) || t().exists (p)
      // Note 1. lazy; tail is never forced if satisfying element found this is
      // because || is non-strict
      // Note 2. this is also tail recursive (because of the special semantics
      // of ||)
    }


    // Exercise 2
    def toList: List[A] = this match {
        case Empty => Nil
        case Cons (h,t) => h() :: t().toList
    }
    
    // Exercise 3
    def take (n :Int) :Stream[A] = this match {
        case Empty => Empty
        case Cons(h,t) => 
            if(n > 1) cons(h(), t().take(n-1)) 
            else cons(h(), Empty)
    }
    
    def drop (n :Int) :Stream[A] = this match {
        case Empty => Empty
        case Cons(h,t) => 
            if(n > 0) t().drop(n-1)
            else cons(h(), t())
    }

    // Exercise 4
    def takeWhile (p: A => Boolean): Stream[A] = this match{
        case Empty => Empty
        case Cons(h,t) =>  
            if(p(h())) cons(h(), t().takeWhile(p))
            else cons(h(), Empty)
    }

    // Exercise 5
    def forAll(p: A => Boolean): Boolean = this match {
        case Empty => true
        case Cons(h,t) => p(h()) && t().forAll(p)
    }
    
    // Exercise 6
    def takeWhileFoldRight(p: A => Boolean): Stream[A] = 
      foldRight[Stream[A]](Empty)((a, b) => if(p(a)) cons(a, b) else b)
    
    // Exercise 7
    def headOptionFoldRight () :Option[A] =
      foldRight[Option[A]](None)((a,b) => Some(a))

    // Exercise 8
    def map[B](f: A => B) : Stream[B] = 
      foldRight[Stream[B]](Empty)((a,b) => Cons(f(a), b))

    def filter(p: A => Boolean): Stream[A] = 
      foldRight[Stream[A]] (Empty) ((a,b) => if(p(a)) cons(a, b) else b) 
    
    def append[B >: A](that: Stream[B]): Stream[B] =
      foldRight[Stream[B]] (that) ((a,b) => Cons(a, b))
    
    def flatMap[B](f: A => Stream[B]): Stream[B] =
      foldRight[Stream[B]](Empty)((a,b) => b.append(f(a)))
    
    // Exercise 9
    // This is okay since filter will only evaluate until a value is found when using streams. 
    // This is unlike filter on List which will check every element matching the predicate p.
    def find (p :A => Boolean) :Option[A] = this.filter (p).headOption

  // Exercise 11
  def unfold[A, S](z : S)(f : S => Option[(A, S)]) : Stream[A] = f(z) match{
  	case None => Empty
  	case Some((a, b)) => Cons(() => a, () => unfold(b)(f))
}

	// Exercise 14
	def startsWith[A](that : Stream[A]) : Boolean = that.foldRight ((true, this)) ((a,b) => { 
		val b1 = b._1
		val b2 = b._2
		val newval = b2.take(1).headOption.get
		(a==newval && b1, b2.drop(1))
	})._1
}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: ()=>A, t: ()=>Stream[A]) extends Stream[A]


object Stream {

  def empty[A]: Stream[A] = Empty

  def cons[A] (hd: => A, tl: => Stream[A]) :Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  // Exercise 1
  def to (n :Int) :Stream[Int] = if (n==1) Cons(() => n,()=>Empty) else Cons(() => n, () => to(n-1))
  def from (n :Int) :Stream[Int] = Cons(() => n, () => from(n+1))

  // Exercise 10
  def fibs : Stream[Int] = {
    def fib(prev : Int, curr : Int) : Stream[Int] = {
      Cons(() => curr, () => fib(curr, prev+curr))
    }
  	Cons(() => 0, () => fib(0,1))
  } 

  // Exercise 12
  def from1 (n :Int) :Stream[Int] = Empty.unfold (n) (a => Some(a,a+1))

  def apply[A] (as: A*) :Stream[A] = if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))
}

object test extends App {
  val a = Stream.to(5);
  println(a.toList)

  val b = Stream.from(2);
  println(b.take(100).toList)
}