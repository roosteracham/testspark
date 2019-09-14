package spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WC {
  def main(args: Array[String]): Unit = {

    var conf = new SparkConf().setAppName("zsf")
      .setMaster("local")
    var sc = new SparkContext(conf)
    var input:RDD[String] = sc.textFile("1.txt")
//    var  words = input.flatMap(line => line.split(" "))
    var counts = input.flatMap(x=>x.split(" ")).map(x=>(x,1)).reduceByKey({case (x, y) => x+y})
//    counts.foreach(x=>println(x))
//    counts.saveAsTextFile(args(1))
    counts.sortBy(x => (x._2, x._1), false).top(5).foreach(println)
  }

}
