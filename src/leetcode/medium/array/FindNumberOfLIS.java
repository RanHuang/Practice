package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * @author nick
 * @date 2019-08-01 星期四 07:43
 **/
public class FindNumberOfLIS {
    @Test
    public void testLengthOfLIS() {
        Assert.assertEquals(1, this.lengthOfLIS(new int[]{1}));
        Assert.assertEquals(1, this.lengthOfLIS(new int[]{2, 2, 2, 2, 2}));
        Assert.assertEquals(4, this.lengthOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    /**
     * 求取最长递增子序列的长度
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int length = nums.length;
        if (length < 2) {
            return length;
        }

        // 状态转移表
        int[][] dp = new int[length][length];
        // 初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i <= length - 2; i++) {
            for (int j = i + 1; j <= length - 1; j++) {
                int max = dp[i][i];
                for (int k = i; k < j; k++) {
                    if (nums[k] < nums[j]) {
                        max = Math.max(max, dp[i][k] + 1);
                    } else {
                        max = Math.max(max, dp[i][k]);
                    }
                }
                dp[i][j] = max;
            }
        }
        return dp[0][length - 1];
    }

    @Test
    public void testFindNumberOfLIS() {
        Assert.assertEquals(1, this.findNumberOfLIS(new int[]{1}));
        Assert.assertEquals(1, this.findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
        Assert.assertEquals(4, this.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    public int findNumberOfLIS(int[] nums) {

        int cnt = 0;
        return cnt;
    }
}
