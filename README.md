# Provide a solution to the `stock-challenge`<br/> in Java8 using `TDD` (`T`est `D`riven `D`evelopment)

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
getMaxProfit time for 1000 calls of 360 data set: 0.042877489 seconds
0    [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([10, 7, 5, 8, 11, 9])
0    [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[2]: 5, sell[4] : 11, maxProfit: 6
getMaxProfit time for 0.1M data set: PT0.004254S
16   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([2, 2, 3, 2, 3, 15, 41, 23, 44, 44, 42, 1, 44])
16   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[3]: 2, sell[12] : 44, maxProfit: 42
17   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([2, 1, 3, 2, 3])
17   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[1]: 1, sell[4] : 3, maxProfit: 2
18   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([10, 7, 5])
18   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 10, sell[2] : 5, maxProfit: -5
18   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([1, 2, 3, 3])
18   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 1, sell[3] : 3, maxProfit: 2
19   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([1, 2, 3, 4])
19   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 1, sell[3] : 4, maxProfit: 3
19   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([10, 7, 5, 8])
20   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[1]: 7, sell[3] : 8, maxProfit: 1
20   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([0, 7])
20   [main] ERROR net.shawfire.stocks.StockUtils  - There must be at least three elements in the array in order to calculate a profit.
22   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit([488, 59, 103, 412, 82, 483, 236, 171, 289, 303, 196, 341, 486, 322, 362, 136, 113, 146, 247, 41, 222, 106, 32, 477, 420, 215, 284, 299, 339, 493, 306, 128, 351, 481, 158, 333, 4, 486, 407, 258, 42, 94, 59, 104, 375, 253, 381, 239, 56, 257, 26, 9, 105, 255, 410, 37, 475, 464, 262, 334, 53, 94, 107, 475, 281, 85, 165, 472, 405, 105, 300, 434, 299, 388, 137, 21, 35, 365, 406, 170, 90, 221, 337, 34, 64, 386, 140, 79, 321, 200, 178, 273, 195, 410, 404, 329, 243, 373, 493, 496, 29, 343, 366, 448, 51, 450, 418, 381, 193, 472, 321, 26, 18, 336, 168, 349, 235, 257, 74, 379, 421, 292, 499, 168, 362, 117, 218, 92, 398, 211, 287, 326, 240, 214, 275, 8, 12, 94, 270, 316, 159, 427, 331, 364, 396, 453, 124, 72, 56, 275, 379, 377, 29, 485, 177, 437, 433, 206, 257, 220, 247, 491, 178, 174, 416, 289, 140, 468, 284, 123, 179, 125, 254, 457, 226, 171, 233, 196, 196, 301, 231, 96, 325, 413, 422, 404, 450, 247, 62, 376, 233, 249, 385, 407, 114, 209, 485, 371, 361, 125, 156, 389, 115, 218, 372, 113, 251, 443, 311, 306, 154, 348, 321, 368, 190, 102, 252, 175, 267, 18, 303, 50, 304, 439, 396, 363, 322, 347, 70, 293, 105, 156, 15, 482, 20, 187, 163, 349, 118, 400, 140, 111, 73, 88, 460, 87, 301, 320, 29, 182, 342, 63, 356, 15, 140, 52, 360, 7, 382, 165, 274, 337, 399, 171, 395, 45, 423, 2, 344, 227, 130, 320, 30, 118, 470, 434, 193, 85, 191, 329, 227, 436, 326, 339, 437, 189, 117, 251, 491, 388, 443, 440, 78, 283, 354, 389, 144, 211, 174, 331, 132, 245, 438, 4, 292, 235, 82, 443, 444, 134, 386, 13, 16, 79, 113, 337, 277, 13, 132, 121, 403, 233, 52, 314, 370, 82, 14, 452, 215, 162, 315, 487, 300, 289, 421, 360, 61, 114, 255, 40, 104, 188, 318, 247, 114, 9, 263, 85, 242, 465, 44, 319, 491, 359, 109, 85, 100, 154, 331, 346])
23   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[2] : 103, profit: -385, maxProfit: -385
23   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[3] : 412, profit: -76, maxProfit: -76
24   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[5] : 483, profit: -5, maxProfit: -5
24   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[12] : 486, profit: -2, maxProfit: -2
25   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[29] : 493, profit: 5, maxProfit: 5
25   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[98] : 493, profit: 5, maxProfit: 5
25   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[99] : 496, profit: 8, maxProfit: 8
25   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[0]: 488, sell[122] : 499, profit: 11, maxProfit: 11
25   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[1]: 59, maxSell[122] : 499, profit: 440, maxProfit: 440
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[19]: 41, maxSell[122] : 499, profit: 458, maxProfit: 458
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[22]: 32, maxSell[122] : 499, profit: 467, maxProfit: 467
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[36]: 4, maxSell[122] : 499, profit: 495, maxProfit: 495
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[269] : 227, profit: 225, maxProfit: 495
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[271] : 320, profit: 318, maxProfit: 495
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[274] : 470, profit: 468, maxProfit: 495
26   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[288] : 491, profit: 489, maxProfit: 495
27   [main] DEBUG net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[352] : 491, profit: 489, maxProfit: 495
27   [main] INFO  net.shawfire.stocks.StockUtils  - getMaxProfit buy[267]: 2, sell[352] : 491, maxProfit: 495
getMaxProfit time for 1 calls of 360 data set: 0.005335495 seconds
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.254 sec

Results :

Tests run: 11, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.750 s
[INFO] Finished at: 2020-09-17T08:03:12+10:00
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

## Efficiencies made

<details><summary>Making the Solution efficient</summary>

- By finding the next buy stockPrice that is less than the last stockPrice used to calculate the last maxProfit. The efficiency gained was a timing of 0.059 seconds
  as apposed to 4.454 seconds for the initial version for a data set of 100,000 prices.
- By finding the next sell price that is higher than the last stockPrice used to calculate the last maxProfit. The efficiency gained was a timing of 0.0254 seconds as apposed to the above timing on a set of 100,000 prices.
- By reusing the maxSalePrice while it is still current when the maxBuyPrice has changed. The efficiency gained was a timing of 0.0025 seconds as apposed to the above timing on a set of 100,000 prices.

</details>

## Logging

<details><summary>Logging</summary>

- Added logging for monitoring.
- Change logging for tests in `src/test/resources/log4j.properties`
- The logging slows down the benchmark to `0.0133` seconds. Which is still lower than all the timing; except for the last efficiency that was made (which was `0.0025` seconds). Apart from monitoring logging is great aid for performance tuning based analysis of the logs.
- The benchmark test switches logging to `ERROR`, then restores the logger level.
- From analyzing the logs an efficiency of `0.004254S` seconds as apposed to `0.0133` seconds with logging set to `ERROR`. The new sellPrice is only taken into account if it is greater than the previous max sell price as the function is iterating.

```bash
# Set root logger level to INFO, DEBUG or ERROR and its only appender to A1.
log4j.rootLogger=INFO, A1
```

</details>

<details><summary>References</summary>

- [junit4 docs](https://junit.org/junit4/)
</details>
