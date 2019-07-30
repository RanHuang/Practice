package leetcode.medium.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @author nick
 * @date 2019-07-30 星期二 15:48
 **/
public class Atoi {
    @Test
    public void testMaxMinInteger() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    @Test
    public void testAtoi() {
        Assert.assertEquals(-42, this.myAtoi("   -42"));
        Assert.assertEquals(0, this.myAtoi("   +0 123"));
        Assert.assertEquals(0, this.myAtoi("+-2"));
        Assert.assertEquals(12345678, this.myAtoi("  0000000000012345678"));
        Assert.assertEquals(42, this.myAtoi("42"));
        Assert.assertEquals(42, this.myAtoi("+42"));
        Assert.assertEquals(0, this.myAtoi("+"));
        Assert.assertEquals(0, this.myAtoi("   -"));
        Assert.assertEquals(4193, this.myAtoi("4193 with words"));
        Assert.assertEquals(0, this.myAtoi("words and 987"));
        Assert.assertEquals(-2147483648, this.myAtoi("-91283472332"));
    }

    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
     * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 去除字符串中开头的空白字符
        int space = 0;
        while (space < str.length() && str.charAt(space) == ' ') {
            space++;
        }
        String validStr = str.substring(space);
        if (validStr.length() == 0) {
            return 0;
        }

        // 处理存在前缀符号位的字符情况
        String prefix = "";

        if (validStr.charAt(0) == '-') {
            prefix = "-";
            validStr = validStr.substring(1);
        } else if (validStr.charAt(0) == '+') {
            // 去除正整数前面冗余的'+'
            validStr = validStr.substring(1);
        }
        if (validStr.length() == 0 || !this.isDigit(validStr.charAt(0))) {
            return 0;
        }


        // 去除前导字符'0'
        int start = 0;
        while (start < validStr.length() && validStr.charAt(start) == '0') {
            start++;
        }
        // 获取有效的数字字符区间
        int end = start;
        while (end < validStr.length() && this.isDigit(validStr.charAt(end))) {
            end++;
        }

        String digitString = validStr.substring(start, end);
        if (digitString.length() == 0) {
            return 0;
        }
        // 如果是负数，则增加符号'-'
        digitString = prefix + digitString;

        // 数值范围为 [−2^31,2^31−1]。如果数值超过这个范围，返回INT_MIN (-2147483648)或INT_MAX(2147483647)
        String maxString = String.valueOf(Integer.MAX_VALUE);
        String minString = String.valueOf(Integer.MIN_VALUE);

        int value;
        if (this.isDigit(digitString.charAt(0))) {
            // 正整数
            if (digitString.length() > 10) {
                value = Integer.MAX_VALUE;
            } else if (digitString.length() == 10 && digitString.compareTo(maxString) > 0) {
                value = Integer.MAX_VALUE;
            } else {
                value = Integer.valueOf(digitString);
            }

        } else {
            // 负整数
            if (digitString.length() > 11) {
                value = Integer.MIN_VALUE;
            } else if (digitString.length() == 11 && digitString.compareTo(minString) > 0) {
                value = Integer.MIN_VALUE;
            } else {
                value = Integer.valueOf(digitString);
            }
        }

        return value;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

}
