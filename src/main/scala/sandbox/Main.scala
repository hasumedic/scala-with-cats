package sandbox

import sandbox.printable.Printable
import sandbox.printable.PrintableInstances._

object Main extends App {
  val x = Printable.format(1)
  println(x)

  Printable.print("Hello")
  Printable.print(145)
}
