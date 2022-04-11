package basic

import org.scalatestplus.play.PlaySpec

class ComputeAverageTest extends PlaySpec {

  "ComputeAverage" should {
    "compute properly" when {
      "case 1" in {
        val values: List[Double] = List(1.0, 5.0, 9.0)
        ComputeAverage.average(values) mustBe 5.0
      }

      "case 2" in {
        val values: List[Double] = List.empty
        ComputeAverage.average(values) mustBe 0.0
      }

      "case 3" in {
        val values: List[Double] = List(10.0)

        ComputeAverage.average(values) mustBe 10.0
      }

      "case 4" in {
        val values: List[Double] = List.range(1, 11).map(_.toDouble)

        ComputeAverage.average(values) mustBe 5.5
      }

      "case 5" in {
        val values: List[Double] = List(15.0, 15.0)

        ComputeAverage.average(values) mustBe 15.0
      }
    }
  }

}
