package leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nick
 * @date 2019-06-19 星期三 20:47
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {8, 7, 11, 1};
        int target = 9;
        int[] result = twoSumHash(nums, target);
        for (int value : result) {
            System.out.println(value);
        }

    }

    /**
     * Hash记录已经访问过的数字
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumHash(int[] nums, int target) {
        int size = nums.length;
        Map<Integer, Integer> valueIndexMap = new HashMap<>(size);
        for (int i = size - 1; i >= 0; i--) {
            int val1 = nums[i];
            int val2 = target - val1;
            if (valueIndexMap.containsKey(val2)) {
                return new int[]{i, valueIndexMap.get(val2)};
            }
            valueIndexMap.put(val1, i);
        }

        throw new RuntimeException("Not Found");
    }

    /**
     * 暴力搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumExhausition(int[] nums, int target) {
        int size = nums.length;
        int i = 0;
        for (; i < size; i++) {
            int a = nums[i];
            for (int j = i + 1; j < size; j++) {
                int b = nums[j];
                if (a + b == target) {
                    int[] result = {i, j};
                    return result;
                }
            }
        }
        return new int[0];
    }
}
