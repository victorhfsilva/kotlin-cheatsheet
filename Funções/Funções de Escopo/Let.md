# Kotlin

## Let

O let é uma função de escopo no Kotlin que permite executar um bloco de código em um objeto e retornar um resultado.

```
fun customPrint(s: String){
    print(s.uppercase())
}

fun main() {
    val isEmpty = "Teste".let{
        customPrint(it)
        it.isEmpty()
    }
    println(" is empty: $isEmpty")
}
```

No exemplo a seguir let só é executado caso nome não seja null. 

```
val nome: String? = "João"

nome?.let { // verifica se o nome não é nulo
    println("O nome é ${it.capitalize()}") // it contém o valor do nome, já que não é nulo
}
```

Usando o let para realizar operações em um objeto e retorná-lo:

```
data class Pessoa(val nome: String, val idade: Int)

fun main(){
 
    val pessoa: Pessoa = Pessoa("jOÃO", 25)

    val novaPessoa = pessoa.let {
        val novoNome = it.nome.toLowerCase().capitalize()
        Pessoa(novoNome, pessoa.idade)
    }

    println(novaPessoa) // resultado: Pessoa(nome=João, idade=25)
    
}
```

Aqui está um exemplo simples de como usar o let aninhado em Kotlin:

```
data class Pessoa(val nome: String, val idade: Int)

val pessoa: Pessoa? = Pessoa("João", 18)

pessoa?.let { pessoa ->
    pessoa.let {
        if (it.idade >= 18) {
            println("${it.nome} pode votar.")
        } else {
            println("${it.nome} não pode votar.")
        }
    }
} ?: println("Pessoa não encontrada.")
```

Neste exemplo, usamos o operador ?. para verificar se a variável pessoa não é nula. Em seguida, usamos o let para executar um bloco de código em pessoa e atribuir seu valor a uma variável chamada pessoa. Em seguida, usamos outro bloco let aninhado para verificar se a pessoa tem idade suficiente para votar. Se tiver, imprimimos uma mensagem de confirmação, caso contrário, imprimimos uma mensagem de erro. Se pessoa for nula, imprimimos uma mensagem de erro indicando que a pessoa não foi encontrada.

