package sandbox.printable

import PrintableInstances._
import PrintableSyntax._

object Application extends App {

  final case class Cat(name: String, age: Int, color: String)

  val cat: Cat = Cat("Peludet", 7, "black")

  cat.print
}
