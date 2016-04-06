// Advanced Programming
// Andrzej Wasowski, IT University of Copenhagen

package fpinscala.laziness
import scala.language.higherKinds

import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers
import org.scalacheck._
import org.scalacheck.Prop._
import Arbitrary.arbitrary

// If you comment out all the import lines below, then you test the Scala
// Standard Library implementation of Streams. Interestingly, the standard
// library streams are stricter than those from the book, so some laziness tests
// fail on them :)

import stream00._    // uncomment to test the book solution
// import stream01._ // uncomment to test the broken headOption implementation
// import stream02._ // uncomment to test another version that breaks headOption

class StreamSpecWasowski extends FlatSpec with Checkers {

  import Stream._

  behavior of "headOption"

  // a scenario test:

  it should "return None on an empty Stream (01)" in {
    assert(empty.headOption == None)
  }

  // An example generator of random finite non-empty streams
  def list2stream[A] (la :List[A]): Stream[A] = la.foldRight (empty[A]) (cons[A](_,_))

  // In ScalaTest we use the check method to switch to ScalaCheck's internal DSL
  def genNonEmptyStream[A] (implicit arbA :Arbitrary[A]) :Gen[Stream[A]] = {
    for { la <- arbitrary[List[A]] suchThat (_.nonEmpty)}
    yield list2stream (la)
  }


  // a property test:

  it should "return the head of the stream packaged in Some (02)" in check {
    // the implict makes the generator available in the context
    implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
   ("singleton" |:
      Prop.forAll { (n :Int) => cons (n,empty).headOption == Some (n) } ) &&
    ("random" |:
      Prop.forAll { (s :Stream[Int]) => s.headOption != None } )
  }


  it should "not evaluate tail" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Stream[Int]) =>{  
        var evaluated = false;
        cons(n.take(1), n.drop(1).map(x => {
          evaluated = true
          x
        })).headOption
        !evaluated
      }
      })
  }


  behavior of "take"

  it should "not force any heads nor any tails of the Stream it manipulates" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) =>{  
        var evaluated = false;
        s.map(x => {
          evaluated = true
          x
        }).take(n)
        !evaluated
      }
      })
  }

  it should "take(n) does not force (n+1st) head (even if we force all elements of take(n))" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) =>{  
        var evaluated = false;
        var j = n;
        s.takeWhile_1(x => { j = j-1; j >= 0 }).append(s.drop(n).map(x => {
          evaluated = true
          x
        })).take(n).toList
        !evaluated
      }
      })
  }

  it should "s.take(n).take(n) == s.take(n) for any Stream s, and any int n" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) =>{  
        val s1 = s.take(n).take(n).toList
        val s2 = s.take(n).toList
        s1 == s2
      }
      })
  }


  behavior of "drop"

  it should "s.drop(n).drop(m) == s.drop(n+m) for any ints n and m" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, m: Int, s: Stream[Int]) =>{
        var n1 = Math.abs(n)
        var m1 = Math.abs(m)
        val sum = n1+m1
        println(s"$n1 $m1 $sum")
        if(n1+m1 < 0) {
          n1 = n1/2
          m1 = m1/2
        }
        val s1 = s.drop(n1).drop(m1).toList
        val s2 = s.drop(n1+m1).toList
        s1 == s2
      }
      })
  }



}
// Cons(() => A, () => Stream[A])
// h()
// Cons(A, => Stream[A])
// if(t istype(stream)) false else true