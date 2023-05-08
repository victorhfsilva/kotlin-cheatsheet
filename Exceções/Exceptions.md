# Kotlin

## Lançando Exceções

Exceção de divisão por zero usando a palavra-chave throw:

```
fun divide(numerator: Int, denominator: Int): Int {
    if (denominator == 0) {
        throw ArithmeticException("Divisão por zero!")
    }
    return numerator / denominator
}
```

Exceção personalizada que pode ser lançada quando o usuário fornece um valor inválido:

```
class InvalidInputException(message: String) : Exception(message)

fun processInput(input: String) {
    if (input.isEmpty()) {
        throw InvalidInputException("Entrada vazia!")
    }
    // fazer algo com a entrada
}
```

Exceção de índice inválido ao acessar um índice fora do intervalo de uma matriz:

```
fun accessArray(array: Array<String>, index: Int) {
    if (index < 0 || index >= array.size) {
        throw IndexOutOfBoundsException("Índice inválido!")
    }
    println(array[index])
}
```

## Capturando exceções

Capturando a exceção de divisão por zero usando a cláusula try-catch:

```
try {
    val result = divide(10, 0)
    println("Resultado: $result")
} catch (e: ArithmeticException) {
    println("Erro: ${e.message}")
}
```

Capturando uma exceção personalizada que pode ser lançada quando o usuário fornece um valor inválido:

```
try {
    processInput("")
} catch (e: InvalidInputException) {
    println("Erro: ${e.message}")
}
```

Capturando uma exceção de índice inválido ao acessar um índice fora do intervalo de uma matriz:

```
try {
    val array = arrayOf("A", "B", "C")
    accessArray(array, 5)
} catch (e: IndexOutOfBoundsException) {
    println("Erro: ${e.message}")
}
```

## Finally

Em Kotlin, o bloco finally é usado para definir código que deve ser executado independentemente de ter ocorrido uma exceção ou não. O bloco finally é frequentemente usado para liberar recursos, como fechar arquivos ou conexões de banco de dados, que foram abertos dentro do bloco try.

```
fun readDataFromFile(filename: String): String {
    val file = File(filename)
    try {
        val input = BufferedReader(FileReader(file))
        return input.readLine()
    } catch (e: IOException) {
        println("Erro ao ler o arquivo: ${e.message}")
    } finally {
        file.delete() // exclui o arquivo independentemente de ter ocorrido uma exceção ou não
    }
    return ""
}
```

## Throws

Caso uma exceção não seja capturada é uma boa prática utilizar a anotação Throws para identificar que a função lança uma exceção.

```
@Throws(IOException::class)
fun readDataFromFile(filename: String): String {
    val file = File(filename)
    val input = BufferedReader(FileReader(file))
    return input.readLine()
}

fun processData() {
    try {
        val data = readDataFromFile("data.txt")
        // processa os dados lidos do arquivo
    } catch (e: IOException) {
        println("Erro ao ler o arquivo: ${e.message}")
        throw e // propagando a exceção para um método superior
    }
}
```