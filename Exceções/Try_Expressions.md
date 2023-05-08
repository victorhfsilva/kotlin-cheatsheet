# Kotlin

## Try Expressions

Capturando uma exceção de divisão por zero e retornando um valor padrão:

```
fun divide(numerator: Int, denominator: Int): Int {
    return try {
        numerator / denominator
    } catch (e: ArithmeticException) {
        println("Erro: ${e.message}")
        0 // retorna 0 como um valor padrão
    }
}
```

Lendo o conteúdo de um arquivo e retornando uma string vazia se ocorrer uma exceção:

```
fun readDataFromFile(filename: String): String {
    val file = File(filename)
    return try {
        val input = BufferedReader(FileReader(file))
        input.readLine()
    } catch (e: IOException) {
        println("Erro ao ler o arquivo: ${e.message}")
        "" // retorna uma string vazia como um valor padrão
    } finally {
        file.delete()
    }
}
```

Capturando uma exceção de índice inválido e retornando um valor padrão:

```
fun accessArray(array: Array<String>, index: Int): String {
    return try {
        array[index]
    } catch (e: IndexOutOfBoundsException) {
        println("Erro: ${e.message}")
        "Valor padrão" // retorna um valor padrão se ocorrer uma exceção de índice inválido
    }
}
```
