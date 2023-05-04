# Kotlin

## Iterator

Iterando sobre uma lista usando um Iterator

```
val list = listOf("a", "b", "c", "d")
val iterator = list.iterator()

while (iterator.hasNext()) {
    val item = iterator.next()
    println(item)
}
```

Iterando sobre um mapa usando um Iterator

```
val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
val iterator = map.iterator()

while (iterator.hasNext()) {
    val entry = iterator.next()
    println("Key: ${entry.key}, Value: ${entry.value}")
}
```

Iterando sobre um conjunto usando um Iterator

```
val set = setOf(1, 2, 3, 4, 5)
val iterator = set.iterator()

while (iterator.hasNext()) {
    val item = iterator.next()
    println(item)
}
```

Implementando a função operator fun iterator(), que retorna um Iterator para uma lista.

```
class MyCustomList(private val list: MutableList<String>) {
    operator fun iterator(): Iterator<String> {
        return list.iterator()
    }
}

fun main() {
    val myCustomList = MyCustomList(mutableListOf("foo", "bar", "baz"))
    for (item in myCustomList) {
        println(item)
    }
}
```

Usando um Iterator com uma classe personalizada.

```
class MyCustomList(private val list: MutableList<String>) : Iterable<String> {
    override fun iterator(): Iterator<String> {
        return MyCustomIterator(list)
    }

    private class MyCustomIterator(private val list: List<String>) : Iterator<String> {
        private var index = 0

        override fun hasNext(): Boolean {
            return index < list.size
        }

        override fun next(): String {
            return list[index++]
        }
    }
}

val myCustomList = MyCustomList(mutableListOf("foo", "bar", "baz"))
for (item in myCustomList) {
    println(item)
}
```
