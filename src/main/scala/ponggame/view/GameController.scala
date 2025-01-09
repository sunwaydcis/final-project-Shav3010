package ponggame.view

import ponggame.PongGame
import javafx.fxml.FXML
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.control.Label
import scalafx.scene.input.{KeyEvent, KeyCode}

@FXML
class GameController:
  @FXML 
  private var ball: Circle = _
  @FXML 
  private var bar: Rectangle = _
  @FXML 
  private var scoreLabel: Label = _
  private var score = 0
  private var dx = 2.0
  private var dy = 2.0

  def initialize(): Unit =
    ball.layoutX = 200
    ball.layoutY = 200

  def handleKeyPress(event: KeyEvent): Unit =
    event.code match
      case KeyCode.Left =>
        bar.setLayoutX(bar.getLayoutX - 20)
      case KeyCode.Right =>
        bar.setLayoutX(bar.getLayoutX + 20)
      case _ =>

  def updateGame(): Unit =
    ball.setLayoutX(ball.getLayoutX + dx)
    ball.setLayoutY(ball.getLayoutY + dy)

    if ball.getLayoutX <= 0 || ball.getLayoutX >= 400 then dx = -dx
    if ball.getLayoutY <= 0 then dy = -dy
    if ball.getLayoutY >= 400 then
      if bar.getBoundsInParent.intersects(ball.getBoundsInParent) then
        score += 1
        scoreLabel.setText(s"Score: $score")
        dy = -dy
      else
        gameOver()

  def gameOver(): Unit =
    val alert = new scalafx.scene.control.Alert(scalafx.scene.control.Alert.AlertType.Information):
      initOwner(PongGame.stage)
      title = "Game Over"
      headerText = "Game Over!"
      contentText = s"Your score: $score"
    alert.showAndWait()
    PongGame.showLeaderboard()
