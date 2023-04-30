# Kotlin

## When Statement

### When simples

```
val fruit = "banana"
when (fruit) {
    "apple" -> println("This is an apple")
    "banana" -> println("This is a banana")
    "orange" -> println("This is an orange")
    else -> println("Unknown fruit")
}
```

### When para múltiplos valores

```
val fruit = "banana"
when (fruit) {
    "apple", "pear" -> println("This is an apple or pear")
    "banana", "orange" -> println("This is a banana or orange")
    else -> println("Unknown fruit")
}
```

### When para intervalos

```
val age = 18
when (age) {
    in 0..17 -> println("You are under 18")
    in 18..65 -> println("You are between 18 and 65")
    else -> println("You are over 65")
}
```

### When para tipos

```
fun printLength(obj: Any) {
    when (obj) {
        is String -> println("Length of string is ${obj.length}")
        is List<*> -> println("Size of list is ${obj.size}")
        is Int -> println("This is an integer")
        else -> println("Unknown type")
    }
}
```

### When em lista

```
data class Person(val name: String, val age: Int)

val people = listOf(
    Person("John", 25),
    Person("Mary", 30),
    Person("Bob", 35)
)

fun findPerson(name: String, age: Int) {
    when (Person(name, age)) {
        in people -> println("$name is in the list")
        else -> println("$name is not in the list")
    }
}
```

## When Expression

```
val x = 5
val result = when (x) {
    in 1..10 -> "x is between 1 and 10"
    in 11..20 -> "x is between 11 and 20"
    else -> "x is outside the range of 1 to 20"
}

println(result) 
```
