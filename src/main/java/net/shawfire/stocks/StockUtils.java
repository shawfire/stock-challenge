package net.shawfire.stocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class StockUtils {

  private static Logger logger = LoggerFactory.getLogger(StockUtils.class);

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
    logger.info("getMaxProfit({})", Arrays.toString(stockPrices));
    if (stockPrices.length < 3) {
      logger.error("There must be at least three elements in the array in order to calculate a profit.");
      throw new IllegalArgumentException(
          "There must be at least three elements in the array in order to calculate a profit.");
    }
    int maxProfit = Integer.MIN_VALUE;
    int profit, prevJ = -1;
    for (int i = 0; i < stockPrices.length - 2;) {

      //  Efficiency: Only find an new max sell price if the current maxProfit
      //  is no longer valid.
      //    Where prevJ is the index of the sell price used in the last
      //    maxProfit calculation.
      if (prevJ != -1 && prevJ > i + 1) {
        profit = stockPrices[prevJ] - stockPrices[i];
        maxProfit = Math.max(profit, maxProfit);
        logger.debug("getMaxProfit buy[{}]: {}, maxSell[{}] : {}, profit: {}", i,
                stockPrices[i], prevJ, stockPrices[prevJ], profit);
      } else {
        // Compare stockPrices[i] with all other subsequent stockPrices to find
        // the maxProfit
        for (int j = i + 2; j < stockPrices.length;) {
          profit = stockPrices[j] - stockPrices[i];
          maxProfit = Math.max(profit, maxProfit);
          logger.debug("getMaxProfit buy[{}]: {}, sell[{}] : {}, profit: {}", i,
                       stockPrices[i], j, stockPrices[j], profit);
          prevJ = j;

          // Efficiency: find the next sell stockPrice that is greater than the
          // current stockPrices[i] value
          int prevSell = stockPrices[j];
          for (j += 1; j < stockPrices.length && prevSell > stockPrices[j]; j++)
            ;
        }
      }

      // Efficiency: find the next buy stockPrice that is less than the current
      // stockPrices[i] value
      int prevStart = stockPrices[i];
      for (i += 1; i < stockPrices.length - 2 && prevStart < stockPrices[i];
           i++)
        ;
    }
    logger.info("getMaxProfit maxProfit: {}", maxProfit);
    return maxProfit;
  }
}
