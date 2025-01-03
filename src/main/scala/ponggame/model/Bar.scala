package ponggame.model

import scalafx.beans.property.{DoubleProperty}
import javafx.scene.shape.Rectangle

class Bar(initX: Double, initWidth: Double, initHeight: Double):
  val x = new DoubleProperty(this, "x", initX)
  val width = new DoubleProperty(this, "width", initWidth)
  val height = new DoubleProperty(this, "height", initHeight)

  val shape: Rectangle = new Rectangle():
    x <== this.x
    width <== this.width
    height <== this.height
    fill = Color.DarkGray

  def moveLeft(step: Double): Unit =
    x.value -= step

  def moveRight(step: Double): Unit =
    x.value += step

end Bar
