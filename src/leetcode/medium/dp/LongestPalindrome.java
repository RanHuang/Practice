package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-22 星期六 21:25
 **/
public class LongestPalindrome {
    @Test
    public void testLongestPalindrome2() {
        Assert.assertEquals("bab", this.longestPalindrome("babad"));
        Assert.assertEquals("ccc", this.longestPalindrome("ccc"));
        Assert.assertEquals("bb", this.longestPalindrome("bb"));
        Assert.assertEquals("aaaa", this.longestPalindrome("aaaa"));
    }

    /**
     * 动态规划法
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }

        char[] chars = s.toCharArray();
        int length = chars.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            // 处理长度为子串
            dp[i][i] = 1;
        }
        if (length > 1) {
            // 处理长度为2子串
            for (int i = 0; i < length - 1; i++) {
                int j = i + 1;
                if (chars[i] == chars[j]) {
                    dp[i][j] = 2;
                }
            }
        }
        if (length > 2) {
            // 处理长度为3子串
            for (int i = 0; i < length - 2; i++) {
                int j = i + 2;
                if (chars[i] == chars[j]) {
                    dp[i][j] = 3;
                }
            }
        }

        if (length > 3) {
            // 使用动态规划处理长度超过3的子串
            for (int k = 3; k < length; k++) {  // 子串长度-1
                for (int i = 0; i < length - k; i++) {
                    int j = i + k;
                    if (dp[i + 1][j - 1] > 0 && chars[i] == chars[j]) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                }
            }
        }

        // 寻找长度最大的回文字串
        int max = Integer.MIN_VALUE;
        int indexStart = -1;
        int indexEnd = -1;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (max < dp[i][j]) {
                    max = dp[i][j];
                    indexStart = i;
                    indexEnd = j;
                }
            }
        }

        return s.substring(indexStart, indexEnd + 1);
    }


    @Test
    public void testLongestPalindrome1() {
        Assert.assertEquals("bab", this.longestPalindrome1("babad"));
        Assert.assertEquals("ccc", this.longestPalindrome1("ccc"));
        Assert.assertEquals("bb", this.longestPalindrome1("bb"));
        Assert.assertEquals("aaaa", this.longestPalindrome1("aaaa"));
    }

    /**
     * 中心扩展法
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }

        char[] chars = s.toCharArray();
        int length = chars.length;

        int first;
        int last;
        int maxLength = 0;
        int maxFirst = 0;
        int maxLast = 0;

        for (int i = 1; i < length - 1; i++) {
            if (chars[i - 1] == chars[i + 1]) {
                first = i - 1;
                last = i + 1;
            } else {
                first = last = i;
            }
            while (first > 0 && last < length - 1 && chars[first - 1] == chars[last + 1]) {
                first--;
                last++;
            }

            int subLen = last - first + 1;
            if (maxLength < subLen) {
                maxLength = subLen;
                maxFirst = first;
                maxLast = last;
            }
        }
        for (int i = 0; i < length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                first = i;
                last = i + 1;
            } else {
                continue;
            }
            while (first > 0 && last < length - 1 && chars[first - 1] == chars[last + 1]) {
                first--;
                last++;
            }

            int subLen = last - first + 1;
            if (maxLength < subLen) {
                maxLength = subLen;
                maxFirst = first;
                maxLast = last;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int index = maxFirst; index <= maxLast; index++) {
            sb.append(chars[index]);
        }
        return sb.toString();
    }
}
