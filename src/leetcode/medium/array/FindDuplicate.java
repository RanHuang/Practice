package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-28 星期日 12:10
 **/
public class FindDuplicate {

    @Test
    public void testFindDuplicate() {
        Assert.assertEquals(2, this.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        Assert.assertEquals(3, this.findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }

    /**
     * 排序，重复的数字在序列中连续排列
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }
}
