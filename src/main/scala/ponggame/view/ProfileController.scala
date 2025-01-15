package ponggame.view

import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import ponggame.model.Player

class ProfileController:

  @FXML
  private var playerName: Label = null
  @FXML
  private var highestScore: Label = null

  def initialize(player: Player): Unit =
    playerName.setText(player.name.value)
    highestScore.setText(player.highestScore.value.toString)
