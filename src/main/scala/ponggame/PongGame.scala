package ponggame

import javafx.fxml.FXMLLoader
import javafx.scene as jfxs
import ponggame.model.User
import ponggame.view.{GameController, LeaderboardController}
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.layout.{AnchorPane, BorderPane, LayoutIncludes}
import scalafx.Includes.*
import scalafx.stage.Modality
    
object PongGame extends JFXApp3:
  var roots: Option[BorderPane] = None
  
  var userData = new ObservableBuffer[User]() // Initialize an empty ObservableBuffer

  def initializeLeaderboard(): Unit =
    // Add some sample users or load from storage
    userData += new User("John", 1500)
    userData += new User("Jane", 2000)
  
  override def start(): Unit =
    val rootResource = getClass.getResource("view/GameView.fxml")
    val loader = new FXMLLoader(rootResource)
    loader.load()
    roots = Option(loader.getRoot[jfxs.layout.BorderPane])
    stage = new PrimaryStage():
      title = "Pong Game"
      scene = new Scene():
        root = roots.get

  def showGameView(): Unit =
    val resource = getClass.getResource("view/GameView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    roots.get.center = loader.getRoot[jfxs.layout.AnchorPane]
    

  def showLeaderboard(): Unit =
    val resource = getClass.getResource("view/LeaderboardView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    val leaderboardPane = loader.getRoot[AnchorPane]
    roots.get.center = leaderboardPane


  def showProfileView(): Unit =
    val resource = getClass.getResource("view/ProfileView.fxml")
    val loader = new FXMLLoader(resource)
    loader.load()
    roots.get.center = loader.getRoot[jfxs.layout.AnchorPane]

