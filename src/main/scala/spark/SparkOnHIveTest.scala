package spark

import java.sql.Timestamp

import org.apache.spark.sql.{DataFrame, Row, SaveMode, SparkSession}

object SparkOnHIveTest {

  def main(args: Array[String]): Unit = {
//    testRead2
  }
//
//  def testRead2: Unit = {
//    val ss = getSpark
//
//    import ss.implicits._
//    val rows: DataFrame = ss.sql(" select name, dates from hive.testtimes").persist()
//    rows.printSchema()
//
//    rows.foreach{case Row(name: String, dates: Timestamp) => {
//        println("name: " + name + ", dates: " + dates)
//      }}
//    rows.show()
//    //    print("filter")
//    //    rows.filter("name=zsf").show()
//  }

  private def getSpark = {
    SparkSession.builder().appName("SparkOnHIveTest")
      .enableHiveSupport()
      .config("spark.sql.warehouse.dir", "hdfs://had1:9000/user/hive/warehouse")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .master("local")
      .getOrCreate()
  }

  def testRead(): Unit = {
    val ss = getSpark

    val rows = ss.table("hive.employee5").persist()
    rows.show()

    //    print("filter")
    //    rows.filter("name=zsf").show()
  }

  def testWrite: Unit = {
    val ss = getSpark

    import ss.implicits._
    val rdd = ss.sparkContext.parallelize(Seq("mhl 10.0 30", "zsf 1000.0 20"))
    val res = rdd.map(x => {
      val str = x.split(" ")
      (str(0), new Timestamp(System.currentTimeMillis()))
    })

    val df = ss.createDataFrame(res)
      .withColumnRenamed("_1", "name")
      .withColumnRenamed("_2", "dates")
    df.printSchema()
    df.select("name", "dates").repartition(1)
      .write.mode(SaveMode.Overwrite).format("text").insertInto("hive.testtimes")
  }

}
