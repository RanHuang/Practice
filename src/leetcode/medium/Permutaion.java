package leetcode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nick
 * @date 2019-07-10 星期三 10:38
 **/
public class Permutaion {

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        // 全排列数量
        int size = 1;
        for (int i = 1; i <= nums.length; i++) {
            size *= i;
        }
        List<List<Integer>> result = new ArrayList<>(size);

        List<Integer> value = new ArrayList<>(nums.length);
        backTrack(nums, 0, result);
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 回溯法求解整数序列的全排列
     *
     * @param nums
     * @param start
     * @param result
     */
    private void backTrack(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            // 候选序列中只剩一个数，则搜集排列结果
            List<Integer> value = new ArrayList<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                value.add(nums[i]);
            }
            result.add(value);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 前移选中数字，更新候选序列
            swap(nums, start, i);
            backTrack(nums, start + 1, result);
            // 回溯
            swap(nums, start, i);
        }
    }

    @Test
    public void testPermute() {
        int nums[] = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }
}
