package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-11 星期四 21:53
 **/
public class CombinationSum {
    @Test
    public void testCombinationSum() {
        List<List<Integer>> result1 = combinationSum(new int[]{2, 3, 6, 7}, 7);
        result1.forEach(System.out::println);
        Assert.assertEquals(2, result1.size());

        System.out.println("==============================================");

        List<List<Integer>> result2 = combinationSum(new int[]{2, 3, 5}, 8);
        result2.forEach(System.out::println);
        Assert.assertEquals(3, result2.size());
    }


    /**
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<>();
        this.backTrack(candidates, target, 0, new ArrayList<>(), 0, result);
        return result;
    }

    /**
     * 使用回溯算法（注意元素可重复使用）
     *
     * @param candidates
     * @param target
     * @param start
     * @param ret
     * @param sum
     */
    private void backTrack(int[] candidates, int target, int start, List<Integer> ret, int sum, List<List<Integer>> result) {
        if (target == sum) {
            result.add(ret);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            List<Integer> plusRet = new ArrayList<>(ret);
            plusRet.add(candidates[i]);
            // start <- i, 重复使用当前元素
            this.backTrack(candidates, target, i, plusRet, sum + candidates[i], result);
        }
    }
}
