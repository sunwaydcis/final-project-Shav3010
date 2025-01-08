package ponggame.model

import scalafx.beans.property.{DoubleProperty, ObjectProperty}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.shape.Shape
import scalafx.animation.FillTransition
import scalafx.util.Duration
import scalafx.Includes.sfxObjectPropertyWithSFXDelegate2jfxObjectProperty


class Ball(initX: Double, initY: Double, initBallRadius: Double):
  val x = new DoubleProperty(this, "x", initX)
  val y = new DoubleProperty(this, "y", initY)
  val ballRadius = new DoubleProperty(this, "ballRadius", initBallRadius)
  val color = ObjectProperty[Color](Color.White)

  val shape: Circle = new Circle():
    centerX <== x
    centerY <== y
    radius <== ballRadius
    fill <== color

  def changeColor(newColor: Color): FillTransition =
    new FillTransition(Duration(1000), shape):
      fromValue = color.value
      toValue = newColor
      onFinished = _ => color.value = newColor

  def animateColorChange(): Unit =
    val randomColor = Color.rgb(
      scala.util.Random.nextInt(256),
      scala.util.Random.nextInt(256),
      scala.util.Random.nextInt(256)
    )
    changeColor(randomColor).play()

end Ball
