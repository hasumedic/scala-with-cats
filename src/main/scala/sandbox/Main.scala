package sandbox

import sandbox.introduction.printable.Printable
import sandbox.introduction.printable.PrintableInstances._

object Main extends App {
  val x = Printable.format(1)
  println(x)

  Printable.print("Hello")
  Printable.print(145)
}
