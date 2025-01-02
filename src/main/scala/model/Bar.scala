package model

import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color

case class Bar() {
  val rectangle: Rectangle = new Rectangle {
    x = 180
    y = 380
    width = 100
    height = 10
    fill = Color.Blue
  }

  def move(dx: Double): Unit = {
    rectangle.translateX.value += dx
    rectangle.translateX.value = math.max(0, math.min(300, rectangle.translateX.value))
  }
}