package sandbox.introduction.eq

import cats.Eq
import cats.syntax.eq._

object Application extends App {

  final case class Cat(name: String, age: Int, color: String)

  import cats.instances.int._
  import cats.instances.string._

  implicit val catEq: Eq[Cat] = Eq.instance((cat1, cat2) => cat1.name === cat2.name && cat1.age === cat2.age && cat1.color === cat2.color)

  val cat1 = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 33, "orange and black")

  println(cat1 === cat2)
  println(cat1 =!= cat2)


  import cats.instances.option._

  val optionCat1 = Option(cat1)
  val optionCat2 = Option.empty[Cat]

  println(optionCat1 === optionCat2)
  println(optionCat1 =!= optionCat2)
}
