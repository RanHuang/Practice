package leetcode.simple;

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
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        for (int i : result) {
            System.out.println(result[i]);
        }

    }

    public static int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        int i = 0;
        int j = 0;
        for (; i < size; i++) {
            int a = nums[i];
            for (j = i + 1; j < size; j++) {
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