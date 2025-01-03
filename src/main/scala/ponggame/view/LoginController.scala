package ponggame.view

import ponggame.PongGame
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.Button
import scalafx.stage.Stage

@FXML
class LoginController:
  @FXML private var nameField: TextField = null
  @FXML private var playButton: Button = null

  def handlePlay(): Unit =
    if (nameField.getText().nonEmpty) then
      PongGame.userData += User(nameField.getText(), 0)
      PongGame.showGameView()
    else
      val alert = new scalafx.scene.control.Alert(scalafx.scene.control.Alert.AlertType.Warning):
        initOwner(Stage())
        title = "Invalid Name"
        headerText = "Please enter a valid name."
        contentText = "Name field cannot be empty."
      alert.showAndWait()
