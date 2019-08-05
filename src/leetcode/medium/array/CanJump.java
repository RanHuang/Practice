package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * @author nick
 * @date 2019-08-05 星期一 21:27
 **/
public class CanJump {
    @Test
    public void testCanJump() {
        Assert.assertTrue(this.canJump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertFalse(this.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    public boolean canJump(int[] nums) {
        boolean ret = this.jump(nums, 0);
        return ret;
    }

    /**
     * 回溯法
     *
     * @param nums
     * @param index
     * @return
     */
    private boolean jump(int[] nums, int index) {
        if (index == nums.length - 1) {
            return true;
        }

        boolean ret = false;
        for (int i = 1; i <= nums[index]; i++) {
            // 排除超出数组范围的情况
            if (index + i >= nums.length) {
                break;
            }
            ret = this.jump(nums, index + i);
            if (ret) {
                break;
            }
        }
        return ret;
    }
}
