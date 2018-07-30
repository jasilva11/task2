import akka.actor._

object System extends App {
  implicit val system: ActorSystem = ActorSystem("mySystem")

  val predictor: ActorRef = system.actorOf(Predictor.props, "predictorActor")
  val queryUser: ActorRef = system.actorOf(QueryUser.props(predictor), "queryUserActor")

  queryUser ! QueryUser.Query("Real Madrid CF", "FC Barcelona")

  scala.io.StdIn.readLine()
  system.terminate()
}