# CafeX Billing Service

## How to run

Required to run:

- **Java JDK** (v11, v17, or v21 recommended)
- **sbt** (Scala Build Tool v1.x+)

[info] welcome to sbt 1.12.13 (Ubuntu Java 21.0.11)

Use the following to run the tests
```bash
sbt test
```

Feel free to check out the `error-handling` branch.
In this solution if we "scan" a non-existing item I made the call to return 0.
The other solution returns an Either + there is a small CLI.
