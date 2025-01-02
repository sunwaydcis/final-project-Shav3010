package view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.{TableView, TableColumn}
import scalafx.collections.ObservableBuffer
import model.Leaderboard

@sfxml
class LeaderboardController(
                             private val leaderboardTable: TableView[(String, Int)],
                             private val nameColumn: TableColumn[(String, Int), String],
                             private val scoreColumn: TableColumn[(String, Int), Int]
                           ) {
  private val leaderboard = Leaderboard() // Load existing leaderboard

  def initialize(): Unit = {
    // Populate the TableView with leaderboard data
    val leaderboardData = ObservableBuffer(leaderboard.getTopScores(): _*)
    leaderboardTable.items = leaderboardData

    // Configure table columns
    nameColumn.cellValueFactory = { entry =>
      new scalafx.beans.property.ReadOnlyStringWrapper(entry.value._1)
    }
    scoreColumn.cellValueFactory = { entry =>
      new scalafx.beans.property.ReadOnlyIntegerWrapper(entry.value._2)
    }
  }

  def addScore(username: String, score: Int): Unit = {
    leaderboard.addScore(username, score)
    updateTable()
  }

  private def updateTable(): Unit = {
    val updatedData = ObservableBuffer(leaderboard.getTopScores(): _*)
    leaderboardTable.items = updatedData
  }

  def handleBackToMenu(): Unit = {
    // Logic to return to the main menu
    println("Returning to the main menu...")
  }
}