# Provide a solution to the `stock-challenge`<br/> in Java8 using `TDD` (`T`est `D`riven `D`envelopment)

`Click` on list items to expand and<br/>
`Click` on hyperlinks for more information.

## Challenge Details

<details><summary>Stock Challenge - getMaxProfit</summary>
<br/>
This technical challenge is as much about clean, simple solution and code as it is about problem solving.
<br/>
<br/>
Suppose we could access yesterday's stock prices as a list, where:
<br/>
<br/>
The indices are the time in minutes past trade opening time, which was 10:00am local time.
The values are the price in dollars of the stock at that time.

So if the stock cost `$5` at `11:00am`, `stock_prices_yesterday[60] = 5`.

<br/>
Write an efficient function that takes an array of stock prices and returns the best profit could have been made from 1 purchase and 1 sale of 1 stock.

For example:

```java
int[] stockPrices = {10, 7, 5, 8, 11, 9};

Assert.assertEquals (6, getMaxProfit(stockPrices)); // returns 6 (buy at $5 sell at $11)
```

You must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).

Expectations

- Implement a solution in Java.
- Prove it works by creating unit tests that test the possible scenarios that the numbers could present.
- Include any comments that you think will be relevant to provide any context around the approach taken / solution developed.

</details>
<br/>

## Building and Testing Instructions

<details><summary>Prerequisites on a mac</summary>
<br/>

- Java 8 sdk
- Maven 3

```bash
$ java -version
java version "1.8.0_251"
Java(TM) SE Runtime Environment (build 1.8.0_251-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.251-b08, mixed mode)

$ export JAVA_HOME=`/usr/libexec/java_home -v "1.8*"`

$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 1.8.0_261, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_261.jdk/Contents/Home/jre
Default locale: en_AU, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
```

- References
  - [Download latest java 8 sdk (jdk-8u261-macosx-x64.dmg)](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
  - [Installing Apache Maven](https://maven.apache.org/install.html)

</details>

<details><summary>Get repo and test</summary>

- Clone repository
- Maven package to build, package and test

```bash
$ git clone https://github.com/shawfire/stock-challenge.git

$ cd stock-challenge

$ mvn test
[INFO] Scanning for projects...
[INFO]
[INFO] ----------------< net.shawfire.stocks:stock-challenge >-----------------
[INFO] Building stock-challenge 1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ stock-challenge ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ stock-challenge ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ stock-challenge ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ stock-challenge ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ stock-challenge ---
[INFO] Surefire report directory: /Users/johnshaw/dev/stock-challenge/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running net.shawfire.stocks.StockUtilsTest
getMaxProfit time for 0.1M data set: PT0.002039S
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.083 sec

Results :

Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.275 s
[INFO] Finished at: 2020-09-14T07:13:07+10:00
[INFO] ------------------------------------------------------------------------
```

</details>

## Documenting the approach taken

<details><summary>Approach</summary>

- Used a `TDD` (`T`est `D`riven `D`esign) Approach within IntelliJ IDEA
- Used `Java 8` and `Junit 4`
- Used Maven directory structure and a `pom.xml` to support command line build and test.
- Each feature and capability has a corresponding test due to the `TDD` approach that has been followed.
</details>

<details><summary>Design Brief</summary>

Find the maximum profit given a list of consecutive stock prices.

- Each element in the stockPrices array represents the dollar value at 10am plus the index value.
- The profit can only be calculated when the buy and sell are separated by more than one minute.
- The profit can't be calculated from consecutive stock prices.
- There must be a minimum of 3 values in the array in order to calculate a profit.

</details>

<details><summary>Making the Solution efficient</summary>

- By finding the next buy stockPrice that is less than the last stockPrice used to calculate the last maxProfit. The efficiency gained was a timing of 0.059 seconds
  as apposed to 4.454 seconds for the initial version for a data set of 100,000 prices.
- By finding the next sell price that is higher than the last stockPrice used to calculate the last maxProfit. The efficiency gained was a timing of 0.0254 seconds as apposed to the above timing on a set of 100,000 prices.
- By reusing the maxSalePrice while it is still current when the maxBuyPrice has changed. The efficiency gained was a timing of 0.0025 seconds as apposed to the above timing on a set of 100,000 prices.

</details>

<details><summary>References</summary>

- [junit4 docs](https://junit.org/junit4/)
</details>
