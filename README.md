# Crafting an interpreter for fun

~~I'm following~~ I followed [this book](https://craftinginterpreters.com/)
to build my interpreter for fun.

The language is called **Lox** and has the following features:

* it's dynamically typed
* it has functions with parameters and return values
* control flow
* while/for loops
* classes (inheritance is supported)
* it's *awfully slow*
* and more...

[Here are some examples](#examples)

## Structure

The **main** branch of this repo contains the code mostly unaltered
from the authors'.

The other branches contain solutions to challenges from the book or experiments
that I found interesting.

## Usage

* `git clone https://github.com/sus-domesticus/crafting-interpreters && cd crafting-interpreters`
* `git checkout` a branch
* `./gradlew run --console=plain --args="$(pwd)/example.lox"`
(use `.\gradlew.bat` on Windows and adjust the command)

## Examples

```Lox
// Example with classes and inheritance

class Root {
  printSomething() {
      print "I am root"; // prints to STDOUT
  }
}

class Child < Root {
  printSomething() {
      // `super` is used to reuse parent methods
      super.printSomething();
      print "I am child";
  }
}

var childInstance = Child();
childInstance.printSomething();
// Output:
// I am root
// I am child
```

```Lox
// Example with a prime number checker

// Lox doesn't have the modulus operator :(
fun modulus(n, m) {
  var tmp = n; // I don't want to mutate `n`
  while (tmp < 0) {
    tmp = tmp + m;
  }
  while (tmp >= m) {
    tmp = tmp - m;
  }
  return tmp;
}

fun isPrime(n) {
  if (n < 2) {
      return false;
  }
  for (var d = 2; d * d <= n; d = d + 1) {
    if (modulus(n, d) == 0) {
      return false;
    }
  }
  return true;
}

print isPrime(193); // true
print isPrime(44); // false
print isPrime(10924875); // false
```
