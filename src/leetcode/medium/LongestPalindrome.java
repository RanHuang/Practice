package leetcode.medium;

/**
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

    public static void main(String[] args) {
        String sub1st = longestPalindrome("babad");
        System.out.println(sub1st);

        String sub2nd = longestPalindrome("ccc");
        System.out.println(sub2nd);

        String sub3rd = longestPalindrome("bb");
        System.out.println(sub3rd);

        String sub4th = longestPalindrome("aaaa");
        System.out.println(sub4th);
    }

    /**
     * 中心扩展法
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
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
