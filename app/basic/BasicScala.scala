package basic

import scala.annotation.tailrec

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    if (params.isEmpty) ""
    else
      params.map { case (key, value) => s"$key=$value" }.mkString("?", "&", "")
  }

  /* reference : https://html.spec.whatwg.org/multipage/input.html#valid-e-mail-address */
  private val emailRegex = """^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = emailRegex.findFirstMatchIn(maybeEmail).isDefined

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1'723'793'299 <- Probably not ?
   *
   */
  def power(i: Int, n: Int): Int = {

    /* Hold 'value' as a Long to avoid .toInt casts over loop */
    @tailrec
    def loop(value: Long, exp: Int): Int = {
      if (exp == 0) value.toInt
      else {
        val updated: Long = value * i

        if (updated >= Int.MaxValue)
          Int.MaxValue
        else
          loop(updated, exp - 1)
      }
    }

    loop(1L, n)
  }

}
