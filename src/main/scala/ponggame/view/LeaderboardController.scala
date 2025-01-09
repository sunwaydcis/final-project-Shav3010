package ponggame.view

import ponggame.model.Player
import ponggame.PongGame
import javafx.fxml.FXML
import javafx.scene.control.{Label, TableColumn, TableView}
import scalafx.beans.property.{StringProperty, IntegerProperty}
import scalafx.collections.ObservableBuffer
import scalafx.Includes._
import scalafx.beans.value.ObservableValue

@FXML
class LeaderboardController:
  @FXML
  private var leaderboardTable: TableView[Player] = null
  @FXML
  private var nameColumn: TableColumn[Player, String] = null
  @FXML
  private var scoreColumn: TableColumn[Int, Int] = null
  @FXML
  private var nameLabel: Label = null
  @FXML
  private var scoreLabel: Label = null

  def showLeaderboard(): Unit =
    leaderboardTable.items = PongGame.playerData

    nameColumn.cellValueFactory = _.value.name
     
    showPlayerDetails(None)

    leaderboardTable.selectionModel().selectedItem.onChange(
      (_, _, newValue) => showPlayerDetails(Option(newValue))
    )
    
  def showPlayerDetails(player: Option[Player]): Unit =
    player match
      case Some(player) =>
        // Fill the labels with player details
        nameLabel.text = player.name.value.toString
        scoreLabel.text = player.highestScore.value.toString
      case None =>
        // Clear the labels
        nameLabel.text = ""
        scoreLabel.text = ""
end LeaderboardController
