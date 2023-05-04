# Kotlin

## If

```
val numero = 10
if (numero > 0) {
    println("O número é positivo")
} else if (numero < 0) {
    println("O número é negativo")
} else {
    println("O número é zero")
}
```

## If Ternário

```
val idade = 18
val mensagem = if (idade >= 18) "Pode votar" else "Não pode votar"
println(mensagem)
```

Segue um exemplo de função em Kotlin utilizando o operador ternário:

```
fun maiorValor(a: Int, b: Int): Int = if (a > b) a else b
```

