package sandbox.introduction.show

import cats.implicits._

object Application extends App {

  val cat = Cat("Peludet", 7, "white")

  println(cat.show)
}
