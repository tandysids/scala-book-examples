sealed trait Tree[A] {
  /*
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    this match {
      case Node(l, r) =>
        node(l.fold(node, leaf), r.fold(node, leaf))
      case Leaf(elt)  =>
        leaf(elt)
    }
   */

//  def describe: String =
//    this match {
//      case Node(l, r) => l.describe + " " + r.describe
//      case Leaf(elt) => elt.toString
//    }

  def describe: String = doAnything( (l,r) => l + " " + r, (leaf) => leaf.toString)

  def size: Int =
    this match {
      case Node(l, r) => l.size + r.size
      case Leaf(elt)  => 1
    }

  def doAnything[B](node: (B, B) => B, leaf: A => B): B =
    this match {
      case Node(l, r) => node(l.doAnything(node, leaf), r.doAnything(node, leaf))
      case Leaf(elt)  => leaf(elt)
    }
}
final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](element: A) extends Tree[A]