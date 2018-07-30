import akka.actor._

object QueryUser {
  props(predictor: ActorRef): Props = Props(new QueryUser(predictor))
  final case class Query(team1: String, team2: String)
}

class QueryUser (val predictor: ActorRef) extends Actor {
  import QueryUser._

  def receive = {
    case Query(team1, team2) => predictor!Predict(team1,team2)
    case _ => println("Unexpected message")
  }
}