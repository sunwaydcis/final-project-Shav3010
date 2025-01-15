package ponggame.model

import scalafx.beans.property.DoubleProperty
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.Includes.jfxRectangle2sfx

class Bar(initX: Double, initWidth: Double, initHeight: Double):
  val barX = new DoubleProperty(this, "x", initX)
  val barWidth = new DoubleProperty(this, "width", initWidth)
  val barHeight = new DoubleProperty(this, "height", initHeight)

  val shape: Rectangle = new Rectangle():
    x <== barX
    barWidth <== this.width
    barHeight <== this.height
    fill = Color.DarkGray

  def moveLeft(step: Double): Unit =
    barX.value -= step

  def moveRight(step: Double): Unit =
    barX.value += step

end Bar
