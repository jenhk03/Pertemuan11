import java.util.Random

class Person(var firstName: String, var lastName: String)
{
    val fullName get() = "$firstName $lastName"
}

class SimplePerson(val name: String)

class Grade(val letter: String, val points: Double, val credits: Double)

class Student(
    val firstName: String,
    val lastName: String,
    val grades: MutableList<Grade> = mutableListOf(),
    var credits: Double = 0.0
)
{
    fun recordGrade(grade: Grade)
    {
        grades.add(grade)
        credits += grade.credits
    }
}

class Student(var firstName: String, var lastName: String, var id: Int)
{
    override fun hashCode(): Int
    {
        val prime = 31
        var result = 1
        result = prime * result + firstName.hashCode()
        result = prime * result + id
        result = prime * result + lastName.hashCode()
        return result
    }
    override fun equals(other: Any?): Boolean
    {
        if (this === other)
        {
            return true
        }
        if (other === null)
        {
            return false
        }
        if (javaClass != other.javaClass)
        {
            return false
        }
        val obj = other as Student?
        if (firstName != obj?.firstName)
        {
            return false
        }
        if (id != obj.id)
        {
            return false
        }
        return true
    }
}

fun main()
{
    val john = Person("Johnny", "Appleseed")
    println(john.fullName)
    var var1 = SimplePerson("John")
    var var2 = var1
    var homeOwner = john
    john.firstName = "John"
    println(john.firstName)
    println(homeOwner.firstName)
    john === homeOwner
    val impostorJohn = Person("John", "Appleseed")
    john === homeOwner
    john === impostorJohn
    impostorJohn === homeOwner
    homeOwner = impostorJohn
    john === homeOwner
    homeOwner = john
    john === homeOwner
    var imposters = (0..100).map {
        Person("John", "Appleseed")
    }
    imposters.map {
        it.firstName == "John" && it.lastName == "Appleseed"
    }.contains(true)
    println(imposters.contains(john))
    val mutableImposters = mutableListOf<Person>()
    mutableImposters.addAll(imposters)
    mutableImposters.contains(john)
    mutableImposters.add(Random().nextInt(5), john)
    println(mutableImposters.contains(john))
    val indexOfJohn = mutableImposters.indexOf(john)
    if (indexOfJohn != -1)
    {
        mutableImposters[indexOfJohn].lastName = "Bananapeel"
    }
    println(john.fullName)
    var jane = Student("Jane", "Appleseed")
    val history = Grade("B", 9.0, 3.0)
    var math = Grade("A", 16.0, 4.0)
    jane.recordGrade(history)
    jane.recordGrade(math)
    jane = Student("John", "Appleseed")
    var credits = 0.0
    jane.credits
    math = Grade("A", 20.0, 5.0)
    jane.recordGrade(math)
    jane.credits
}