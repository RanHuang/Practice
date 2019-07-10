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
        List<Integer> src = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            src.add(nums[i]);
        }
        List<Integer> value = new ArrayList<>(nums.length);
        permuteInner(src, value, result);
        return result;
    }

    private void permuteInner(List<Integer> src, List<Integer> value, List<List<Integer>> result) {
        if (src.isEmpty()) {
            result.add(value);
            return;
        }
        for (Integer item : src) {
            List<Integer> subSrc = new ArrayList<>();
            subSrc.addAll(src);
            subSrc.remove(item);

            List<Integer> plusValue = new ArrayList<>(value);
            plusValue.add(item);

            permuteInner(subSrc, plusValue, result);

        }
    }

    @Test
    public void testPermute() {
        int nums[] = {1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }
}
