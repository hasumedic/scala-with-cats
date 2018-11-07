package sandbox.monad

import cats.Eval

object eval {

  def foldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B = as match {
    case Nil => acc
    case ::(head, tail) => f(head, foldRight(tail, acc)(f))
  }

  def evalFoldRight[A, B](as: List[A], acc: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = as match {
    case Nil => acc
    case ::(head, tail) => Eval.defer(f(head, evalFoldRight(tail, acc)(f)))
  }

  def stackSafeFoldRight[A, B](as: List[A], acc: B)(f: (A, B) => B): B = evalFoldRight(as, Eval.now(acc))((a, evalB) => evalB.map(f(a, _))).value
}

object EvalApp extends App {
  //StackOverflow
  //println(eval.foldRight[Int, Int]((1 to 10000).toList, 0)(_ + _))

  println(eval.stackSafeFoldRight[Int, Int]((1 to 10000).toList, 0)(_ + _))
}
