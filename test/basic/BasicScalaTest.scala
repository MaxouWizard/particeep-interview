package basic

import org.scalatestplus.play.PlaySpec

class BasicScalaTest extends PlaySpec {

  "BasicScala" should {
    "encode url parameters" when {
      "default behavior" in {
        val parameters = Map[String, String](
          "sort_by" -> "name",
          "order_by" -> "asc",
          "user_id" -> "12"
        )

        val expected = "?sort_by=name&order_by=asc&user_id=12"

        BasicScala.encodeParamsInUrl(parameters) mustBe expected
      }

      "empty params" in {
        val parameters = Map.empty[String, String]
        val expected = ""

        BasicScala.encodeParamsInUrl(parameters) mustBe expected
      }

      "single param" in {
        val parameters = Map[String, String]("sort_by" -> "name")
        val expected = "?sort_by=name"

        BasicScala.encodeParamsInUrl(parameters) mustBe expected
      }
    }

    "compute email" in {
      BasicScala.isEmail("jean@particeep@particeep.com") mustBe false
      BasicScala.isEmail("jean@gmail.co") mustBe true
      BasicScala.isEmail("jean_particeep.com") mustBe false
      BasicScala.isEmail("jean particeep.com") mustBe false
      BasicScala.isEmail("jean_particeep@com") mustBe true
      BasicScala.isEmail("jean-particeep-@-particeep-.com") mustBe false
    }

    "compute power" in {
      /* Let's trust Math.pow to check */
      BasicScala.power(2, 3) mustBe Math.pow(2, 3).toInt
      BasicScala.power(10, 4) mustBe Math.pow(10, 4).toInt
      BasicScala.power(99999, 10) mustBe Math.pow(99999, 10).toInt
      BasicScala.power(99, 38997) mustBe Math.pow(99, 38997).toInt
    }
  }
}
