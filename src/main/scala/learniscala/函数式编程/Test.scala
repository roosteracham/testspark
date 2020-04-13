package learniscala.函数式编程

object Test {
  def main(args: Array[String]): Unit = {

  }

  def m2: Int => Int = {
    val factor = 2
    val multi = (i: Int) => i * factor
    multi
  }

  def m1(f: Int => Int) = {
    (1 to 10) filter (_%2 == 0) map(f) reduce(_*_)
  }

  println(m1(m2))

  def pf(a: String)(b: String)(c: String): String = a + " " + b + " "+ c

  def pf1: String => String => String = pf("hello")

//  def pf2 = pf("world")

  println(pf1("world")("123"))
//  println(pf2("hello")("3"))
}
