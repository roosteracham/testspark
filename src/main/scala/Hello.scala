import org.apache.spark.{SparkConf, SparkContext}
import spring.jdbc
import spring.jdbc.User

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
    val u1 = new User()
    u1.setName("1")
    u1.setAge(1)
    val u2 = new User()
    u2.setName("1")
    u2.setAge(2)

    val p = new Person("1")


    var conf = new SparkConf().setAppName("AppName")
      .setMaster("local")
    var sc = new SparkContext(conf)

    val r1 = sc.parallelize(Seq(u1)).map(x => (x.getName, x))
    val r3 = sc.parallelize(Seq(u2)).map(x => (x.getName, x))
    val r2 = sc.parallelize(Seq(p)).map(x => ("1", x))

    val res = r1.join(r2)
    res.foreach(x => {
      println(x._1)
      println(x._2._1)
      println(x._2._2)
    })

  }

  def test1(a:String = "a", b: String = "b"): Unit = {
    println(a + b)
  }
}

class Person() extends Serializable {
  var name : String = _
  def this(name: String) {
    this
    this.name = name
  }

  override def toString: String = name
}