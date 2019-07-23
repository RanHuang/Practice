package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-23 星期二 22:20
 **/
public class FindPeakElement {
    /**
     * 要求:  O(logN) 时间复杂度
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        // 仅具有一个元素或者首尾元素为峰值
        if (length < 2 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[length - 2] < nums[length - 1]) {
            return length - 1;
        }

        // 二分搜索法
        int start = 0;
        int end = length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (mid == 0 || mid == length - 1) {
                break;
            }
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return -1;
    }

    @Test
    public void testFindPeakElement() {
        Assert.assertEquals(2, this.findPeakElement(new int[]{1, 2, 3, 1}));
        Assert.assertEquals(5, this.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
        Assert.assertEquals(1, this.findPeakElement(new int[]{3, 4, 3, 2, 1}));
    }
}
