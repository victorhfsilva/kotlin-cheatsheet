# Kotlin

## Higher Order Functions Parameters

```
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x,y)
}

fun calculate(x: Double, y: Double, operation: (Double, Double) -> Double): Double {
    return operation(x,y)
}

fun sum(x: Int, y: Int) = x + y

fun subtraction(x: Int, y: Int) = x - y

fun multiplication(x: Int, y: Int) = x * y

fun main() {
    val sum = calculate(12, 5, ::sum)
    println("Sum: $sum")
    
    val subtraction = calculate(12, 5, ::subtraction)
    println("Subtraction: $subtraction")
    
    val multiplication = calculate(12, 5, ::multiplication)
    println("Multiplication: $multiplication")
    
    val division = calculate(12.toDouble(), 5.toDouble()) {x, y -> x / y}
    println("Division: $division")
}
```

## Higher Order Functions Returning

```
fun multiplyBy(factor: Int): (Int) -> Int {
    return { input -> input * factor }
}

fun main() {
    val multiplyByTwo = multiplyBy(2)
    val multiplyByThree = multiplyBy(3)
    val multiplyByFour = multiplyBy(4)

    println("Multiply 5 by 2: ${multiplyByTwo(5)}")
    println("Multiply 7 by 3: ${multiplyByThree(7)}")
    println("Multiply 8 by 4: ${multiplyByFour(8)}")
}
```

