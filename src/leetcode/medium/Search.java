package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author nick
 * @date 2019-07-09 星期二 12:13
 **/
public class Search {
    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * <p>
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * * 你的算法时间复杂度必须是 O(log n) 级别。
     * * 如果数组中不存在目标值，返回 [-1, -1]。
     * <p>
     * 使用二分查找法确定target位置，确保时间复杂度未O(long n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int index = binarySearch(nums, start, end, target);

        if (index < 0) {
            // 数组中不存在匹配target的值
            return new int[]{-1, -1};
        } else {
            // 确定数值的首尾位置
            int i = index;
            int j = index;
            while (i > 0 && nums[i - 1] == target) {
                i--;
            }
            while (j < nums.length - 1 && nums[j + 1] == target) {
                j++;
            }
            return new int[]{i, j};
        }
    }

    /**
     * 二分搜索法
     *
     * @param nums
     * @param start
     * @param end
     * @param target
     * @return
     */
    private int binarySearch(int nums[], int start, int end, int target) {
        int i = start;
        int j = end;
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

        return index;
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


    /**
     * 33. 搜索旋转排序数组
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 算法时间复杂度必须是 O(log n) 级别。
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }
        // 先定位旋转分界点
        int start = 0;
        int end = nums.length - 1;
        int bound = bound(nums, start, end);
        if (bound > -1) {
            if (nums[0] > target) {
                // target位于旋转数组后半截
                start = bound + 1;
                end = nums.length - 1;
            } else {
                // target位于旋转数组前半截
                start = 0;
                end = bound;
            }
        }
        // 再使用二分查找法确定target位置
        int index = binarySearch(nums, start, end, target);
        return index;
    }

    /**
     * 使用二分查找法确定旋转点，下一个值为降序的数值位置点
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int bound(int[] nums, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return mid;
        }
        int boundLeft = bound(nums, start, mid);
        if (boundLeft > -1) {
            return boundLeft;
        }
        int boundRight = bound(nums, mid + 1, end);
        if (boundRight > -1) {
            return boundRight;
        }
        return -1;
    }

    @Test
    public void testBound1() {
        int nums[] = {4, 5, 6, 7, 0, 1, 2};
        int bound = bound(nums, 0, nums.length - 1);
        Assert.assertEquals(3, bound);
    }

    @Test
    public void testBound2() {
        int nums[] = {1, 3};
        int bound = bound(nums, 0, nums.length - 1);
        Assert.assertEquals(-1, bound);
    }

    @Test
    public void testBound3() {
        int nums[] = {3, 1};
        int bound = bound(nums, 0, nums.length - 1);
        Assert.assertEquals(0, bound);
    }

    @Test
    public void testBound4() {
        int nums[] = {5, 1, 3};
        int bound = bound(nums, 0, nums.length - 1);
        Assert.assertEquals(0, bound);
    }

    @Test
    public void testSearch1() {
        int nums[] = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        Assert.assertEquals(4, search(nums, target));
    }

    @Test
    public void testSearch2() {
        int nums[] = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        Assert.assertEquals(-1, search(nums, target));
    }

    @Test
    public void testSearch3() {
        int nums[] = {1, 3};
        int target = 0;
        Assert.assertEquals(-1, search(nums, target));
    }

    @Test
    public void testSearch4() {
        int nums[] = {3, 1};
        int target = 1;
        Assert.assertEquals(1, search(nums, target));
    }
}
