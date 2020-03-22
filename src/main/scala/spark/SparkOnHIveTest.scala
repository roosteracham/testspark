package spark

import domain.User
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object SparkOnHIveTest {

  def main(args: Array[String]): Unit = {
    testRead()
  }

  def testRead(): Unit = {
    val ss = SparkSession.builder().appName("SparkOnHIveTest")
      .enableHiveSupport()
      .config("spark.sql.warehouse.dir", "hdfs://had1:9000/user/hive/warehouse")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .master("local")
      .getOrCreate()

    val rows = ss.table("hive.employee5").persist()
    rows.show()

//    print("filter")
//    rows.filter("name=zsf").show()
val rdd = ss.sparkContext.parallelize(Seq("mhl 10.0 30", "zsf 1000.0 20", "zgx 10000 50"))
    val res = rdd.map(x => {
      val str = x.split(" ")
        new User(str(0), str(1).toDouble, str(2).toInt)
    })

    val df = ss.sqlContext.createDataFrame(res, classOf[User])
    df.select("name", "salary", "age", "country", "state").repartition(1)
      .write.mode(SaveMode.Overwrite).format("text").insertInto("hive.employee5")
  }

}
