# Kotlin

## With

O with pode ser utilizado para simplificar o acesso a propriedades de um objeto:

```
class Person(val name: String, val age: Int)

val person = Person("John", 30)

with(person) {
    println("Name: $name")
    println("Age: $age")
}
```

Usando o with para realizar uma sequência de operações em um objeto:

```
val list = mutableListOf<String>("apple", "banana", "cherry")

with(list) {
    add("orange")
    remove("banana")
    shuffle()
}
```

