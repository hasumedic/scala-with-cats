package sandbox.functor

import sandbox.functor.Contramap.Box

object Imap {

  trait Codec[A] { self =>
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      override def encode(value: B): String = self.encode(enc(value))
      override def decode(value: String): B = dec(self.decode(value))
    }
  }

  def encode[A: Codec](value: A): String = implicitly[Codec[A]].encode(value)
  def decode[A: Codec](value: String): A = implicitly[Codec[A]].decode(value)

  implicit val stringCodec: Codec[String] = new Codec[String] {
    override def encode(value: String): String = value
    override def decode(value: String): String = value
  }

  implicit val doubleCodec: Codec[Double] = stringCodec.imap[Double](_.toDouble, _.toString)

  implicit def boxCodec[A: Codec]: Codec[Box[A]] = implicitly[Codec[A]].imap[Box[A]](Box(_), _.value)
}
