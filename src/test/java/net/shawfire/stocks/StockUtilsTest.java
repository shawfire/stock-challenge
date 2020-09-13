package net.shawfire.stocks;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

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
    // Create 0.1M data points
    int[] stockPrices = new int[100000];
    for (int i = 0; i < 100000; i++) {
      stockPrices[i] = (int)(Math.random() * 100);
    }

    // Time the even more optimized function
    Instant start = Instant.now();
    StockUtils.getMaxProfit(stockPrices);
    Instant end = Instant.now();
    Duration duration = Duration.between(start, end);
    System.out.println("getMaxProfit time for 0.1M data set: " + duration);
    // getMaxProfit time for 0.1M data set: PT4.454S
  }

}
