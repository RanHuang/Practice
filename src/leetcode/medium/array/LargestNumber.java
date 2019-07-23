package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. 最大数
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 *
 * @author nick
 * @date 2019-07-23 星期二 22:49
 **/
public class LargestNumber {
    public String largestNumber(int[] nums) {
        // 将整数序列转换为字符串序列
        String[] numString = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numString[i] = String.valueOf(nums[i]);
        }

        // 处理数组全部为0的特殊序列
        Arrays.sort(numString, Comparator.reverseOrder());
        if ("0".equals(numString[0])) {
            return "0";
        }

        // 自定义比较器
        Arrays.sort(numString, (o1, o2) -> {
            String a = o1 + o2;
            String b = o2 + o1;
            return b.compareTo(a);
        });

        // 从高位至低位搜集结果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numString.length; i++) {
            sb.append(numString[i]);
        }
        return sb.toString();
    }

    @Test
    public void testLargestNumber() {
        Assert.assertEquals("210", this.largestNumber(new int[]{10, 2}));
        Assert.assertEquals("9534330", this.largestNumber(new int[]{3, 30, 34, 5, 9}));
        Assert.assertEquals("0", this.largestNumber(new int[]{0, 0, 0}));
    }
}
