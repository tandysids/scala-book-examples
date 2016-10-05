package bookexamples

import scala.util.Try

/**
 * Created by tsidhu15 on 10/3/16.
 */

sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

sealed trait LinkedList[A]{
  def length(): Int ={
    this match {
      case End() => 0
      case Pair(hd, rest) => 1 + rest.length()
    }
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }

  def calculator(operand1: String, operator: String, operand2: String): Unit = {

    operator match {
      case "+" =>
       val out = for {
        o1 <- toInt(operand1)
        o2 <- toInt(operand2)
      } yield (o1 + o2)

       println (out.getOrElse(0))

      case "-" =>
        val out = for {
          o1 <- toInt(operand1)
          o2 <- toInt(operand2)
        } yield (o1 - o2)

        println (out.getOrElse(0))

    }
//    val test: Int = for {
//      o1 <- toInt(operand1)
//      o2 <- toInt(operand2)
//    } yield o1 + o2

    print("x")
  }

  def contains(obj: A): Boolean ={
    this match {
      case End() => false
      case Pair(hd, rest) if hd == obj => true
      case Pair(hd, rest) => rest.contains(obj)
    }
  }

  def apply(n: Int, cur: Int = 0): Result[A] = {
    this match {
      case End() => Failure("blah")
      case Pair(hd, rest) if cur == n => Success(hd)
      case Pair(hd, rest) => rest.apply(n, cur + 1)
    }
  }
}
final case class End[A]() extends LinkedList[A]
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

sealed trait Maybe[A]{
  def fold[B](full: A => B, empty: B): B = {
    this match {
      case Empty() => empty
      case Full(b) => full(b)
    }
  }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]


sealed trait Sum[A, B] {
  def fold[C](blah: (A,B) => A, blah2: (A,B) => B): C ={
    this match {
      case Left(a) => a
      case Right(b) => b
    }
  }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]