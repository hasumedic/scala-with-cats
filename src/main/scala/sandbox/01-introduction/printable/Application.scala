package sandbox.introduction.printable

import sandbox.introduction.printable.PrintableInstances._
import sandbox.introduction.printable.PrintableSyntax._

object Application extends App {

  val cat: Cat = Cat("Peludet", 7, "black")

  cat.print
}
