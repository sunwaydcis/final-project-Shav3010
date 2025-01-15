package ponggame.model

import scalafx.beans.property.{DoubleProperty, ObjectProperty}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.animation.{FillTransition, Timeline}
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
  
end Ball
