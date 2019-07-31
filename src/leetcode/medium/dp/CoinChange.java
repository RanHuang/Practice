package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change-2
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

    /**
     * 动态规划法，状态转移方程: d[j] = min(d[j-coins[k]]) + 1,其中0<=j<=amount, 0<=k<coins.length
     *
     * @param coins
     * @param amount
     * @return
     */
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

    @Test
    public void testChange() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        Assert.assertEquals(4, this.change(amount, coins));
    }

    static int amount = 0;

    /**
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        this.changeBackTrack(amount, coins);
        return amount;
    }



    /**
     * 使用回溯方法(存在重复方案)
     *
     * @param left
     * @param coins
     * @return
     */
    private void changeBackTrack(int left, int[] coins) {
        if (left < 0) {
            return;
        } else if (left == 0) {
            amount++;
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            this.changeBackTrack(left - coins[i], coins);
        }
    }
}
