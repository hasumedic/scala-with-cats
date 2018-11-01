package sandbox.monoid.and.semigroups

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.syntax.semigroup._

object AddingAllTheThings {

  def add(items: List[Int]): Int = items.reduce(_ |+| _)

  def addOption(items: List[Option[Int]]): Int = items.reduce(_ |+| _).getOrElse(Monoid[Int].empty)

  def superAdder[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(monoid.empty)(monoid.combine)

  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0, 0)

    override def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
  }
}
