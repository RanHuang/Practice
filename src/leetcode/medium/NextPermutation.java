package leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-06 星期六 23:56
 **/
public class NextPermutation {

    @Test
    public void testNextPermutation() {
        assertTrue(Arrays.equals(nextPermutation(new int[]{1, 2, 3}), new int[]{1, 3, 2}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{3, 2, 1}), new int[]{1, 2, 3}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{1, 1, 5}), new int[]{1, 5, 1}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{1, 1, 1}), new int[]{1, 1, 1}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{1}), new int[]{1}));
    }

    @Test
    public void debug() {
        assertTrue(Arrays.equals(nextPermutation(new int[]{1, 3, 2}), new int[]{2, 1, 3}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{2, 3, 1}), new int[]{3, 1, 2}));
        assertTrue(Arrays.equals(nextPermutation(new int[]{5, 2, 4, 3}), new int[]{5, 3, 2, 4}));
    }

    @Test
    public void printNextPermutation() {
        int[] nums = {1, 2, 3};
        int[] next = nextPermutation(nums);
        System.out.println(Arrays.toString(next));
    }

    public int[] nextPermutation(int[] nums) {
        int length = nums.length;
        int j = length - 1;
        while (j > 0) {
            if (nums[j] > nums[j - 1]) {
                break;
            }
            j--;
        }

        if (j == 0) {
            // 不存在下一个更大的排列，则将数字重新排列成最小的排列
            // 将从大到小的序列逆序为从小到大的序列
            // 方法1. 双指针依次交换首尾元素
            int start = 0;
            int end = length - 1;
            while (start < end) {
                nums[start] = nums[start] ^ nums[end];
                nums[end] = nums[start] ^ nums[end];
                nums[start] = nums[start] ^ nums[end];
                start++;
                end--;
            }
            // 方法2. 重新从小到大排序
            //Arrays.sort(nums);
        } else {
            //取从j...length - 1中找到比nums[j-1]大的最小值位置记为s,
            // 交换s与j-1位置元素，
            // 对j...length-1位置的元素重新排序
            int min = Integer.MAX_VALUE;
            int s = 0;
            int index = j;
            while (index < length) {
                if (nums[index] > nums[j - 1] && nums[index] < min) {
                    s = index;
                    min = nums[index];
                }
                index++;
            }
            //
            nums[j - 1] = nums[s] ^ nums[j - 1];
            nums[s] = nums[s] ^ nums[j - 1];
            nums[j - 1] = nums[s] ^ nums[j - 1];

            Arrays.sort(nums, j, length);
        }
        return nums;
    }
}
