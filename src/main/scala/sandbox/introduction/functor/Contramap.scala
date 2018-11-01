package sandbox.introduction.functor

object Contramap {

  trait Printable[A] {
    self =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] = new Printable[B] {
      override def format(value: B): String = self.format(func(value))
    }
  }

  def format[A: Printable](value: A): String = implicitly[Printable[A]].format(value)

  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = s""""$value""""
  }

  implicit val booleanPrintable: Printable[Boolean] = new Printable[Boolean] {
    override def format(value: Boolean): String = if (value) "yes" else "no"
  }

  final case class Box[A](value: A)

  implicit def boxPrintable[A: Printable]: Printable[Box[A]] = implicitly[Printable[A]].contramap[Box[A]](_.value)

}
