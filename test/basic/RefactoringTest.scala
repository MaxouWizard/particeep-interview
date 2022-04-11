package basic

import basic.Refactoring.File
import org.scalatestplus.play.PlaySpec

class RefactoringTest extends PlaySpec {

  "Refactoring" should {

    "work properly" in {
      val files = List[File](
        File("File0", "Categ0"),
        File("File1", "Categ0"),
        File("File2", "Categ1"),
        File("File3", "Categ2"),
        File("File4", "Categ2"),
        File("File5", "Categ3")
      )

      val categories = Set[String]("Categ0", "Categ1", "Categ2", "Categ3")

      Refactoring.getCategories(files) mustBe categories
    }

  }

}
