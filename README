
## QtJambi for Scala
Codename QtScampi, is an attempt to make QtJambi work better with scala, first and foremost to improve the way you connect signals and slot. Signals can be connected to any scala function and is completely type safe. *Note that this is still highly experimental.* You might experience some threading issues as connection types are not implemented.


#### Download
Go to [Downloads section](https://github.com/takoi/qtjambi-for-scala/downloads) and get patched QtJambi, *qtjambi-4.7.2.jar* and *qtjambi-extensions-scala-4.7.2.jar*


#### Connecting
```val btn = new QPushButton("")
val slot = () => println("slot")
btn.released.connect(slot)
```

#### Connecting to method
```def slot() = { }
btn.released.connect(slot _)
```

#### Disconnecting
The connect method will return the function which was connected to. You _must_ pass this as the argument to disconnect. disconnect returns false if the connection does not exist.

```val f = btn.released.connect(slot _)
btn.released.disconnect(f)
```

#### Custom signals
Classes containing custom signal must inherit QSignalEmitter.

```class Foo extends QSignalEmitter {
  val bar = new Signal1[Int]
}```
