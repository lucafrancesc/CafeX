# CafeX Billing Service

## How to run

Required to run:

- **Java JDK** (v11, v17, or v21 recommended)
- **sbt** (Scala Build Tool v1.x+)

[info] welcome to sbt 1.12.13 (Ubuntu Java 21.0.11)


```bash
sbt "run \"Cola\" \"Steak Sandwich\""
```

It will return an ItemNotFound error for mispelling or missing items

```bash
sbt "run \"Generic Soda\" \"Steak Sandwich\""
```

Use the following to run the tests
```bash
sbt test
```