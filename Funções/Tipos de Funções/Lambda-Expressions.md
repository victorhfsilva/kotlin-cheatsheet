# Kotlin 

## Lambda Expressions


Exemplo de lambda expression simples que imprime um número:

```
val printNumber = { number: Int -> println(number) }
printNumber(10)
```

Exemplo de lambda expression que recebe dois números e retorna a soma:

```
val sum = { a: Int, b: Int -> a + b }
println(sum(10, 20))
```

Exemplo de lambda expression que verifica se um número é par:

```
val isEven = { number: Int -> number % 2 == 0 }
println(isEven(10)) // true
println(isEven(7)) // false
```

Exemplo de lambda expression que recebe uma lista de números e retorna a soma:

```
val numbers = listOf(1, 2, 3, 4, 5)
val sum = numbers.fold(0) { acc, number -> acc + number }
println(sum) // 15
```

A função fold é aplicada à lista de inteiros, usando o valor inicial 0 como acumulador e a função lambda acc, i -> acc + i como operação acumulativa. Essa função lambda recebe dois parâmetros: o acumulador atual e o próximo elemento da lista. A operação acumulativa simplesmente soma o acumulador atual com o próximo elemento da lista e retorna o resultado, que se torna o novo valor do acumulador.

Exemplo de lambda expression que filtra uma lista de números pares:

```
val numbers = listOf(1, 2, 3, 4, 5)
val evenNumbers = numbers.filter { it % 2 == 0 }
println(evenNumbers) // [2, 4]
```

