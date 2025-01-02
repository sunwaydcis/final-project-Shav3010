package view

import scalafxml.core.macros.sfxml
import scalafx.scene.control.Label
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.animation.AnimationTimer
import model.{Ball, Bar}

@sfxml
class GameController(
                      private val ball: Circle,
                      private val bar: Rectangle,
                      private val scoreLabel: Label
                    ) {
  private val gameBall = Ball(2, 3)
  private val gameBar = Bar()
  private var score = 0

  private val animation = AnimationTimer { _ =>
    gameBall.move()
    checkCollision()
  }

  def startGame(): Unit = animation.start()

  private def checkCollision(): Unit = {
    if (gameBall.circle.centerX.value <= 0 || gameBall.circle.centerX.value >= 400) gameBall.bounce(horizontal = true)
    if (gameBall.circle.centerY.value <= 0) gameBall.bounce(horizontal = false)

    if (gameBall.circle.centerY.value >= bar.layoutY.value - 10 &&
      gameBall.circle.centerX.value >= bar.layoutX.value &&
      gameBall.circle.centerX.value <= bar.layoutX.value + bar.width.value) {
      gameBall.bounce(horizontal = false)
      score += 1
      scoreLabel.text = s"Score: $score"
    }

    if (gameBall.circle.centerY.value > 400) animation.stop()
  }
}