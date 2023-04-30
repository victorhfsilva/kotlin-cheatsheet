# Kotlin

## Genéricos

Exemplos:

```
class Box<T>(val item: T) {
    fun showItem() {
        println("The item inside the box is $item")
    }
}

fun <T> printItems(vararg items: T) {
    for (item in items) {
        println(item)
    }
}

fun main() {
    val box1 = Box<String>("Hello")
    box1.showItem()

    val box2 = Box<Int>(42)
    box2.showItem()

    printItems("apple", "orange", "banana", "grape") //A tipagem do método também pode ser explicita: printItems<String>("apple", "orange", "banana", "grape")
}
```
