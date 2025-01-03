package ponggame.model

import scalafx.beans.property.{DoubleProperty, ObjectProperty}
import scalafx.scene.paint.Color
import javafx.animation.FillTransition
import scalafx.util.Duration
import javafx.scene.shape.Circle

class Ball(initX: Double, initY: Double, initRadius: Double):
  val x = new DoubleProperty(this, "x", initX)
  val y = new DoubleProperty(this, "y", initY)
  val radius = new DoubleProperty(this, "radius", initRadius)
  val color = ObjectProperty[Color](Color.White)

  val shape: Circle = new Circle():
    centerX <== x
    centerY <== y
    radius <== this.radius
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
