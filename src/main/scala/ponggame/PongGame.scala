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
    Player("Alice", 120),
    Player("Bob", 95),
    Player("Charlie", 80)
  )
  
  override def start(): Unit =
    println("Start method called!")
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
    roots.get.center = gameRoot
    controller.initialize()
    

  def showLeaderboard(): Unit =
    val resource = getClass.getResource("/ponggame.view/LeaderboardView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val leaderboardPane = loader.getRoot[AnchorPane]
    val controller = loader.getController[view.LeaderboardController]
    roots.get.center = leaderboardPane


  def showProfileView(): Unit =
    val resource = getClass.getResource("/ponggame.view/ProfileView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val profilePane = loader.getRoot[AnchorPane]
    val controller = loader.getController[view.ProfileController]
    roots.get.center = profilePane

end PongGame
