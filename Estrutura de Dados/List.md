# Kotlin

## List

### Lista Imutável

Não é possível adicionar, remover ou alterar elementos depois de criada.

```
val numeros: List<Int> = listOf(1, 2, 3, 4, 5)
println(numeros) // Output: [1, 2, 3, 4, 5]
```

### Lista Mutável

#### Adicionando valores

```
val nomes : MutableList<Int> = mutableListOf("João", "Maria", "Pedro")
nomes.add("Ana")
println(nomes) // Output: [João, Maria, Pedro, Ana]
```

#### Removendo valores

```
val animais = mutableListOf("cachorro", "gato", "papagaio")
animais.remove("gato")
println(animais) // Output: [cachorro, papagaio]
```

O tipo da variável List ou MutableList é inferida pelo Kotlin caso não seja declarada.

#### Acessando elementos

```
val frutas = listOf("maçã", "banana", "laranja", "uva")
println(frutas[0]) // Output: maçã
println(frutas[2]) // Output: laranja
```

