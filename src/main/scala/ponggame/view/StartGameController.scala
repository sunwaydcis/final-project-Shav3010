package ponggame.view

import ponggame.PongGame
import ponggame.model.Player
import javafx.fxml.FXML
import javafx.scene.control.{Button, TextField, Label}
import javafx.event.ActionEvent
import scalafx.Includes.{jfxTextField2sfx, jfxLabel2sfx}

@FXML
class StartGameController:
  @FXML
  var nameField: TextField = null
  @FXML
  var startButton: Button = null
  @FXML
  var errorLabel: Label = null

  private var mainApp: PongGame.type = _

  def initialize(app: PongGame.type): Unit =
    this.mainApp = app
    errorLabel.text = ""

  @FXML
  def handleStartButton(action: ActionEvent): Unit =
    val playerName = nameField.getText
    if playerName.isEmpty then
      errorLabel.text = "Name cannot be empty!"
    else
      val newPlayer = new Player(playerName, 0)
      PongGame.playerName = Some(newPlayer)
      PongGame.playerData += newPlayer
      PongGame.showGameView() // Navigate to GameView
