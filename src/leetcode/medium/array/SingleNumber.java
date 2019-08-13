package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 137. 只出现一次的数字 II
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-08-13 星期二 22:44
 **/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        Set<Integer> valSet = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            valSet.add(nums[i]);
            sum += nums[i];
        }

        int sum3 = 0;
        for (Integer val : valSet) {
            sum3 += val;
        }
        sum3 = sum3 * 3;

        return (sum3 - sum) / 2;
    }

    @Test
    public void testSingleNumber() {
        Assert.assertEquals(3, this.singleNumber(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, this.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }
}
