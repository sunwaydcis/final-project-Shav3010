package ponggame.model

import scalafx.beans.property.{StringProperty, IntegerProperty}

class User(initName: String):
  val name = new StringProperty(this, "name", initName)
  val highestScore = new IntegerProperty(this, "highestScore", 0)

  def updateHighestScore(newScore: Int): Unit =
    if newScore > highestScore.value then
      highestScore.value = newScore

end User
