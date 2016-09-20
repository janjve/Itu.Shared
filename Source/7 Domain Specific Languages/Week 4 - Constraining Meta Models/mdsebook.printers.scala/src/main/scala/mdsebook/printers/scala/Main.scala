// (c) mddbook, wasowski, tberger
// This is the main driver for printer constraints
// Run using 'sbt run'
package mdsebook.printers.scala

import mdsebook.printers.T1._
import mdsebook.printers.T2._
import mdsebook.printers.T3._
import mdsebook.printers.T4._
import mdsebook.printers.T5._
import mdsebook.printers.T6._

import mdsebook.scala.EMFScala._

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl

object Main extends App {

  // Register a resource factory for XMI files

  Resource.Factory.Registry.INSTANCE.
    getExtensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl)

  // Register the the meta-model packages (quite a few in this exercise, impure)

  T1Package.eINSTANCE.eClass
  T2Package.eINSTANCE.eClass
  T3Package.eINSTANCE.eClass
  T4Package.eINSTANCE.eClass
  T5Package.eINSTANCE.eClass
  T6Package.eINSTANCE.eClass

  // Load the example models

  val cases = List( "t1-00", "t1-01", "t1-02", "t1-03", "t2-00", "t2-01",
           "t2-02", "t3-00", "t3-01", "t4-00", "t4-01", "t5-00", "t5-01",
           "t6-00", "t6-01", "t6-02")
  val uris = cases.map { c => URI.createURI (s"../mdsebook.printers/test-files/$c.xmi") }
  val ress = uris.map { (new ResourceSetImpl).getResource (_,true) }

  // Check constraints and print results


  for ( (f,res) <- cases zip ress ) {

    val content = EcoreUtil.getAllContents[EObject] (res,false).toList
		val relevant = Constraints.invariants.filter ( _._2 appliesToAny content )
  	val (labels,predicates) = relevant.unzip
    val results = predicates.map { p => content.forall { p check _ } }
    val msgs = results.map { if (_) "pass" else "FAIL" }

    println (s"\nCase $f: ")
    for ( (l,m) <- labels zip msgs ) println (s"  [$m] $l")
  }

}
