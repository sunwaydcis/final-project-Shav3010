package ponggame.model

import scalafx.beans.property.{StringProperty, IntegerProperty}

class User(initName: String, initScore: Int):
  var name = new StringProperty(this, "name", initName)
  var highestScore = new IntegerProperty(this, "highestScore", initScore)

  def updateHighestScore(newScore: Int): Unit =
    if newScore > highestScore.value then
      highestScore.value = newScore

end User
