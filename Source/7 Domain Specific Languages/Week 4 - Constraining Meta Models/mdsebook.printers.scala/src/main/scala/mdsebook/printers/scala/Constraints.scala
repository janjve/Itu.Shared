// (c) mdsebook, wasowski, tberger

package mdsebook.printers.scala

import scala.collection.JavaConversions._ // for natural access to EList
import mdsebook.scala.EMFScala._
import mdsebook.printers._

object Constraints {

 /* We put constraints for all the six meta-models on one list for simplicity
  * (the context type will ensure that the constraints are only applied to
  * instances of the right models anyways -- and we don't have a performance
  * issue in this exercise)
  */

	val invariants = List[(String,Constraint)] (

	)

}
