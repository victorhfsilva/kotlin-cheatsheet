# Kotlin

## Also

Also é semelhante ao apply, com a diferença que devemos utilizar it para chamar a instância do objeto.

Usando o also para logar o valor de uma variável:

```
val myValue = 42

myValue.also {
    println("O valor atual é $it")
}
```

Usando o also para atualizar um objeto e retorná-lo:

```
data class Person(var name: String, var age: Int)

val person = Person("John", 30)

val updatedPerson = person.also {
    it.age = 31
}

println(person)         // Output: Person(name=John, age=31)
println(updatedPerson)  // Output: Person(name=John, age=31)
```

Usando o also para executar uma ação em um objeto e ignorar o valor de retorno:

```
val myList = mutableListOf("apple", "banana", "cherry")

myList.also {
    it.add("orange")
    it.remove("banana")
    it.shuffle()
}

println(myList)  // Output: [apple, cherry, orange]
```
