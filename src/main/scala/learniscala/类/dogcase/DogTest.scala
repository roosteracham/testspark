package learniscala.类.dogcase

object DogTest {
  def main(args: Array[String]): Unit = {
    val dog = new Dogcase("a")

//    val dog = new Dogcase()
    println(dog.say())
  }
}
