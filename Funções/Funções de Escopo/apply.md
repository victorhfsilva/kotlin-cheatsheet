# Kotlin

## Apply

Atualiza valores específicos dentro de uma estrutura de dados.

```
data class Person(var name: String = "", var age: Int = 0, var email: String = "")

val person = Person().apply {
    name = "John"
    age = 30
}
```