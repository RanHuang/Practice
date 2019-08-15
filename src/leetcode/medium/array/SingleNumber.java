package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
    @Test
    public void testSingleNumber() {
        Assert.assertEquals(3, this.singleNumber(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(3, this.singleNumberSet(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, this.singleNumberMap(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    /**
     * 将所有数字转换为3进制，进行不进位的三进制加法操作
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] retVal3Base = new int[30];
        for (int i = 0; i < nums.length; i++) {
            int[] val3Base = this.fromTenToThreeBase(nums[i]);
            for (int j = 0; j < val3Base.length; j++) {
                retVal3Base[j] = (retVal3Base[j] + val3Base[j]) % 3;
            }
        }
        // 翻转retVal3Base的高位和低位
        int retval = this.fromThreeToTenBase(retVal3Base);
        return retval;
    }

    /**
     * 将十进制整数转换为3进制数组
     * 其中数组的低位存放数字的低位，即打印的数组字符串为逆序
     *
     * @param val
     * @return
     */
    private int[] fromTenToThreeBase(int val) {
        Deque<Integer> deque = new LinkedList<>();
        do {
            deque.add(val % 3);
            val = val / 3;
        } while (val > 0);

        int[] ret = new int[deque.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = deque.poll();
        }
        return ret;
    }

    /**
     * 从高位至低位遍历3进制数组，累加转换为十进制整数
     *
     * @param vals
     * @return
     */
    private int fromThreeToTenBase(int[] vals) {
        int sum = 0;
        for (int i = vals.length - 1; i >= 0; i--) {
            sum = sum * 3 + vals[i];
        }
        return sum;
    }

    @Test
    public void testFromThreeToTenBase() {
        Assert.assertEquals(7, this.fromThreeToTenBase(new int[]{1, 2}));
        Assert.assertTrue(Arrays.equals(new int[]{1, 2}, this.fromTenToThreeBase(7)));
    }

    public int singleNumberMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 此方法可能会出现整数溢出
     *
     * @param nums
     * @return
     */
    public int singleNumberSet(int[] nums) {
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
}
