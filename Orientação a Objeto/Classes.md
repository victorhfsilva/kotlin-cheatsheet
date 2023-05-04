# Kotlin

## Classes

### Data Class

Em Kotlin, as data classes são uma maneira conveniente de definir uma classe que é usada principalmente para armazenar dados. As data classes fornecem funcionalidades úteis, como a geração automática de funções equals(), hashCode(), toString(), copy(), etc.

```
data class Pessoa(val nome: String, val idade: Int)
```

### Enum Class

Em Kotlin, as enum classes são usadas para representar um conjunto fixo de valores ou constantes nomeadas. As enum classes são úteis quando você precisa de um conjunto limitado de valores para um determinado tipo de dados.

```
enum class DiaDaSemana {
    DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
}
```

#### Enum class com propriedades e métodos


```
enum class Cor(val codigo: Int, val nome: String) {
    VERMELHO(0xFF0000, "Vermelho"),
    VERDE(0x00FF00, "Verde"),
    AZUL(0x0000FF, "Azul");

    fun rgb(): Int = codigo
}
```

#### Enum class com implementação de interface

```
interface OpcaoSelecionavel {
    fun selecionar()
}

enum class MenuPrincipal : OpcaoSelecionavel {
    INICIO {
        override fun selecionar() {
            println("Você selecionou a opção Início")
        }
    },
    PERFIL {
        override fun selecionar() {
            println("Você selecionou a opção Perfil")
        }
    },
    CONFIGURACOES {
        override fun selecionar() {
            println("Você selecionou a opção Configurações")
        }
    }
}
```

### Sealed Class

Em Kotlin, uma sealed class é uma classe abstrata que permite uma hierarquia de classes limitada a um conjunto fixo de subtipos. Ela é usada principalmente para representar uma hierarquia de estados ou resultados, onde os subtipos possíveis são conhecidos de antemão e finitos.

```
sealed class Result {
    class Success(val data: String) : Result()
    class Error(val message: String) : Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Result.Success -> println("Success: ${result.data}")
        is Result.Error -> println("Error: ${result.message}")
    }
}

val successResult = Result.Success("Data received")
val errorResult = Result.Error("Connection failed")

handleResult(successResult)
handleResult(errorResult)
```


