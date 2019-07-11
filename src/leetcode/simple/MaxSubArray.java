package leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author nick
 * @date 2019-07-11 星期四 22:34
 **/
public class MaxSubArray {
    @Test
    public void testMaxSubArray1() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, maxSubArray(nums));
    }

    @Test
    public void testMaxSubArray2() {
        int[] nums = {-2, -3, -1, -5};
        Assert.assertEquals(-1, maxSubArray(nums));
    }

    /**
     * 时间复杂度O(n)
     *  动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // 序列中存在正数
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            if (sum + val > 0) {
                sum += val;
                if (sum > max) {
                    max = sum;
                }
            } else {
                sum = 0;
            }
        }

        //  序列中无正数，则寻找最大负数
        if (max < 0) {
            for (int val : nums) {
                if (val > max) {
                    max = val;
                }
            }
        }

        return max;
    }

    // TODO 尝试使用更为精妙的分治法求解
}
