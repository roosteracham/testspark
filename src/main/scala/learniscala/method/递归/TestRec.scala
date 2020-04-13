package learniscala.method.递归

object TestRec {
  def main(args: Array[String]): Unit = {

  }

  // 尾递归, Scala对其有些许优化
  def factorial(i: Int): Long = {
    @scala.annotation.tailrec
    def fact(i: Int, acc: Int): Long = {
      if (i <= 1) acc
      else fact(i - 1, i * acc)
    }
    fact(i, 1)
  }

  (0 to 5) foreach(x => {
    println(factorial(x))
  })

  // 非尾递归
//  @scala.annotation.tailrec
  def feb(x: Int): Int = {
    if (x < 1) 1
    else feb(x - 1) + feb(x - 2)
  }

  def testUnit(i: Int) {i * 2}

  def testUnit2(i: Int) = {i * 2}

  // 任何时候都应该显示指定方法返回值，不能只依赖Scala的类型推断
  println(testUnit(2))
  println(testUnit2(2))
  
}
