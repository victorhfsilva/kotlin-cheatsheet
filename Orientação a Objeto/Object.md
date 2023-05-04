# Kotlin

## Object

### Em classes

Em Kotlin, a palavra-chave object é usada para definir uma classe que tem apenas uma instância.

```
object MySingleton {
    fun doSomething() {
        println("Doing something...")
    }
}

// Uso
MySingleton.doSomething()
```

### Em expressões

```
fun RentPrice(standardDays: Int, festivityDays: Int, specialDays: Int): Unit{
	
	val dayRates = object {
		var standard: Int = 30*standardDays
		var festivity: Int = 50*festivityDays
		var special: 100*specialDays
	}

	val total = dayRates.standard + dayRates.festivity + dayRates.special
	print("Price: R$$total")
}

```

## Companion Objects

A palavra-chave object também pode ser usada dentro de uma classe para definir um objeto companheiro (companion object). Esse objeto é semelhante a um objeto singleton, mas é associado a uma classe externa e pode acessar seus membros privados. Aqui está um exemplo:

```
class MyClass {
    companion object {
        fun doSomething() {
            println("Doing something...")
        }
    }
}

// Uso
MyClass.doSomething()
```

## Anonymous Objects

Em Kotlin, a palavra-chave object também pode ser usada para criar objetos anônimos (anonymous objects), que são objetos que não possuem nome e são definidos diretamente em um local de uso. Aqui estão alguns exemplos de como o object pode ser usado para criar objetos anônimos:

```
interface MyInterface {
    fun doSomething()
}

fun main() {
    val obj = object : MyInterface {
        override fun doSomething() {
            println("Doing something...")
        }
    }

    obj.doSomething()
}
```