package async

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String): Future[Either[String, Int]] = {
    val first  = Webservice1.call(id)
    val second = Webservice2.call(id)

    first.flatMap {
      case Some(v1) =>
        second.map {
          case Right(v2) =>
            val value: Long = v1.toLong + v2.toLong

            if(value > Int.MaxValue) Left(s"Value exceeded Int.MaxValue") else Right(value.toInt)
          case Left(err) => Left(s"Webservice2 returned error: $err")
        }

      case None => Future.successful(Left(s"Webservice1 timed out or did not provide any value")) // Do we need to check if Webservice2 did respond ?
    }
  }

}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
