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
    int profit, prevSellIndex = 0, prevBuyIndex = 0;
    for (int buyIndex = 0; buyIndex < stockPrices.length - 2;) {

      //  Efficiency: Only find an new max sell price if
      //    the current max sell price (indexed by prevSellIndex) is no longer valid.
      if (prevSellIndex > buyIndex + 1) {
        profit = stockPrices[prevSellIndex] - stockPrices[buyIndex];
        maxProfit = Math.max(profit, maxProfit);
        logger.debug("getMaxProfit buy[{}]: {}, maxSell[{}] : {}, profit: {}, maxProfit: {}", buyIndex,
                stockPrices[buyIndex], prevSellIndex, stockPrices[prevSellIndex], profit, maxProfit);
        prevBuyIndex = buyIndex;
      } else {
        // Compare stockPrices[buyIndex] with all other subsequent stockPrices to find
        // the maxProfit
        for (int sellIndex = buyIndex + 2; sellIndex < stockPrices.length;) {
          profit = stockPrices[sellIndex] - stockPrices[buyIndex];
          maxProfit = Math.max(profit, maxProfit);
          logger.debug("getMaxProfit buy[{}]: {}, sell[{}] : {}, profit: {}, maxProfit: {}", buyIndex,
                       stockPrices[buyIndex], sellIndex, stockPrices[sellIndex], profit, maxProfit);
          prevSellIndex = sellIndex;
          prevBuyIndex = buyIndex;

          // Efficiency: find the next sell stockPrice that is greater than the
          // current stockPrices[buyIndex] value
          int prevSell = stockPrices[prevSellIndex];
          do {
            sellIndex += 1;
          } while (sellIndex < stockPrices.length && stockPrices[sellIndex] < prevSell);
        }
      }

      // Efficiency: find the next buy stockPrice that is less than the current
      // stockPrices[buyIndex] value
      int prevStart = stockPrices[buyIndex];
      do {
        buyIndex += 1;
      } while (buyIndex < stockPrices.length - 2 && prevStart < stockPrices[buyIndex]);
    }
    logger.info("getMaxProfit buy[{}]: {}, sell[{}] : {}, maxProfit: {}", prevBuyIndex,
            stockPrices[prevBuyIndex], prevSellIndex, stockPrices[prevSellIndex], maxProfit);
    return maxProfit;
  }
}
