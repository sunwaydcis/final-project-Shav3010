package ponggame.view

import javafx.fxml.FXML
import javafx.scene.control.{Label, TextField}
import ponggame.model.Player

class ProfileController:

  @FXML
  private var playerName: Label = _
  @FXML
  private var highestScore: Label = _

  def initialize(player: Player): Unit =
    playerName.setText(player.name.value)
    highestScore.setText(s"${player.highestScore}")
