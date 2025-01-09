package ponggame.model

import ponggame.model.Player
import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalafx.collections.ObservableBuffer

class Leaderboard(initName: String, initScore: Int):
  private val players = ObservableBuffer[Player]()

  def getTopPlayers(): Seq[Player] = 

    players.sortBy(-_.highestScore.value).toSeq

end Leaderboard

