package getstart

class BasicSyntax(val name : String =  "abc", var age : Int) {
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
    fun descObject() : String {
        return "name: " + name
    }

    override fun toString(): String {
        return "name: " + name + ", age: " + age
    }
}