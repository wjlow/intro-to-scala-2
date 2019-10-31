package introtoscala2.level01

import cats.data.Reader

object ReaderExercises {

  sealed trait Environment

  case object Dev extends Environment

  case object Prod extends Environment

  object NoReader {

    def multiplyByTwoIfProd(env: Environment, num: Int): Int =
      env match {
        case Dev => num
        case Prod => num * 2
      }

    def multiplyByTwoIfProdThenShow(env: Environment, num: Int): String =
      s"The number is ${multiplyByTwoIfProd(env, num)}"

    def isProd(env: Environment): Boolean =
      env match {
        case Dev => false
        case Prod => true
      }

    def program(env: Environment, num: Int): (Int, Boolean) =
      (multiplyByTwoIfProd(env, num), isProd(env))

    def endOfTheWorldInDev: (Int, Boolean) = {
      program(Dev, 100)
    }

    def endOfTheWorldInProd: (Int, Boolean) = {
      program(Prod, 100)
    }

  }

  object UsingReader {

    def multiplyByTwoIfProdR(num: Int): Reader[Environment, Int] =
      Reader {
        case Dev => num
        case Prod => num * 2
      }

    def multiplyByTwoIfProdThenShowR(num: Int): Reader[Environment, String] =
      multiplyByTwoIfProdR(num).map(n => s"The number is ${n}")

    def isProdR: Reader[Environment, Boolean] =
      Reader {
        case Dev => false
        case Prod => true
      }

    def programR(num: Int): Reader[Environment, (Int, Boolean)] = {
      multiplyByTwoIfProdR(num).flatMap(num => isProdR.map(bool => (num, bool)))
    }

    def endOfTheWorldInDevR: (Int, Boolean) =
      programR(100).run(Dev)

    def endOfTheWorldInProdR: (Int, Boolean) =
      programR(100).run(Prod)

  }

}
