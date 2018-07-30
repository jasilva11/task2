import akka.actor._

object System extends App {
  implicit val system: ActorSystem = ActorSystem("mySystem")

  val predictor: ActorRef = system.actorOf(Predictor.props, "predictorActor")
  val queryUser: ActorRef = system.actorOf(QueryUser.props(predictor), "queryUserActor")

  val team1=scala.io.StdIn.readLine()
  val team2=scala.io.StdIn.readLine()
  queryUser ! QueryUser.Query(team1, team2)

  scala.io.StdIn.readLine()
  system.terminate()
}