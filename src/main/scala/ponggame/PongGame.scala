package ponggame

import javafx.fxml.FXMLLoader
import javafx.scene as jfxs
import javafx.scene.layout.{AnchorPane, BorderPane}
import ponggame.model.Player
import ponggame.view.{GameController, LeaderboardController, StartGameController}
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.Includes.*
    
object PongGame extends JFXApp3:
  var roots: Option[BorderPane] = None
  var playerName: Option[Player] = None
  
  val playerData: ObservableBuffer[Player] = ObservableBuffer(
    new Player("Alice", 120),
    new Player("Bob", 95),
    new Player("Charlie", 80)
  )
  
  override def start(): Unit =
    val rootResource = getClass.getResource("/ponggame.view/RootLayout.fxml")
    val loader = new FXMLLoader(rootResource)
    loader.load()

    roots = Option(loader.getRoot[jfxs.layout.BorderPane])

    stage = new PrimaryStage():
      title = "Pong Game"
      scene = new Scene():
        root = roots.get

    showStartGameView()

  def showStartGameView(): Unit =
    val resource = getClass.getResource("/ponggame.view/StartGameView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val startGameRoot = loader.getRoot[jfxs.layout.AnchorPane]
    val controller = loader.getController[view.StartGameController]
    controller.initialize(this)
    roots.get.center = startGameRoot

  def showGameView(): Unit =
    val resource = getClass.getResource("/ponggame.view/GameView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val gameRoot = loader.getRoot[jfxs.layout.AnchorPane]
    val controller = loader.getController[view.GameController]

    controller.resetGameState()

    val rootPane = roots.get
    val menuBar = rootPane.lookup("#menuBar").asInstanceOf[jfxs.control.MenuBar]
    menuBar.setVisible(true)
    controller.resetGameState()
    roots.get.center = gameRoot
    controller.initialize()

  def showLeaderboardView(): Unit =
    val resource = getClass.getResource("/ponggame.view/LeaderboardView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val leaderboardPane = loader.getRoot[AnchorPane]
    val controller = loader.getController[view.LeaderboardController]
    controller.showLeaderboard()
    roots.get.center = leaderboardPane

  def showProfileView(): Unit =
    val resource = getClass.getResource("/ponggame.view/ProfileView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val profilePane = loader.getRoot[AnchorPane]
    val controller = loader.getController[view.ProfileController]
    playerName match
      case Some(player) => controller.initialize(player)
      case None => println("No player data available!")
    roots.get.center = profilePane

end PongGame
