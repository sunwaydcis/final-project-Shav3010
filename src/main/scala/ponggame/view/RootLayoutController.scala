package ponggame.view

import ponggame.PongGame.{showGameView, showLeaderboard, showProfileView}
import javafx.fxml.FXML
import javafx.scene.control.Menu
import javafx.event.ActionEvent
import ponggame.PongGame


class RootLayoutController:

  @FXML
  private var playMenu: Menu = _
  @FXML
  private var leaderboardMenu: Menu = _
  @FXML
  private var profileMenu: Menu = _

  @FXML
  def handlePlayMenu(action: ActionEvent): Unit = {
    PongGame.showGameView()
  }

  @FXML
  def handleLeaderboardMenu(action: ActionEvent): Unit = {
    PongGame.showLeaderboard()
  }

  @FXML
  def handleProfileMenu(action: ActionEvent): Unit = {
    PongGame.showProfileView()
  }
