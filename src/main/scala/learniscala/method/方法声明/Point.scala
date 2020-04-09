package learniscala.method.方法声明

object Point {
  def main(args: Array[String]): Unit = {

  }
  case class Point(x: Double = 0.0, y: Double = 0.0) {
    def shift(dx: Double, dy: Double) = {
      copy(x + dx, y + dy)
    }
  }

  val p = new Point(1, 2)
  val p2 = p.copy(y = 3)

  println(p)
  println(p2)

}
