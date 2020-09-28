package spark

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.beans.factory.xml.XmlBeanFactory
import org.springframework.core.io.ClassPathResource
import spring.父子继承.Sub

object Test {

  def main(args: Array[String]): Unit = {
//    System.setProperty("hadoop.home.dir", "D:\\hadoop-2.7.5\\bin")
    var conf = new SparkConf().setAppName("AppName")
      .setMaster("local")
    var sc = new SparkContext(conf)
//    test1(sc)

//    testParalleize(sc)
    testParalleize(sc)
  }

  private def test1(sc: SparkContext) = {
    var rdd = sc.textFile("1.txt")
    println(rdd.count())
  }
  private def testParalleize(sc: SparkContext) = {
    var input = sc.parallelize(List(1,1,1,2,2,3,4))
    var a = input.map(x => x * x)
    println(a.collect().mkString(", "))
    println()
    val sub = new XmlBeanFactory(new ClassPathResource("spring2.xml")).getBean(classOf[Sub])
    def method(x: Int) = sub.abc(x)
    input.map(method).foreach(println)
  }

  private def testTop(sc: SparkContext) = {
    implicit object KeyVauleOrdering extends scala.math.Ordering[(String, Int)] {

      def compare(x: (String, Int), y: (String, Int)) =
        if (x._2.compareTo(y._2) == 0){
          x._1.compareTo(y._1)
        } else{
          x._2.compareTo(y._2)
        }
    }

    var i = sc.parallelize(List(1,1,1,2,2,3,4))
    var p = i.map(x => (x, 1)).reduceByKey({case (x, y) => x+y})
    p.foreach(x => println(x))
    p.map(x => (x._1 + "", x._2)).sortBy(x=>(x._2, x._1), false).top(2)(KeyVauleOrdering).foreach(x => println(x))
  }
}