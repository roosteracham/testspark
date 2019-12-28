package getstart

object Idioms {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = listOf<String>("m", "h", "l")
        list.filter { it.startsWith("m") }
                .map { it.toUpperCase() }
                .forEach { println(it) }

        println(BasicSyntax(age = 2).decribe(1))
        println(BasicSyntax(age = 3).toString())

        val value = "abc"
        val val2 by lazy { "lazy" }
        value.length?.let { print(val2.length) }

        listOf(1,2,3,4,5).forEach{
            if (it == 3) {
                return@forEach
            }
            println(it)
        }
        print("ret loop")
    }

    fun testRet(): Int? {
        val a : String = ""
        return a?.length?:"abc".length
    }
}