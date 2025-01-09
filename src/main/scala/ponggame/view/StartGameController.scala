package ponggame.view

import ponggame.PongGame
import ponggame.model.Player
import javafx.fxml.FXML
import javafx.scene.control.{Button, TextField, Label}
import scalafx.event.ActionEvent
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

  def handleStartButton(action: ActionEvent): Unit =
    val name = nameField.text.value.trim
    if name.isEmpty then
      errorLabel.text = "Name cannot be empty!"
    else
      PongGame.playerName = Some(Player(name, 0))
      PongGame.showGameView() // Navigate to GameView
