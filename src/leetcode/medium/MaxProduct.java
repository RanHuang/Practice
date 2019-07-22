package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 152. 乘积最大子序列
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）
 *
 * @author nick
 * @date 2019-07-21 星期日 22:49
 **/
public class MaxProduct {
    @Test
    public void testMaxProduct() {
        int[] nums = {2, 3, -2, 4};
        Assert.assertEquals(6, this.maxProduct(nums));
    }

    /**
     * 动态规划求解乘积最大子序列
     *   由于存在负数，需要翻转最大/小结果
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int minVal = 1;
        int maxVal = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = minVal;
                minVal = maxVal;
                maxVal = tmp;
            }
            minVal = Math.min(minVal * nums[i], nums[i]);
            maxVal = Math.max(maxVal * nums[i], nums[i]);
            max = Math.max(maxVal, max);
        }
        return max;
    }
}
