# Kotlin

## Ranges

Range de números inteiros:

```
val range = 1..10
for (i in range) {
    println(i)
}
```

Range de caracteres:

```
val range = 'a'..'z'
for (c in range) {
    println(c)
}
```

Range de datas:

```
import java.time.LocalDate

val startDate = LocalDate.of(2022, 1, 1)
val endDate = LocalDate.of(2022, 12, 31)
val range = startDate..endDate

for (date in range) {
    println(date)
}
```

Range invertido:

```
val range = 10 downTo 1
for (i in range) {
    println(i)
}
```

Range com incremento personalizado:

```
val range = (1..10 step 2)
for (i in range) {
    println(i)
}
```

Em Kotlin, também é possível utilizar ranges em uma condição if para verificar se um valor está dentro de um determinado intervalo.

```
val x = 5
if (x in 1..10) {
    println("$x está dentro do intervalo de 1 a 10")
} else {
    println("$x está fora do intervalo de 1 a 10")
}
```

## Range Inclusivo e Exclusivo

O operador .. cria um range inclusivo, ou seja, inclui o valor final do range. Por exemplo, o range 1..5 incluirá os valores 1, 2, 3, 4 e 5.

Já o operador until cria um range exclusivo, ou seja, exclui o valor final do range. Por exemplo, o range 1 until 5 incluirá os valores 1, 2, 3 e 4, mas não incluirá o valor 5.

```
// Range inclusivo criado com o operador ".."
val range1 = 1..5
for (i in range1) {
    print("$i ")
}
// Output: 1 2 3 4 5

// Range exclusivo criado com o operador "until"
val range2 = 1 until 5
for (i in range2) {
    print("$i ")
}
// Output: 1 2 3 4
```

