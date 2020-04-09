package learniscala.method.partialfuction

/**
 * 偏函数是用来做什么的呢
 */
object TestPF {
  def main(args: Array[String]): Unit = {
    List("asd", 10.9, 23) foreach({x =>
      println(x.toString)
      println(d(x, stringPF))
      println(tryPF(x, stringPF))
      println(d(x, doublePF))
      println(tryPF(x, doublePF))
      println(d(x, pf))
      println(tryPF(x, pf))

      println("============")
    })
  }

  // 定义一个匹配字符串的偏函数
  def stringPF: PartialFunction[Any, String] = {case s: String => "YES"}
  // 定义一个匹配double的偏函数
  //PartialFunction[Any, Double] 第一个any表示接受任意类型的参数，Double表示匹配时的输出类型
  def doublePF: PartialFunction[Any, Double] = {case d: Double => 12.2}

  // 生命一个可以匹配string和double的pf
  val pf = stringPF orElse doublePF

  def tryPF(x: Any, f: PartialFunction[Any, Any]) : String = {
    try {
      f(x).toString
    } catch {
      case _: MatchError => "ERROR"
    }
  }

  def d(x: Any, f: PartialFunction[Any, Any]) : String = {
    f.isDefinedAt(x).toString
  }
}
