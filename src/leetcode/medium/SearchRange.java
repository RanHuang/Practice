package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-09 星期二 12:13
 **/
public class SearchRange {
    /**
     * 要求算法时间复杂度必须是 O(log n) 级别，使用二分查找法确定target位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int index = -1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            } else {
                index = mid;
                break;
            }
        }

        if (index < 0) {
            // 数组中不存在匹配target的值
            return new int[]{-1, -1};
        } else {
            // 确定数值的首尾位置
            i = j = index;
            while (i > 0 && nums[i - 1] == target) {
                i--;
            }
            while (j < nums.length - 1 && nums[j + 1] == target) {
                j++;
            }
            return new int[]{i, j};
        }
    }

    @Test
    public void testSearchRange1() {
        int nums[] = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int ret[] = searchRange(nums, target);
        Assert.assertTrue(Arrays.equals(ret, new int[]{3, 4}));
    }

    @Test
    public void testSearchRange2() {
        int nums[] = {5, 7, 7, 8, 8, 10};
        int target = 6;
        int ret[] = searchRange(nums, target);
        Assert.assertTrue(Arrays.equals(ret, new int[]{-1, -1}));
    }
}
