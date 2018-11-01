package sandbox.introduction.functor

import cats.Functor
import sandbox.introduction.functor.Functors.{Branch, Leaf, Tree}
import sandbox.introduction.functor.Imap._

object Application extends App {

  val intTree: Tree[Int] = Branch(Branch(Leaf(4), Leaf(1)), Leaf(5))
  val doubleIntTree: Tree[Int] = Branch(Branch(Leaf(8), Leaf(2)), Leaf(10))
  assert(Functor[Tree].map(intTree)(_ * 2) == doubleIntTree)


  val optionIntTree: Tree[Option[Int]] = Branch(Branch(Leaf(Some(4)), Leaf(None)), Leaf(Some(5)))
  val doubleOptionIntTree: Tree[Option[Int]] = Branch(Branch(Leaf(Some(8)), Leaf(None)), Leaf(Some(10)))
  assert(Functor[Tree].map(optionIntTree)(_.map(_ * 2)) == doubleOptionIntTree)



  import Contramap._
  println(format("hello"))
  println(format(true))

  println(format(Box("Hello World!")))
  println(format(Box(false)))

  //Doesn't compile
//  println(format(Box(123)))


  println(encode(123.4))
  println(decode[Double]("123.4"))
  println(encode(Box(123.4)))
  println(decode[Box[Double]]("123.4"))
}
