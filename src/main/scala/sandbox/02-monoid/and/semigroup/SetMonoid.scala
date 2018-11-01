package sandbox.monoid.and.semigroups

import BaseMonoid._

object SetMonoid {

  def setUnionMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x ++ y
  }

  /**
    * Intersection does not conform a Monoid for Sets. It violates the identity law.
    * It does conform a Semigroup though, by complying to the associativity law.
    */
  def setIntersectionMonoid[A](): Monoid[Set[A]] = new Monoid[Set[A]] {
    override def empty: Set[A] = Set.empty[A]

    override def combine(x: Set[A], y: Set[A]): Set[A] = x intersect y
  }

  def setIntersectionSemigroup[A](): Semigroup[Set[A]] = (x: Set[A], y: Set[A]) => x intersect y

}
