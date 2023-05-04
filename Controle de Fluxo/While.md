# Kotlin

## While

Usando o loop while para imprimir números de 1 a 5.

```
var i = 1
while (i <= 5) {
    println(i)
    i++
}
```

Usando o loop while para encontrar o primeiro número da sequência de Fibonacci maior que 1000.

```
var a = 0
var b = 1
var c = 1
while (c <= 1000) {
    a = b
    b = c
    c = a + b
}
println("O primeiro número da sequência de Fibonacci maior que 1000 é $c")
```

## Do While

Usando o loop do-while para ler a entrada do usuário até que seja fornecida uma resposta válida.

```
var input: String?
do {
    print("Por favor, digite S ou N: ")
    input = readLine()
} while (input != "S" && input != "N")
```

Usando o loop do-while para jogar o jogo da adivinhação.

```
val number = (1..100).random()
var guess: Int?
var attempts = 0
do {
    print("Adivinhe o número (1-100): ")
    guess = readLine()?.toIntOrNull()
    attempts++
    if (guess != null) {
        if (guess < number) {
            println("Muito baixo, tente novamente")
        } else if (guess > number) {
            println("Muito alto, tente novamente")
        } else {
            println("Parabéns, você adivinhou o número em $attempts tentativas!")
        }
    }
} while (guess != number)
```



