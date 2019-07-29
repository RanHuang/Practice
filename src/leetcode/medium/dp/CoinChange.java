package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-29 星期一 20:13
 **/
public class CoinChange {
    @Test
    public void testCoinChange() {
        Assert.assertEquals(20, this.coinChange(new int[]{186, 419, 83, 408}, 6249));
        Assert.assertEquals(3, this.coinChange(new int[]{1, 2, 5}, 11));
        Assert.assertEquals(-1, this.coinChange(new int[]{2}, 3));
        Assert.assertEquals(0, this.coinChange(new int[]{1}, 0));
    }

    public int coinChange(int[] coins, int amount) {
        int[] d = new int[amount + 1];
        Arrays.fill(d, -1);
        d[0] = 0;

        for (int j = 1; j <= amount; j++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if (j - coins[i] >= 0 && d[j - coins[i]] >= 0) {
                    min = Math.min(d[j - coins[i]], min);
                }
            }
            d[j] = min + 1;
        }

        return d[amount] >= 0 ? d[amount] : -1;
    }
}
