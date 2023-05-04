# Kotlin

## Funções relevantes para Estruturas de Dados

### Filter

```
val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

// Filtra os números pares na lista original
val evenNumbers = numbers.filter { it % 2 == 0 }

// Imprime os números pares
evenNumbers.forEach { println(it) }
```

### For Each

```
val fruits = listOf("Apple", "Banana", "Grapes", "Orange", "Mango")

// Imprime cada elemento da lista com um prefixo
fruits.forEach { println("I love $it") }
```

### Map

```
val numbers = listOf(1, 2, 3, 4, 5)

// Transforma os números em seus quadrados correspondentes
val squares = numbers.map { it * it }

// Imprime os números e seus quadrados correspondentes
numbers.forEachIndexed { index, number -> println("$number * $number = ${squares[index]}") }
```

### flatMap

```
val nestedList = listOf(
    listOf(1, 2, 3),
    listOf(4, 5, 6),
    listOf(7, 8, 9)
)

// Achata a lista aninhada em uma lista única
val flatList = nestedList.flatMap { it }

// Imprime os elementos da lista achatada
flatList.forEach { println(it) }
```

