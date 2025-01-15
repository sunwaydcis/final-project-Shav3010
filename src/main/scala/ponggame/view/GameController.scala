package ponggame.view

import ponggame.PongGame
import javafx.fxml.FXML
import javafx.scene.shape.{Circle, Rectangle}
import javafx.scene.control.Label
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.AnchorPane
import javafx.util.Duration
import javafx.animation.{KeyFrame, Timeline}
import scalafx.application.Platform
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType


@FXML
class GameController:
  @FXML 
  private var ball: Circle = null
  @FXML 
  private var bar: Rectangle = null
  @FXML 
  private var scoreLabel: Label = null
  @FXML
  private var root: AnchorPane = null

  private var dx: Double = _
  private var dy: Double = _
  private val barSpeed: Double = 20.0
  private var score: Int = 0
  private var gameOverTriggered: Boolean = false
  private var timeline: Timeline = _


  def initialize(): Unit =
    ball.setLayoutX(200)
    ball.setLayoutY(200)

    dx = (scala.util.Random.nextDouble() * 2 - 1) * 4
    dy = (scala.util.Random.nextDouble() * 2 - 1) * 4

    val minSpeed = 2.0
    if (Math.abs(dx) < minSpeed) {
      dx = if (dx > 0) minSpeed else -minSpeed
    }
    if (Math.abs(dy) < minSpeed) {
      dy = if (dy > 0) minSpeed else -minSpeed
    }
    bar.setLayoutX(150)
    bar.setLayoutY(root.getHeight - bar.getHeight)

    root.sceneProperty().addListener: (_, _, scene) =>
      if (scene != null) then
        root.heightProperty().addListener: (_, _, _) =>
          bar.setLayoutX(150)
          bar.setLayoutY(root.getHeight - bar.getHeight)

    root.setOnKeyPressed(handleKeyPress)
    root.requestFocus()

    // Create a Timeline for the animation
    timeline = new Timeline(
      new KeyFrame(
        Duration.seconds(1.0 / 60.0), // Frame rate of 60 FPS
        (_: javafx.event.ActionEvent) => updateBallPosition() // Call update method every frame
      )
    )
    timeline.setCycleCount(-1) // Loop the animation indefinitely
    timeline.play() // Start the animation

  def updateBallPosition(): Unit =
    if gameOverTriggered then return // Skip update if the game is over

    // Move the ball by the current speed in X and Y direction
    ball.setLayoutX(ball.getLayoutX + dx)
    ball.setLayoutY(ball.getLayoutY + dy)

    // Check for collision with the left and right boundaries
    if ball.getLayoutX <= 0 || ball.getLayoutX >= root.getWidth - ball.getRadius() * 2 then
      dx = -dx // Reverse the direction in the X-axis

    // Check for collision with the top boundary
    if ball.getLayoutY <= 0 then
      dy = -dy // Reverse the direction in the Y-axis

    // Check if the ball touches the bottom boundary (game over condition)
    if ball.getLayoutY + ball.getRadius() * 2 >= root.getHeight then
      triggerGameOver()

    checkBarCollision()

  def checkBarCollision(): Unit =
    if (ball.getLayoutY + ball.getRadius() * 2 >= bar.getLayoutY &&
      ball.getLayoutX + ball.getRadius() >= bar.getLayoutX &&
      ball.getLayoutX <= bar.getLayoutX + bar.getWidth) then
        dy = -dy // Reverse the direction in the Y-axis (ball hits the bar)
        increaseScore() // Increase score when the ball hits the bar

  def handleKeyPress(event: KeyEvent): Unit =
    event.getCode match
      case KeyCode.LEFT =>
        if bar.getLayoutX > 0 then
          bar.setLayoutX(bar.getLayoutX - barSpeed)
      case KeyCode.RIGHT =>
        if bar.getLayoutX < root.getWidth - bar.getWidth then
          bar.setLayoutX(bar.getLayoutX + barSpeed)  // Move the bar right
      case _ =>

  def increaseScore(): Unit =
    score += 1 // Increase score by 1
    scoreLabel.setText(s"$score")

  def triggerGameOver(): Unit =
    if !gameOverTriggered then
      gameOverTriggered = true
      if timeline != null then timeline.stop()
      val currentPlayer = PongGame.playerName
      currentPlayer.foreach(_.updateHighestScore(score))
      gameOver()

  def gameOver(): Unit =
    Platform.runLater(() => {
      val alert = new Alert(AlertType.Warning):
        initOwner(PongGame.stage)
        title = "Game Over"
        headerText = "Game Over!"
        contentText = s"Your score: $score"
      alert.showAndWait()
      PongGame.showLeaderboardView()
    })

  def resetGameState(): Unit = 
    // Reset ball's position and speed
    ball.setLayoutX(200)
    ball.setLayoutY(200)
    dx = 3.5
    dy = 3.5 
    score = 0 
    scoreLabel.setText(s"$score")
    gameOverTriggered = false 
  

