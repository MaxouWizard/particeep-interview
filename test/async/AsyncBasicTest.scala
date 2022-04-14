package async

import org.scalatestplus.play.PlaySpec

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class AsyncBasicTest extends PlaySpec {

  "AsyncBasic" should {
    "compute properly" when {

      "given id 1" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("1"), Duration.Inf)

        result mustBe Right(1099)
      }

      "given id 2" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("2"), Duration.Inf)

        result mustBe Left("Webservice2 returned error: No value")
      }

      "given id 3" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("3"), Duration.Inf)

        result mustBe Left("Webservice1 timed out or did not provide any value")
      }

      "given id 5" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("5"), Duration.Inf)

        result mustBe Left("Webservice2 returned error: No value")
      }

      "given id 10" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("10"), Duration.Inf)

        result mustBe Left("Value exceeded Int.MaxValue")
      }

      "given id 42" in {
        val result: Either[String, Int] = Await.result(AsyncBasic.compute("42"), Duration.Inf)

        result mustBe Left(s"Webservice1 timed out or did not provide any value")
      }
    }
  }

}
