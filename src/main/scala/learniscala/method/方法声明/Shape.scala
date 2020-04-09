package learniscala.method.方法声明

import learniscala.method.方法声明.Point.Point

abstract class Shape() {
  // 两个列表参数
  def draw(offset: Point = new Point(0.0, 0.0))(f: String => Unit): Unit = {
    f(s"draw(offset=$offset), ${this.toString}")
  }

  case class Circle(center: Point, radius: Double) extends Shape
  case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape
}
