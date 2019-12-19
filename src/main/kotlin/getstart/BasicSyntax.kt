package getstart

class BasicSyntax {
    fun main() {
        print("Hello World")
    }

    // when expr
    fun decribe(obj: Any) : String =
            when(obj) {
                1 -> "one"
                "hello" -> "greeting"
                is Long -> "Long"
                !is String -> "not a string"
                else -> "unknown"
            }


}