# Kotlin

## For

Iterando sobre um intervalo de números.

```
for (i in 1..10) {
    println(i)
}
```

Iterando sobre um vetor.

```
val numbers = arrayOf(1, 2, 3, 4, 5)

for (number in numbers) {
    println(number)
}
```

Iterando sobre uma lista com um índice.

```
val fruits = listOf("maçã", "banana", "laranja", "pera")

for ((index, fruit) in fruits.withIndex()) {
    println("A fruta no índice $index é $fruit")
}
```

Iterando sobre um mapa.

```
val map = mapOf("chave1" to "valor1", "chave2" to "valor2", "chave3" to "valor3")

for ((key, value) in map) {
    println("Chave: $key, Valor: $value")
}
```

