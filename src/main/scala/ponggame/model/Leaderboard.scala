package ponggame.model

import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.collections.ObservableBuffer

class Leaderboard(initName: String, initScore: Int):
  private val players = ObservableBuffer[User]()

  def getTopPlayers(): Seq[User] = 

    players.sortBy(-_.highestScore.value).toSeq

end Leaderboard

