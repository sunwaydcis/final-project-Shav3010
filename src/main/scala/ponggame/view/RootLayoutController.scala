package ponggame.view

import ponggame.PongGame
import javafx.fxml.FXML
import javafx.scene.control.{Menu, MenuBar}
import javafx.event.ActionEvent

class RootLayoutController:

  @FXML
  private var playMenu: Menu = null
  @FXML
  private var leaderboardMenu: Menu = null
  @FXML
  private var profileMenu: Menu = null
  @FXML
  private var menuBar: MenuBar = null

  @FXML
  def initialize(): Unit =
    menuBar.setVisible(false)

  @FXML
  def handlePlayMenu(action: ActionEvent): Unit =
    PongGame.showGameView()
    menuBar.setVisible(true)

  @FXML
  def handleLeaderboardMenu(action: ActionEvent): Unit =
    PongGame.showLeaderboardView()

  @FXML
  def handleProfileMenu(action: ActionEvent): Unit =
    PongGame.showProfileView()

end RootLayoutController
