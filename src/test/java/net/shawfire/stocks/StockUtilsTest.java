package net.shawfire.stocks;

import org.apache.log4j.Level;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class StockUtilsTest {

  @Test(expected = java.lang.IllegalArgumentException.class)
  public void getMaxProfit_insufficientData() {
    int[] stockPrices = {0, 7};
    StockUtils.getMaxProfit(stockPrices);
  }

  @Test
  public void getMaxProfit_simpleCase() {
    int[] stockPrices = {10, 7, 5}; // one possible profit
    // possible profits:
    // buy sell profit
    // 10  5    -5
    Assert.assertEquals(-5, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit_nextCase() {
    int[] stockPrices = {10, 7, 5, 8};
    // possible profits:
    // buy sell profit
    // 10   5    -5     initial profit
    // 10   8    -2     higher sell price
    //  7   8     1     lower buy price
    Assert.assertEquals(1, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit3_givenCase() {
    int[] stockPrices = {10, 7, 5, 8, 11, 9}; // add lower price
    Assert.assertEquals(6, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit4_edgeCase() {
    int[] stockPrices = {1,2,3,4}; // find higher selling price
    Assert.assertEquals(3, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit5_edgeCase() {
    int[] stockPrices = {1,2,3,3}; // no higher selling price, no lower buy price
    Assert.assertEquals(2, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit6_edgeCase() {
    int[] stockPrices = {2,1,3,2,3}; // lower buy price, same selling price higher profit
    Assert.assertEquals(2, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void getMaxProfit7_edgeCase() {
    int[] stockPrices = {2,2,3,2,3,15,41,23,44,44,42,1,44}; // min buy price to close to end, not counted
    Assert.assertEquals(42, StockUtils.getMaxProfit(stockPrices));
  }

  @Test
  public void benchmark() {

    org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
    // Save the current log level
    Level logLevel = logger4j.getLevel();
    // For benchmarking use the lowest level of logging
    logger4j.setLevel(org.apache.log4j.Level.toLevel("ERROR"));

    // Create 0.1M data points
    final int dataSetSize = 100_000;
    final int priceRange = 100;
    int[] stockPrices = new int[dataSetSize];
    for (int i = 0; i < dataSetSize; i++) {
      stockPrices[i] = (int)(Math.random() * priceRange);
    }

    // Time the even more optimized function
    Instant start = Instant.now();
    StockUtils.getMaxProfit(stockPrices);
    Instant end = Instant.now();
    Duration duration = Duration.between(start, end);
    System.out.println("getMaxProfit time for 0.1M data set: " + duration);
    // getMaxProfit time for 0.1M data set: PT0.0025
    // After second optimization: getMaxProfit time for 0.1M data set: PT0.028S
    // After the first optimization: getMaxProfit time for 0.1M data set: PT0.059S
    // Initial version timing: getMaxProfit time for 0.1M data set: PT4.454S

    // Restore the log level
    logger4j.setLevel(logLevel);
  }

  @Test
  public void batchBenchmark() {
    org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
    // Save the current log level
    Level logLevel = logger4j.getLevel();
    // For benchmarking use the lowest level of logging
    logger4j.setLevel(org.apache.log4j.Level.toLevel("ERROR"));

    final int numberOfIterations = 1_000;
    // Market opens at 10am and closes at 4pm, that's  6 hrs * 60 minutes = 360 minutes
    // with one data point (stock price) every minute, a full day of data would be 360 prices.
    final int dataSetSize = 360;
    final int priceRange = 300;
    long duration = IntStream.range(0, numberOfIterations).mapToLong(x -> {
      int[] stockPrices = new int[dataSetSize];
      for (int i = 0; i < stockPrices.length; i++) {
        stockPrices[i] = (int)(Math.random() * priceRange);
      }
      // calculate only the time to run the function
      final long start = System.nanoTime();
      StockUtils.getMaxProfit(stockPrices);;
      final long end = System.nanoTime();
      return end - start;
    }).sum(); // sum all the individual execution times

    // to seconds divide by 1_000_000_000
    double durationInSecounds = duration / 1_000_000_000.0;
    System.out.println("getMaxProfit time for " + numberOfIterations + " calls of " +
            dataSetSize + " data set: " + durationInSecounds + " seconds");

    // Restore the log level
    logger4j.setLevel(logLevel);
  }

}
