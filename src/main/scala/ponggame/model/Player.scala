package ponggame.model

import scalafx.beans.property.{StringProperty, IntegerProperty, ObjectProperty}

class Player(nameS: String, highestscoreS: Int):
  var name = new StringProperty(nameS)
  var highestScore = ObjectProperty[Int](1234)

  def updateHighestScore(newScore: Int): Unit =
    if newScore > highestScore.value then
      highestScore.value = newScore

end Player