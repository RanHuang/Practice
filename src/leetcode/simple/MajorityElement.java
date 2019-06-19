package leetcode.simple;

import java.util.Arrays;

/**
 * @author nick
 * @date 2019-06-19 星期三 21:30
 *
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 7, 4, 3, 3, 5, 3};

        int target = majorityElement(nums);
        System.out.println(target);
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int head = 0;
        int tail = nums.length - 1;
        while (head <= tail) {
            if (nums[head] != nums[tail]) {
                head++;
                tail--;
            } else {
                return nums[head];
            }
        }

        throw new RuntimeException("Illegal Integer Array, Not Found!");
    }
}
