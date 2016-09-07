package fpinscala.parsing

import language.higherKinds
import scala.language.implicitConversions
import scala.util.matching.Regex

trait Parsers[Parser[+_]] { self => // so inner classes may call methods of trait

  	implicit def operators[A](p: Parser[A]) = ParserOps[A](p)
  	implicit def regex(r: Regex): Parser[String]
	implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]):
		ParserOps[String] = ParserOps(f(a))

	def char(c: Char): Parser[Char] =
		string(c.toString) map (_.charAt(0))
	def succeed[A](a: A): Parser[A] = string("") map (_ => a)

	def string(s: String): Parser[String] 
  	def product[A,B](p: Parser[A], p2: => Parser[B]): Parser[(A,B)]
  	def or[A](p1: Parser[A], p2: Parser[A]): Parser[A]
	def slice[A](p: Parser[A]): Parser[String]
	def flatMap[A,B](p: Parser[A])(f: A => Parser[B]): Parser[B]

	def run[A](p: Parser[A])(input: String): Either[ParseError,A]
	
	def map[A,B](a: Parser[A])(f: A => B): Parser[B] = 
		flatMap(a)(x => succeed(f(x)))

	def opt[A](a: Parser[A]): Parser[Option[A]] = 
		or(a.map(Some(_)), succeed(None))

	def listOfN[A](n: Int, p: Parser[A]): Parser[List[A]] = {
		if(n == 0)  map2(p, succeed(List()))(_::_)
		else map2(p, listOfN(n-1, p))(_::_)
	}

	def many[A](p: Parser[A]): Parser[List[A]] = {
		map2(p, many(p))(_::_).or(succeed(List()))
	}

	def many1[A](p: Parser[A]): Parser[List[A]] = 
		map2(p, many(p))(_::_)	

  	def map2[A,B,C](p: Parser[A], p2: => Parser[B])(f: (A,B) => C): Parser[C] = 
  		product(p, p2).map(x => f(x._1, x._2))
  		
  	val zeroOrMoreA = char('a').many.slice.map(_.size) ** char('b').many1.slice.map(_.size)
  	val digitAndThatManyA = regex("[0-9]".r).flatMap(x => listOfN(x.toInt, char('a')))

 	case class ParserOps[A](p: Parser[A]) {
		def product[B](p2: => Parser[B]): Parser[(A,B)] = self.product(p, p2)
		def **[B](p2: Parser[B]): Parser[(A,B)] = self.product(p, p2)
		def *|[B](p2: Parser[B]): Parser[A] = (p ** p2).map(_._1) 
		def |*[B](p2: Parser[B]): Parser[B] = (p ** p2).map(_._2)
		def map[B](f: A => B): Parser[B] = self.map(p)(f)
		def or(p2: Parser[A]): Parser[A] = self.or(p, p2)
		def |(p2: Parser[A]): Parser[A] = self.or(p, p2)
		def many: Parser[List[A]] = self.many(p)
		def * : Parser[List[A]] = self.many(p)
		def slice: Parser[String] = self.slice(p)
		def many1: Parser[List[A]] = self.many1(p)
		def ? : Parser[Option[A]] = self.opt(p)
  		def map2[B,C](p2: => Parser[B])(f: (A,B) => C): Parser[C] = self.map2(p, p2)(f)
		def listOfN(n: Int): Parser[List[A]] = self.listOfN(n, p)
		def flatMap[B](f: A => Parser[B]): Parser[B] = self.flatMap(p)(f)
	}

  object Laws {
  }
}

case class Location(input: String, offset: Int = 0) {

  lazy val line = input.slice(0,offset+1).count(_ == '\n') + 1
  lazy val col = input.slice(0,offset+1).reverse.indexOf('\n')

  def toError(msg: String): ParseError =
    ParseError(List((this, msg)))

  def advanceBy(n: Int) = copy(offset = offset+n)

  /* Returns the line corresponding to this location */
  def currentLine: String = 
    if (input.length > 1) input.lines.drop(line-1).next
    else ""
}

case class ParseError(stack: List[(Location,String)] = List(),
                      otherFailures: List[ParseError] = List()) {
}

trait JSON
object JSON {
	case object JNull extends JSON
	case class JNumber(get: Double) extends JSON
	case class JString(get: String) extends JSON
	case class JBool(get: Boolean) extends JSON
	case class JArray(get: IndexedSeq[JSON]) extends JSON
	case class JObject(get: Map[String, JSON]) extends JSON


  	def jsonParser[Parser[+_]](P: Parsers[Parser]): Parser[JSON] = {
  		import P._

  		val QUOTED: Parser[String] = (""""[^"]*"""".r).map{_.dropRight(1).substring(1) }

  		//val WS: Parser[Unit] = "[\t\n ]+".r.map{_ => ()}
		//val a: Parser[String] = """"[^"]*"""".r map(x => x)	
  		(P.succeed(JNumber(123)) ** succeed(JBool(true))) map(_._1)
	}
}