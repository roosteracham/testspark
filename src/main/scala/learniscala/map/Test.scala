package learniscala.map

object Test {
  def main(args: Array[String]): Unit = {
    val map = Map("m" -> "1",
    "h" -> "2",
    "l" -> "3")

    println(map.get("m").get)
    println(map.get("3").getOrElse("4"))
  }
}
