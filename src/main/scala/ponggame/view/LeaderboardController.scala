package ponggame.view

import ponggame.model.User
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
  private var leaderboardTable: TableView[User] = null
  @FXML
  private var nameColumn: TableColumn[User, String] = null
  @FXML
  private var scoreColumn: TableColumn[User, Int] = null
  @FXML
  private var nameLabel: Label = _
  @FXML
  private var scoreLabel: Label = _

  def initialize(): Unit =
    nameColumn.cellValueFactory = {_.value.name }
    scoreColumn.cellValueFactory = {_.value.highestScore}
     
    showLeaderboardDetails(None)

    leaderboardTable.selectionModel().selectedItem.onChange(
      (_, _, newValue) => showLeaderboardDetails(Option(newValue))
    )

    leaderboardTable.items = ponggame.PongGame.userData
    
  private def showLeaderboardDetails(user: Option[User]): Unit
    user match
      case Some(u) =>
        nameLabel.text <== u.name
        scoreLabel.text = u.highestScore.value.toString
      case None =>
        nameLabel.text = ""
        scoreLabel.text = ""
end LeaderboardController
