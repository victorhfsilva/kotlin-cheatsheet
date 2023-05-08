# Kotlin

## Operator Functions

Operator functions e infix functions são recursos diferentes em Kotlin, mas ambos têm a ver com a forma como as funções são chamadas em um código.

Operator functions permitem que as funções sejam chamadas como operadores em expressões. Por exemplo, você pode definir uma operator function para adicionar dois objetos customizados usando o operador +.

Por outro lado, as infix functions permitem que as funções sejam chamadas usando a sintaxe de operador infixo, o que significa que elas podem ser chamadas sem usar parênteses ou pontos. Por exemplo, você pode definir uma infix function para calcular a média entre dois números.

Resumindo, a principal diferença entre as duas é que operator functions permitem que as funções sejam chamadas como operadores em expressões, enquanto infix functions permitem que as funções sejam chamadas usando a sintaxe de operador infixo.

Definindo uma operator function para adicionar dois objetos customizados:

```
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}

val p1 = Point(1, 2)
val p2 = Point(3, 4)
val result = p1 + p2
println(result)  // Output: Point(x=4, y=6)
```

Definindo uma operator function para comparar dois objetos customizados:

```
data class Person(val name: String, val age: Int) {
    operator fun compareTo(other: Person): Int {
        return age.compareTo(other.age)
    }
}

val p1 = Person("John", 30)
val p2 = Person("Jane", 25)
val result = p1 > p2
println(result)  // Output: true
```

Definindo uma operator function para acessar um elemento de um mapa:

```
data class Currency(val name: String, val symbol: String)

operator fun Map<String, Currency>.get(name: String): Currency? {
    return this[name.toUpperCase()]
}

val currencies = mapOf(
    "USD" to Currency("US Dollar", "$"),
    "EUR" to Currency("Euro", "€"),
    "JPY" to Currency("Japanese Yen", "¥")
)

val result = currencies["usd"]
println(result)  // Output: Currency(name=US Dollar, symbol=$)
```