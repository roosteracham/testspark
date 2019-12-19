package getstart

object Idioms {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = listOf<String>("m", "h", "l")
        list.filter { it.startsWith("m") }
                .map { it.toUpperCase() }
                .forEach { println(it) }

        println(BasicSyntax().decribe(1))
    }
}