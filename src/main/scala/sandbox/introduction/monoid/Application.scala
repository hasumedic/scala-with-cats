package sandbox.introduction.monoid

import sandbox.introduction.monoid.AllSetForMonoids.{setIntersectionMonoid, setUnionMonoid}
import sandbox.introduction.monoid.BaseMonoid.associativityLaw
import sandbox.introduction.monoid.TheTruthAboutMonoids.{booleanAndMonoid, booleanOrMonoid, booleanReverseAndMonoid, booleanReverseOrMonoid}
import cats.instances.int._
import cats.instances.option._
import sandbox.introduction.monoid.AddingAllTheThings.Order

object Application extends App {

  val True = true
  val False = false

  //Boolean Monoids

  //AndMonoid
  assert(associativityLaw(True, True, True)(booleanAndMonoid))
  assert(associativityLaw(False, True, True)(booleanAndMonoid))
  assert(associativityLaw(False, False, True)(booleanAndMonoid))
  assert(associativityLaw(False, False, False)(booleanAndMonoid))
  assert(associativityLaw(True, False, False)(booleanAndMonoid))
  assert(associativityLaw(True, True, False)(booleanAndMonoid))

  assert(BaseMonoid.identityLaw(True)(booleanAndMonoid))
  assert(BaseMonoid.identityLaw(False)(booleanAndMonoid))

  //OrMonoid
  assert(associativityLaw(True, True, True)(booleanOrMonoid))
  assert(associativityLaw(False, True, True)(booleanOrMonoid))
  assert(associativityLaw(False, False, True)(booleanOrMonoid))
  assert(associativityLaw(False, False, False)(booleanOrMonoid))
  assert(associativityLaw(True, False, False)(booleanOrMonoid))
  assert(associativityLaw(True, True, False)(booleanOrMonoid))

  assert(BaseMonoid.identityLaw(True)(booleanOrMonoid))
  assert(BaseMonoid.identityLaw(False)(booleanOrMonoid))

  //ReverseAndMonoid
  assert(associativityLaw(True, True, True)(booleanReverseAndMonoid))
  assert(associativityLaw(False, True, True)(booleanReverseAndMonoid))
  assert(associativityLaw(False, False, True)(booleanReverseAndMonoid))
  assert(associativityLaw(False, False, False)(booleanReverseAndMonoid))
  assert(associativityLaw(True, False, False)(booleanReverseAndMonoid))
  assert(associativityLaw(True, True, False)(booleanReverseAndMonoid))

  assert(BaseMonoid.identityLaw(True)(booleanReverseAndMonoid))
  assert(BaseMonoid.identityLaw(False)(booleanReverseAndMonoid))

  //ReverseOrMonoid
  assert(associativityLaw(True, True, True)(booleanReverseOrMonoid))
  assert(associativityLaw(False, True, True)(booleanReverseOrMonoid))
  assert(associativityLaw(False, False, True)(booleanReverseOrMonoid))
  assert(associativityLaw(False, False, False)(booleanReverseOrMonoid))
  assert(associativityLaw(True, False, False)(booleanReverseOrMonoid))
  assert(associativityLaw(True, True, False)(booleanReverseOrMonoid))

  assert(BaseMonoid.identityLaw(True)(booleanReverseOrMonoid))
  assert(BaseMonoid.identityLaw(False)(booleanReverseOrMonoid))


  //
  // Set semigroups: There's a single implementation possible: Union
  //

  val smallIntSet: Set[Int] = Set(1, 2, 3, 4)
  val mediumIntSet: Set[Int] = Set(1, 5, 10, 15, 20)
  val bigIntSet: Set[Int] = Set(1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

  assert(associativityLaw(smallIntSet, mediumIntSet, bigIntSet)(setUnionMonoid()))

  assert(BaseMonoid.identityLaw(smallIntSet)(setUnionMonoid()))
  assert(BaseMonoid.identityLaw(mediumIntSet)(setUnionMonoid()))
  assert(BaseMonoid.identityLaw(bigIntSet)(setUnionMonoid()))

  // Intersection Semigroup respects the associativity law
  assert(associativityLaw(smallIntSet, mediumIntSet, bigIntSet)(setIntersectionMonoid()))

  // Intersection Monoid would not respect the identity law
//  assert(BaseMonoid.identityLaw(smallIntSet)(setIntersectionMonoid()))
//  assert(BaseMonoid.identityLaw(mediumIntSet)(setIntersectionMonoid()))
//  assert(BaseMonoid.identityLaw(bigIntSet)(setIntersectionMonoid()))

  private val listOfInts = List(1, 2, 3)
  val added = AddingAllTheThings.add(listOfInts)
  assert(added == 6)

  private val maybeInts = List(None, Some(1), None, Some(5))
  val addedOptions = AddingAllTheThings.addOption(maybeInts)
  assert(addedOptions == 6)

  val superAdderInts = AddingAllTheThings.superAdder(listOfInts)
  assert(superAdderInts == 6)

  val superAdderOptions = AddingAllTheThings.superAdder(maybeInts)
  assert(superAdderOptions.contains(6))

  val orders = List(Order(40.5, 2), Order(20.4, 1.5))
  val superAdderOrders = AddingAllTheThings.superAdder(orders)
  assert(superAdderOrders == Order(60.9, 3.5))
}
