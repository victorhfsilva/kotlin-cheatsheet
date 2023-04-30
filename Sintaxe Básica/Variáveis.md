# Kotlin

## Variáveis

### Mutáveis

```
var <variável> : <tipo> = <valor>
```

### Imutáveis

```
val <variável> : <tipo> = <valor>
```

Caso o tipo não seja especificado o kotlin infere o tipo de acordo com o valor.

### Null Safety

Para que uma variável aceite o valor null é necessário declarar seu tipo seguido de ?

Exemplo:

```
var name : String? = "Victor Silva"
println(name?.length) //O posfixo ? é necessário em variáveis que permitem valores nulos

name = null

println(name?.length ?: 0)
println(name ?: "") //Caso null, não imprime nada.
```

