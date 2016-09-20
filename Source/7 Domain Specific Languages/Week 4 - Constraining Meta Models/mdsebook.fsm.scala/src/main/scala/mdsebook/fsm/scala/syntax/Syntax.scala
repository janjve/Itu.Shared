package mdsebook.fsm.scala.syntax

import scala.util.parsing.combinator.PackratParsers
import scala.util.parsing.combinator.RegexParsers

import scala.language.postfixOps

import mdsebook.fsm.scala.adt.Pure._

class FsmPureASTParser extends RegexParsers with PackratParsers {

  val identifier :Parser[String] =
    """\^?[a-zA-Z]+""".r ^^ { s => if (s startsWith "^") s substring 1 else s }
    // the escaping hat is for consistence with Xtend


  val MACHINE = literal ("machine")
  val INITIAL = literal ("initial")
  val STATE = literal ("state")
  val ON = literal ("on")
  val INPUT = literal ("input")
  val OUTPUT = literal ("output")
  val AND = literal ("and")
  val GO = literal ("go")
  val TO = literal ("to")

  val LBR = literal ("[")
  val RBR = literal ("]")

  val string = regex("\"[^\"]*\"".r) ^^ { s => s.substring(1, s.length - 1) }

  def modelParser (fname: String): Parser[Model] =
    (finiteStateMachine*) ^^ { ms => Model(fname, ms.toList) }

  lazy val finiteStateMachine: Parser[FiniteStateMachine] =
    MACHINE ~> identifier ~ states ^^
    { case ~(n,(initial,ts)) =>
        val name = n
        val states :List[String] = ts.map { _._1 }
        val transitions :Map[String,List[Transition]]= ts.toMap
        FiniteStateMachine (name,states,transitions,initial) }

  lazy val states: Parser[(String, List[(String,List[Transition])])] =
    LBR ~> INITIAL ~> identifier ~ (state*) <~ RBR ^^
    { case i ~ ss => (i,ss) }

  lazy val state: Parser[(String,List[Transition])] =
    STATE ~> identifier ~ (leavingTransitions?) ^^
    { case s ~ ts => (s,ts getOrElse List()) }

  lazy val leavingTransitions: Parser[List[Transition]] =
    LBR ~> (transition*) <~ RBR

  lazy val transition: Parser[Transition] =
    ON ~> INPUT ~> string ~ (output?) ~ target ^^
    { case i ~ o ~ t => Transition (target=t, input=i, output=o getOrElse null) }

  lazy val output: Parser[String] = OUTPUT ~> string

  lazy val target: Parser[String] = AND ~> GO ~> TO ~> identifier
}


