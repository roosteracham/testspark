object Hello {
  def main(args: Array[String]): Unit = {
//    var a = new Array[Int](10)
//    for (i <- a) {
//      println(i)
//    }
//    println("hello, scala")
//    println(math.pow(math.pow(3, -3), 3))
//    println(10 max 11)
//    println("crazy" * 3)
////    println(BigInt.)
////    var a: String = 3 .toString
//    var += = 3
//    += += 3
//    println(+=)
//    test1(b = "v")
    var p1 = new Person
    var p2 = new Person("asd")
  }

  def test1(a:String = "a", b: String = "b"): Unit = {
    println(a + b)
  }
}

class Person() {
  var name : String = _
  def this(name: String) {
    this
    println("this name")
  }
}