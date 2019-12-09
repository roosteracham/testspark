package learniscala.类.dogcase

class Dogcase(iname: String) {
  var name: String = iname
  var age : Int = _
  var weight: Double = _

//  this(name: String, age: Int, weight: Double) {
//    this(name)
//    this.name = name
//    this.age = age
//    this.weight = weight
//  }
  def say() : String = {
    toString
  }
  override def toString = s"Dogcase($name, $age, $weight)"
}
