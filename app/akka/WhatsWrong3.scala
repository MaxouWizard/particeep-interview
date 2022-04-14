package akka

import akka.actor.Actor
import akka.pattern.PipeToSupport

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

/**
 * Do you see anything that could lead to potential problems ?
 *    => We don't want to mutate state using a var, in actors, we want to use context become within Receive
 *    => Using onComplete within an actor is not good, we should use pipeTo(self) instead;
 *       otherwise we could end up handling the response in a different state it was queried.
 *    => It's also good to recoverWith the future to avoid actor crashing in case something goes wrong
 *       that way we can handle the error
 *
 *    (With such implementation we might want to wrap the String result within an object containing Try[String])
 *
 * What would you do to fix it ?
 * Do not mind about the not implemented code
 */
class WhatsWrong3 extends Actor with PipeToSupport {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
