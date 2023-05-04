# Kotlin

## Set

A principal diferença entre um Set e uma Lista (List) é que um Set armazena um conjunto de elementos únicos, enquanto uma Lista pode conter elementos duplicados. Isso significa que em um Set, cada elemento é único e não há repetição, enquanto em uma Lista, é possível ter a mesma entrada várias vezes.

Outra diferença importante é que em um Set, os elementos não possuem uma ordem específica, enquanto em uma Lista, a ordem dos elementos é preservada. Isso significa que, se a ordem em que os elementos são inseridos em uma Lista é importante para a lógica do programa, então uma Lista é mais adequada do que um Set.

Além disso, um Set geralmente é mais eficiente do que uma Lista em determinadas operações, como verificar se um elemento está contido no conjunto (contains) ou verificar a interseção entre dois conjuntos. Isso ocorre porque um Set usa uma tabela hash para armazenar os elementos, o que permite uma busca muito rápida e eficiente pelos elementos.

Por outro lado, uma Lista pode ser mais adequada em situações em que é necessário manter a ordem dos elementos ou acessar elementos específicos por meio de um índice.

### Set Imutável

```
val numeros = setOf(1, 2, 3, 4, 5)
println(numeros) // Output: [1, 2, 3, 4, 5]
```

### Set Mutável

#### Adição

```
val cores = mutableSetOf("vermelho", "verde", "azul")
cores.add("amarelo")
println(cores) // Output: [vermelho, verde, azul, amarelo]
```

Quando um elemento é adicionado em um Set usando o método add(), o método retorna true se o elemento ainda não existia no Set e foi adicionado com sucesso, ou false se o elemento já existia no Set e não foi adicionado novamente.

```
val mySet = mutableSetOf("apple", "banana", "orange")
val added = mySet.add("kiwi")
println(added) // Output: true, pois "kiwi" foi adicionado com sucesso ao Set
```

```
val mySet = mutableSetOf("apple", "banana", "orange")
val added = mySet.add("banana")
println(added) // Output: false, pois "banana" já existia no Set e não foi adicionado novamente
```

#### Acesso

```
val vogais = setOf("a", "e", "i", "o", "u")
println(vogais.contains("a")) // Output: true
println(vogais.contains("b")) // Output: false
```

#### Remoção

```
val frutas = mutableSetOf("maçã", "banana", "laranja", "uva")
frutas.remove("banana")
println(frutas) // Output: [maçã, laranja, uva]
```


