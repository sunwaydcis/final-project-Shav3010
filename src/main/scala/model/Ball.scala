package model

import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color

case class Ball(var dx: Double, var dy: Double) {
  val circle: Circle = new Circle {
    centerX = 200
    centerY = 200
    radius = 10
    fill = Color.Red
  }

  def move(): Unit = {
    circle.centerX.value += dx
    circle.centerY.value += dy
  }

  def bounce(horizontal: Boolean): Unit = {
    if (horizontal) dx = -dx
    else dy = -dy
  }
}