# Kotlin

## Funções

### Função Main

```
fun main(){
	//Corpo do Main
}
```

### Funções com argumentos nomeados

```
fun <nome da função>(<argumentos> : <tipo do argumento>) : <tipo de retorno> {
	//corpo da função
}
```

Exemplos:

```
fun printMessage(message: String) : Unit {
	println(message)
}
```
**Unit retorna qualquer tipo de dado ou até mesmo dado nenhum.*

```
fun sum(x: Int, y: Int) = x + y
```

```
fun div(x: Int, y:Int) : Double {
	if (y == 0) {
        throw IllegalArgumentException("y cannot be zero")
    }
	return x / y
}
```

### Funções com valores padrão

```
fun <nome da função>(<argumentos> : <tipo do argumento> = <valor padrão>) : <tipo de retorno> {
	//corpo da função
}
```

Exemplo:

```
fun printMessage(message: String = "Hello World") {
	println(message)
}
```

```
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun getCurrentTime(message: String = "Hello") {
	val currentTime = LocalTime.now() 
	val formatter = DateTimeFormatter.ofPattern("HH:mm")
	val formattedTime = currentTime.format(formatter)
	println("$message, the time is $formattedTime.")
}

fun main() {
    getCurrentTime()
}
```

### Parâmetros vararg

Aceita um número indeterminado de parâmetros como uma lista. 

Exemplos:

```
fun printAll (prefix : String, vararg messages: String){
	var num = 1
	for (m in messages) {
		println(prefix+num+" -> "+m)
	    num++
	}
}
fun main(){
	printAll(prefix = "Garota n°: ", "Camila", "Dieina", "Bruna")
}
```

Para passar um vararg como argumento de outra função utiliza-se * antes do argumento.

```
fun printValues(vararg values: String) {
    for (value in values) {
        println(value)
    }
}

fun callPrintValues(vararg values: String) {
    printValues(*values)
}

fun main() {
    callPrintValues("apple", "banana", "orange")
}
```