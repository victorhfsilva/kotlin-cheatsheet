# Kotlin

## Run

Tanto o let quanto o run são escopos de função em Kotlin que permitem executar um bloco de código em um objeto. A principal diferença entre eles está na forma como o objeto é referenciado dentro do escopo.

O let é usado para executar um bloco de código em um objeto e passá-lo como um argumento para a lambda. Dentro desse bloco, o objeto é referenciado por it. A principal vantagem do let é que ele permite executar um bloco de código em um objeto e, em seguida, retornar um valor calculado com base no objeto. Por exemplo, podemos usar o let para validar uma variável e, em seguida, retornar um valor nulo ou não nulo, dependendo do resultado da validação.

O run, por outro lado, é usado para executar um bloco de código em um objeto e referenciá-lo dentro desse bloco usando a palavra-chave this. A principal vantagem do run é que ele permite executar um bloco de código em um objeto e, em seguida, retornar o próprio objeto. Isso pode ser útil em casos em que precisamos realizar uma série de operações em um objeto e, em seguida, retorná-lo para que possa ser usado em outro lugar.

Exemplo:

```
val nome: String? = "joão"

// Usando let
val resultadoLet = nome?.let {
    it.toLowerCase().capitalize()
}

// Usando run
val resultadoRun = nome?.run {
	toLowerCase().capitalize()
}

println(resultadoLet) // Imprime "João"
println(resultadoRun) // Imprime "João"
```

Usando o run para inicializar uma variável com o resultado de um bloco de código:

```
fun main(){
 
    val listaNomes = mutableListOf<String>().run {
        add("João")
        add("Maria")
        add("Pedro")
        this
    }

    println(listaNomes)
```

Neste exemplo, usamos o run para executar um bloco de código em um objeto mutableListOf. Dentro desse bloco, adicionamos alguns nomes à lista e, em seguida, retornamos a própria lista usando a palavra-chave this. O resultado é que a variável listaNomes é inicializada com a lista que acabamos de criar e manipular.

Usando o run para retornar um valor calculado:

```
fun calcularIdadeEmDias(anos: Int, meses: Int, dias: Int): Int = run {
    val diasPorAno = 365
    val diasPorMes = 30
    anos * diasPorAno + meses * diasPorMes + dias
}

val idadeEmDias = calcularIdadeEmDias(20, 6, 15)
println("Minha idade em dias é $idadeEmDias")
```

Usando o run para executar um bloco de código em um objeto e retornar o próprio objeto:

```
data class Pessoa(val nome: String, val idade: Int)

val pessoa = Pessoa("João", 20).run {
    println("Nome: $nome, Idade: $idade")
    this
}
```