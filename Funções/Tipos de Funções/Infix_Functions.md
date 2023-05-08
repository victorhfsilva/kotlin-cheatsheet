# Kotlin

## Infix Functions

As infix functions são definidas usando a palavra-chave infix, que permite que as funções sejam chamadas como operadores entre dois valores. As infix functions podem ser úteis para melhorar a legibilidade do código.

Definindo uma infix function para calcular a média entre dois números:

```
infix fun Double.average(other: Double): Double {
    return (this + other) / 2.0
}

val result = 4.0 average 8.0
println(result)  // Output: 6.0
```

Definindo uma infix function para verificar se uma lista contém um determinado elemento:

```
infix fun <T> List<T>.containsElement(element: T): Boolean {
    return element in this
}

val myList = listOf(1, 2, 3, 4, 5)
val result = myList containsElement 3
println(result)  // Output: true
```

