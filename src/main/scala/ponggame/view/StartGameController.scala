package ponggame.view

import ponggame.PongGame
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, TextField, Label}

class StartGameController:
  var nameField: Option[TextField] = None
  var startButton: Button = _
  var errorLabel: Label = _

  private var mainApp: PongGame.type = _

  def initialize(app: PongGame.type): Unit =
    this.mainApp = app
    errorLabel.text = "" // Clear error label initially

  def handleStartButton(action: ActionEvent): Unit =
    val name = nameField.textfield.value.trim
    if name.isEmpty then
      errorLabel.text = "Name cannot be empty!"
    else
      mainApp.uName = Some(name) // Save the username
      mainApp.showGameView() // Navigate to GameView
