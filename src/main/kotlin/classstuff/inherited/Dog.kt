package classstuff.inherited

class Dog(var name: String, var age: Int) : Animal(age) {

    var color : String = ""
    constructor(name: String, age: Int, color: String) : this(name, age) {
        this.color = color
    }

    override fun canInherited() {
        println("Dog canInherited")
    }
    override fun toString(): String {
        return "name: " + name + ", age: " + age + ", color: " + color
    }
}