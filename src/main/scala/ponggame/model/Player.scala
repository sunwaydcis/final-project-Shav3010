package ponggame.model

import scalafx.beans.property.{StringProperty, IntegerProperty, ObjectProperty}

class Player(nameS: String, highestscoreS: Int):
  var name = new StringProperty(nameS)
  var highestScore = IntegerProperty(highestscoreS)

  def updateHighestScore(newScore: Int): Unit =
    if newScore > highestScore.value then
      highestScore.value = newScore

  override def toString: String = s"Player(name=${name.value}, highestScore=${highestScore.value})"
end Player
