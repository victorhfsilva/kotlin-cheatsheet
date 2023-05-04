# Kotlin

## Map

### Mapa Imutável

```
// Cria um mapa imutável com pares de chave-valor
val myMap = mapOf(
    "key1" to 1,
    "key2" to 2,
    "key3" to 3
)

// Acesso aos valores no mapa
println(myMap["key1"]) // Output: 1
println(myMap["key4"]) // Output: null (a chave não existe no mapa)

// Iteração sobre os pares de chave-valor do mapa
for ((key, value) in myMap) {
    println("$key -> $value")
}
```

### Mapa Mutável

```
// Cria um mapa mutável vazio
val myMutableMap = mutableMapOf<String, Int>()

// Adiciona pares de chave-valor no mapa
myMutableMap["key1"] = 1
myMutableMap["key2"] = 2
myMutableMap["key3"] = 3

// Acesso aos valores no mapa
println(myMutableMap["key1"]) // Output: 1
println(myMutableMap["key4"]) // Output: null (a chave não existe no mapa)

// Iteração sobre os pares de chave-valor do mapa
for ((key, value) in myMutableMap) {
    println("$key -> $value")
}

// Remove um par de chave-valor do mapa
myMutableMap.remove("key2")

// Verifica se uma chave existe no mapa
if ("key3" in myMutableMap) {
    println("A chave 'key3' existe no mapa")
}
```

