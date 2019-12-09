package learniscala

object ForLoop {

  def main(args: Array[String]): Unit = {
    for (i <- 1 until 10 if i == 2) {
      println(i)
    }
  }
}
