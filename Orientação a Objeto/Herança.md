# Kotlin 

## Herança

### Herança Simples

```
open class Dog {
	//Todas as classes em Kotlin são final (não podem ser extendidas) a menos que declaradas open.
	open fun sayHello(){
		println("Hello! I'm a pretty wild dog.")
	}
}

//Herança
class Yorkshire : Dog() {
	//Polimorfismo
	override fun sayHello(){
		println("Good evening, sir! I'm a fancy Yorkshire.")
	}
}

fun main() {
	val dog: Dog = Yorkshire()
	dog.sayHello() //Output: Good evening, sir! I'm a fancy Yorkshire.
}
```

### Herança com construtor parametrizado

```
open class Tiger(val origin: String){
	fun sayHello() {
		println("Meow! I'm a ferocious cat.")
	}
}

class SiberianTiger : Tiger("Siberia"){
	override fun sayHello(){
		println("Meow. I'm not just a cat. I'm a Siberian cat!")
	}
}

fun main() {
	val tiger: Tiger = SiberianTiger()
	tiger.sayHello()
}
```

### Passando Argumentos do Construtor para a Superclasse

```
open class Lion (val name: String, val origin: String) {
	fun sayHello() {
		println("Hello. I'm $name, the king of %origin")
	}
}

class BrazilianLion(name: String) : Lion(name=name, origin = "Brazil")

fun main() {
	val lion: Lion = BrazilianLion("Benício")
	lion.sayHello()
}