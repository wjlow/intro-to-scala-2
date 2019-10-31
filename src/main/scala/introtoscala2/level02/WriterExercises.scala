package introtoscala2.level02

import cats.data.Writer
import cats.implicits._

object WriterExercises {

  object ImpureLogging {

    def step1(num: Int): Int = {
      println("Executing step 1")
      num
    }

    def step2(num: Int): Int = {
      println("Executing step 2")
      num * 2
    }

    def step3(num: Int): Int = {
      println("Executing step 3\n")
      num * 3
    }

    def program(num: Int): Int = {
      val res1 = step1(num)
      val res2 = step2(res1)
      val res3 = step3(res2)

      res3
    }

    def endOfTheWorld(): Unit = {
      val result = program(100)
      println(result)
    }

  }

  object NoWriter {

    def step1(num: Int): (String, Int) =
      ("Executing step 1\n", num)

    def step2(num: Int): (String, Int) =
      ("Executing step 2\n", num * 2)

    def step3(num: Int): (String, Int) =
      ("Executing step 3\n", num * 3)

    def program(num: Int): (String, Int) = {
      val (log1, res1) = step1(num)
      val (log2, res2) = step2(res1)
      val (log3, res3) = step3(res2)

      (s"${log1}${log2}${log3}", res3)
    }

    def endOfTheWorld(): Unit = {
      val (log, result) = program(100)
      println(log)
      println(result)
    }

  }

  object UsingWriter {

    def step1(num: Int): Writer[String, Int] = {
      val writeLog = Writer.tell("Executing step 1\n")
      writeLog.map(_ => num)
    }

    def step2(num: Int): Writer[String, Int] = {
      val writeLog = Writer.tell("Executing step 2\n")
      writeLog.map(_ => num * 2)
    }

    def step3(num: Int): Writer[String, Int] = {
      val writeLog = Writer.tell("Executing step 3\n")
      writeLog.map(_ => num * 3)
    }

    def program(num: Int): Writer[String, Int] =
      for {
        res1 <- step1(num)
        res2 <- step2(res1)
        res3 <- step3(res2)
      } yield res3

    def endOfTheWorld(): Unit = {
      val (log, result) = program(100).run
      println(log)
      println(result)
    }

  }

}
