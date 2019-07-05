package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-05 星期五 00:21
 **/
public class ThreeSum {
    public static void main(String[] args) {
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {0, 0, 0, 0, 0, 0};
        //int nums[] = {-2, 0, 1, 1, 2};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        int start = 0;
        int end = nums.length - 2;
        for (; start < end; start++) {
            if (nums[start] > 0) {
                break;
            }
            // 关键的判断，否则会出现重复结果
            if (start > 0 && nums[start - 1] == nums[start]) {
                continue;
            }
            int next = start + 1;
            int tail = nums.length - 1;
            while (next < tail) {
                if (next > start + 1 && nums[next - 1] == nums[next]) {
                    next++;
                    continue;
                }
                if (tail < nums.length - 1 && nums[tail] == nums[tail + 1]) {
                    tail--;
                    continue;
                }
                if (nums[next] + nums[tail] == Math.abs(nums[start])) {
                    result.add(Arrays.asList(nums[start], nums[next], nums[tail]));
                    next++;
                    tail--;
                } else if (nums[next] + nums[tail] < Math.abs(nums[start])) {
                    next++;
                } else {
                    tail--;
                }
            }

        }

        return result;
    }
}
