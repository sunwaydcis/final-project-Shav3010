package ponggame.model

import scalafx.beans.property.{StringProperty, IntegerProperty}

class Leaderboard(initName: String, initScore: Int):
  val name = new StringProperty(this, "name", initName)
  val score = new IntegerProperty(this, "score", initScore)

end Leaderboard

