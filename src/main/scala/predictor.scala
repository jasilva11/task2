import akka.actor._

object Predictor {
  def props: Props = Props(new Predictor)
  final case class Predict(team1: String, team2: String)
}

class Predictor extends Actor {
  import Predictor._

  var cache:Map[String, Int] = Map()

  def calculate(team):Int = {
    val bufferedSource = io.Source.fromFile("./files/players.csv")
    var teamSkill:Int = 0
    var numJugadores = 0
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      if(cols(2)=="Real Madrid CF")
      {
        numJugadores += 1
        teamSkill += Integer.parseInt(cols(3))
      }
    }
    teamSkill = teamSkill/numJugadores
    bufferedSource.close
    println("El " + team + " tiene " + teamSkill + " de puntaje")
    cache+(team->teamSkill)
    return teamSkill
  }

  def receive = {
    case Predict(team1, team2) => {
      if(calculate(team1) > calculate(team2))
      {
        println("El ganador sera " + team1)
      }
      else if(calculate(team2) > calculate(team1))
      {
        println("El ganador sera " + team2)
      }
      else
      {
        println("Sera empate")
      }
    }
    case _ => println("Unexpected message")
  }
}