package ponggame.view

import ponggame.model.{Leaderboard, User}
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.layout.AnchorPane

class LeaderboardController:

  // FXML injected elements
  var leaderboardPane: AnchorPane = _
  var leaderboardTable: TableView[User] = _
  var nameColumn: TableColumn[User, String] = _
  var scoreColumn: TableColumn[User, Int] = _

  // Observable buffer for leaderboard data
  private val leaderboardData = ObservableBuffer[User]()

  // Reference to the leaderboard model
  private val leaderboard = new Leaderboard()

  def initialize(): Unit =
    // Bind table columns to User properties
    nameColumn.cellValueFactory = _.value.nameProperty
    scoreColumn.cellValueFactory = _.value.highScoreProperty

    // Populate leaderboard data
    leaderboardData ++= leaderboard.getTopPlayers()
    leaderboardTable.items = leaderboardData

  // Refresh leaderboard data (useful for updates)
  def refreshLeaderboard(): Unit =
    leaderboardData.clear()
    leaderboardData ++= leaderboard.getTopPlayers()

end LeaderboardController
