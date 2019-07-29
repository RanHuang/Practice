package leetcode.medium.permutation;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * @author nick
 * @date 2019-07-29 星期一 19:46
 **/
public class Subsets {

    @Test
    public void testSubsets() {
        int[] vals = {1,2,3};
        List<List<Integer>> result = this.subsets(vals);
        Assert.assertEquals((int)Math.pow(2, vals.length), result.size());
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        this.subsets_(result, data, nums, 0);
        return result;
    }

    private void subsets_(List<List<Integer>> result, List<Integer> data, int[] nums, int index) {
        if (index == nums.length) {
            result.add(data);
            return;
        }
        // 当前元素不加入集合中
        List<Integer> dataWithOut = new ArrayList<>(data);
        this.subsets_(result, dataWithOut, nums, index+1);
        // 当前元素加入集合中
        List<Integer> dataWith = new ArrayList<>(data);
        dataWith.add(nums[index]);
        this.subsets_(result, dataWith, nums, index+1);
    }
}
