import akka.actor._

object UpdateUser {
  def props(predictor: ActorRef): Props = Props(new UpdateUser(predictor))
  final case class Update(player: String)
}

class UpdateUser (val predictor: ActorRef) extends Actor {
  import UpdateUser._

  def receive = {
    case Update(player) =>
    case _ => println("Unexpected message")
  }
}