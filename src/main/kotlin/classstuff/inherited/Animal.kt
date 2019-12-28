package classstuff.inherited

open class Animal(age : Int = 0) {

    open fun canInherited() {
        println("Animal canInherited")
    }

    fun cannotInherited() {
        println("Animal cannotInherited")
    }
}