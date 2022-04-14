package akka

import org.scalatestplus.play.PlaySpec

import java.io.ByteArrayOutputStream

class BasicActorTest extends PlaySpec {

  "BasicActor" should {
    "output properly on receive" in {
      val streamOutput = new ByteArrayOutputStream

      Console.withOut(streamOutput) {
        FireActor.fireActor()

        Thread.sleep(1000)

        /* Meh ? If we wanted to do this properly and make this test meaningful,
         * we would inject a logger dependency into the basic-actor and mock it there
         * */
      }

      val output    = streamOutput.toString
      val separator = System.getProperty("line.separator") /* making sure this is os-friendly, we might work on Windows and release on Linux */
      val expected  = s"Hello there.${separator}What?$separator"

      output mustBe expected
    }
  }

}
