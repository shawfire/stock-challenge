package net.shawfire.stocks;

public class StockUtils {

  /**
   * Find the maximum profit given a list of consecutive stock prices.
   * Each element in the stockPrices array represents the dollar value at 10am
   * plus the index value. The profit can only be calculated when the buy and
   * sell are separated by more than one second. The profit can't be calculated
   * from consecutive stock prices. There must be a minimum of 3 values in the
   * array in order to calculate a profit.
   *
   * @param stockPrices is a list of stock prices
   * @return the maximum profit from one purchase and sale give the stockPrices
   */
  public static int getMaxProfit(int[] stockPrices) {
    if (stockPrices.length < 3) {
      throw new IllegalArgumentException(
              "There must be at least three elements in the array in order to calculate a profit.");
    }
    int maxProfit = Integer.MIN_VALUE;
    int profit;
    for (int i = 0; i < stockPrices.length - 2;) {
      // Compare stockPrices[i] with all other subsequent stockPrices to find
      // the maxProfit
      for (int j = i + 2; j < stockPrices.length; j++) {
        profit = stockPrices[j] - stockPrices[i];
        maxProfit = Math.max(profit, maxProfit);
      }
      // Efficiency: find the next buy stockPrice that is less than the current
      // stockPrices[i] value
      int prevStart = stockPrices[i];
      for (i += 1; i < stockPrices.length - 2 && prevStart < stockPrices[i];
           i++)
        ;
    }
    return maxProfit;
  }
}
