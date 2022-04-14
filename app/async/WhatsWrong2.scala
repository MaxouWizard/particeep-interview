package async

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future(ceos.find(_.id == id))
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future(enterprises.find(_.ceo_id == ceo_id))
}

object WhatsWrong2 {

  /**
   * Review this code. What could be done better ? How would you do it ?
   *       => Do we have CEOs without Enterprises ? If not, returned type would be better as Future[Option[(CEO, Enterprise)]]
   *       => We don't have any error handling (CEO not found, Enterprise not found...)
   *           => Make return type Future[Either[String, (CEO, Enterprise)]]
   *              (given String is acceptable for error handling, which is probably not if we want to pattern match on the error).
   *       => Depending on why we need this method, ceo_id is preferably just a String and not an Option[String]
   *          => Otherwise we need to handle the error case of having no ceo_id provided, which makes no sense to call the function without any ceo_id.
   */
  def getCEOAndEnterprise(ceo_id: String): Future[Either[String, (CEO, Enterprise)]] = {
    CEODao.byId(ceo_id).flatMap {
      case Some(ceo) =>
        EnterpriseDao.byCEOId(ceo.id).map {
          case Some(enterprise) => Right(ceo -> enterprise)
          case None             => Left(s"No enterprise found for ceo=$ceo")
        }
      case None      => Future.successful(Left(s"No ceo found for id=$ceo_id"))
    }
  }
}
