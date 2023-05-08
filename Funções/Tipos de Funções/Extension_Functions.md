# Kotlin

## Extension Functions

São funções que extendem a funcionalidade e propriedades de uma classe.

Adicionando uma extension function à classe String para remover todos os caracteres não numéricos:

```
fun String.removeNonNumeric(): String {
    return this.replace("[^\\d]".toRegex(), "")
}

val text = "12a3bc4"
val numericText = text.removeNonNumeric() // resultado: "1234"
```

Adicionando uma extension function à classe Date para formatar a data no padrão "dd/MM/yyyy":

```
import java.text.SimpleDateFormat
import java.util.Date

fun Date.format(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    return sdf.format(this)
}

val date = Date()
val formattedDate = date.format() // resultado: "06/05/2023" (considerando que hoje é 06/05/2023)
```

Adicionando uma proprieadade a classe Person:

```
class Person(val name: String, val age: Int)

val Person.isAdult: Boolean
    get() = this.age >= 18

val person = Person("João", 30)
val isAdult = person.isAdult

println("Is ${person.name} an adult? $isAdult")
```



