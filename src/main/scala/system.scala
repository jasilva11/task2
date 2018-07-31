import akka.actor._

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("mySystem")

  val predictor: ActorRef = system.actorOf(Predictor.props, "predictorActor")
  val queryUser: ActorRef = system.actorOf(QueryUser.props(predictor), "queryUserActor")
  val updateUser: ActorRef = system.actorOf(UpdateUser.props(predictor), "updateUserActor")

  println("Ingrese el primer equipo:")
  val team1=scala.io.StdIn.readLine()
  println("Ingrese el segundo equipo:")
  val team2=scala.io.StdIn.readLine()
  queryUser ! QueryUser.Query(team1, team2)
  println()
  println()
  Thread.sleep(200)

  println("Ingrese el nombre del jugador cuyo puntaje desea modificar:")
  val player=scala.io.StdIn.readLine()
  println("Ingrese el nuevo puntaje:")
  val newSkill=Integer.parseInt(scala.io.StdIn.readLine())
  updateUser ! UpdateUser.UpdateRequest(player, newSkill)

  scala.io.StdIn.readLine()
  system.terminate()
}
