package learniscala.隐式详解

object Test {

  def main(args: Array[String]): Unit = {

  }
  def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

  implicit val taxRate : Float = 0.08F

  println(calcTax(2))


}
