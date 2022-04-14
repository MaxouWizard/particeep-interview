package akka

import akka.actor.{Actor, ActorSystem, Props}

/**
 * Question about Akka framework http://akka.io
 *
 * When receiving a message that says "Hello", BasicActor must print "Hello there."
 * It must print "What?" when receiving any other message
 */
class BasicActor extends Actor {
  override def receive: Receive = {
    case "Hello" => println("Hello there.")
    case _       => println("What?")
  }
}

object BasicActor {
  def props(): Props = Props(new BasicActor())
}

object FireActor {

  /**
   * Create an instance of BasicActor
   *
   * Make it print "Hello there." and "What?"
   */
  def fireActor(): Unit = {
    val system = ActorSystem()
    val actor = system.actorOf(BasicActor.props(), name = "actor")

    actor ! "Hello"
    actor ! 1
  }
}
