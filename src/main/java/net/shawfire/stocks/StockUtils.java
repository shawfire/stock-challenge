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
    int profit, prevSellIndex = 0;
    for (int buyIndex = 0; buyIndex < stockPrices.length - 2;) {

      //  Efficiency: Only find an new max sell price if
      //    the current max sell price (indexed by prevSellIndex) is no longer valid.
      if (prevSellIndex > buyIndex + 1) {
        profit = stockPrices[prevSellIndex] - stockPrices[buyIndex];
        maxProfit = Math.max(profit, maxProfit);
        logger.debug("getMaxProfit buy[{}]: {}, maxSell[{}] : {}, profit: {}", buyIndex,
                stockPrices[buyIndex], prevSellIndex, stockPrices[prevSellIndex], profit);
      } else {
        // Compare stockPrices[buyIndex] with all other subsequent stockPrices to find
        // the maxProfit
        for (int sellIndex = buyIndex + 2; sellIndex < stockPrices.length;) {
          profit = stockPrices[sellIndex] - stockPrices[buyIndex];
          maxProfit = Math.max(profit, maxProfit);
          logger.debug("getMaxProfit buy[{}]: {}, sell[{}] : {}, profit: {}", buyIndex,
                       stockPrices[buyIndex], sellIndex, stockPrices[sellIndex], profit);
          prevSellIndex = sellIndex;

          // Efficiency: find the next sell stockPrice that is greater than the
          // current stockPrices[buyIndex] value
          int prevSell = stockPrices[sellIndex];
          for (sellIndex += 1; sellIndex < stockPrices.length && prevSell > stockPrices[sellIndex]; sellIndex++)
            ;
        }
      }

      // Efficiency: find the next buy stockPrice that is less than the current
      // stockPrices[buyIndex] value
      int prevStart = stockPrices[buyIndex];
      for (buyIndex += 1; buyIndex < stockPrices.length - 2 && prevStart < stockPrices[buyIndex];
           buyIndex++)
        ;
    }
    logger.info("getMaxProfit maxProfit: {}", maxProfit);
    return maxProfit;
  }
}
