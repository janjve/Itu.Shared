// Advanced Programming
// Andrzej Wasowski, IT University of Copenhagen
// Name1: Søren Harrison
// Name2: Jan Vium Enghoff
// Group: 23

package fpinscala.laziness
import scala.language.higherKinds

import org.scalatest.FlatSpec
import org.scalatest.prop.Checkers
import org.scalatest.exceptions._
import org.scalacheck._
import org.scalacheck.Prop._
import Arbitrary.arbitrary

// If you comment out all the import lines below, then you test the Scala
// Standard Library implementation of Streams. Interestingly, the standard
// library streams are stricter than those from the book, so some laziness tests
// fail on them :)

import stream00._    // uncomment to test the book solution
//import stream01._ // uncomment to test the broken headOption implementation
//import stream02._ // uncomment to test another version that breaks headOption

class StreamSpecSoehJaen extends FlatSpec with Checkers {

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

  def genInfiniteStream[A] (implicit arbA :Arbitrary[A]) :Gen[Stream[A]] = {
    for { la <- arbitrary[A] }
    yield constantStream(la)
  }

  def constantStream[A] (n :A) :Stream[A] = {
    Cons(() => n, () => constantStream(n))
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
        cons(n.take(1), n.drop(1).map(x => {
          throw new TestFailedException("evaluated the tail",1)
          x
        })).headOption
        true
      }
      })
  }


  behavior of "take"

  it should "not force any heads nor any tails of the Stream it manipulates" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) =>{  
        s.map(x => {
          throw new TestFailedException("forced a head somewhere",1)
          x
        }).take(n)
        true
      }
      })
  }

  it should "take(n) does not force (n+1st) head (even if we force all elements of take(n))" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) =>{  
        var j = n;
        s.takeWhile(x => { j = j-1; j >= 0 }).append(s.drop(n).map(x => {
          throw new TestFailedException("forced n+1st head",1)
          x
        })).take(n).toList
        true
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
    implicit def arbInt = Arbitrary(Gen.chooseNum(0,Int.MaxValue/2))
    ("random" |:
      Prop.forAll { (n: Int, m: Int, s: Stream[Int]) => {
        val s1 = s.drop(n).drop(m).toList
        val s2 = s.drop(n+m).toList
        s1 == s2
      }
      })
  }

  it should "s.drop(n) does not force any of the dropped elements heads" in check {
    implicit def arbIntStreamCallback = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    implicit def arbInt = Arbitrary(Gen.chooseNum(0,Int.MaxValue))
     ("empty tail" |:
      Prop.forAll { (n: Int, s: Stream[Int]) => {
        s.take(n).map(x => {
          throw new TestFailedException("forced a head somewhere",1)
          x
          }).drop(n).headOption
        true
      }
      })
  }

  it should "s.drop(n) does not force any of the dropped elements heads even when forcing tail" in {
    def callback(n: Int): Int = {
      assert(false)
      n
    }
    cons(callback(3), cons(callback(4), cons(5, empty))).drop(2).headOption
    assert(true)
  }

  behavior of "map"

  it should "x.map(id) == x (where id is the identity function)" in check {
    implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (n: Int, s: Stream[Int]) => {
        val s1 = s.map(x => x)
        s.startsWith(s1) && s.toList.length == s1.toList.length
      }
      })
  }

  it should "map terminates on infinite streams" in check {
    implicit def arbInfIntStream = Arbitrary[Stream[Int]] (genInfiniteStream[Int])
    ("random" |:
      Prop.forAll { (s: Stream[Int]) => {
        s.map(x => x+1)
        true
      }
      })
  }

  behavior of "append"

  it should "return the same stream when appending an empty stream" in check {
    implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (s: Stream[Int]) => {
        val s1 = s.append(empty)
        s.startsWith(s1) && s.toList.length == s1.toList.length
      }
      })
  }

  it should "hold that s.append(s2.append(s3)) == s.append(s2).append(s3)" in check {
    implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (s1: Stream[Int], s2: Stream[Int], s3: Stream[Int]) => {
        val s4 = s1.append(s2).append(s3) 
        val s5 = s1.append(s2.append(s3))
        s4.startsWith(s5) && s4.toList.length == s5.toList.length
      }
      })
  }

  it should "not change the size no matter what order append is called" in check {
    implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
    ("random" |:
      Prop.forAll { (s1: Stream[Int], s2: Stream[Int]) => {
        val s3 = s1.append(s2)
        val s4 = s2.append(s1)
        s3.toList.length == s4.toList.length
      }
      })
  }

  it should "hold that s1 comes before s2 for s1.append(s2)." in check {
  implicit def arbIntStream = Arbitrary[Stream[Int]] (genNonEmptyStream[Int])
  ("random" |:
    Prop.forAll { (s1: Stream[Int], s2: Stream[Int]) => {
      val s3 = s1.append(s2)
      s3.startsWith(s1) && s3.drop(s1.toList.length).startsWith(s2) 
    }
    })
  }

}
