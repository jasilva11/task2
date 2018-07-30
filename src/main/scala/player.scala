class Player(pName:String, pTeam:String, pSkill:Int){
  val name = pName;
  val team = pTeam;
  var skill = pSkill;

  def updateSkill(newSkill: Int) = {
    this.skill = newSkill;
  }
}