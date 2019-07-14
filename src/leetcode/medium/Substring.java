package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * <p>
 * 示例 4:
 * 输入: "au"
 * 输出: 2
 * 解释: 因为无重复字符的最长子串是 "au"，所以其长度为 2。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-21 星期五 21:33
 **/
public class Substring {

    @Test
    public void testLongestSubstring() {
        String str1st = "abcabcbb";
        int length1st = lengthOfLongestSubstringImprovement(str1st);
        System.out.println(length1st);

        String str2nd = "bbbbb";
        int length2nd = lengthOfLongestSubstringBrute(str2nd);
        System.out.println(length2nd);

        String str3rd = "au";
        int length3rd = lengthOfLongestSubstringImprovement(str3rd);
        System.out.println(length3rd);
    }

    /**
     * 改进算法：从前向后遍历字符串，将当前字符与不重复子串匹配：
     * 1.遇到重复字符记为a， a...a, 则新的不重复子串为 ...a
     * 2.如果未遇到重复字符，则不重复字串扩充一个字符
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringImprovement(String s) {
        if (s == null || s.length() < 2) {
            // 空串或仅包含一个字符的字符串
            return s.length();
        }

        char[] chars = s.toCharArray();
        int sLength = chars.length;
        int maxLength = 0;
        int startOfSubstring = 0;
        for (int current = 0; current < sLength; current++) {
            for (int index = startOfSubstring; index < current; index++) {
                if (chars[index] == chars[current]) {
                    // 遇到重复字符，则前面为包含非重复字符的字串
                    startOfSubstring = index + 1;
                    break;
                }
            }
            int subLength = current - startOfSubstring + 1;
            maxLength = Math.max(maxLength, subLength);
        }

        return maxLength;
    }

    /**
     * 暴力搜索：通过Set判断是否遇到重复字符，出现第一个重复字符之前的子串为
     * 无重复字符的子串
     *
     * @param
     * @return
     */
    public static int lengthOfLongestSubstringBrute(String s) {
        if (s == null || s.length() < 2) {
            // 空串或仅包含一个字符的字符串
            return s.length();
        }
        char[] chars = s.toCharArray();
        int sLength = chars.length;
        int maxLength = 0;

        Set<Character> characterSet = new HashSet<>(10);
        for (int i = 0; i < sLength; i++) {
            characterSet.clear();
            for (int j = i; j < sLength; j++) {
                if (characterSet.contains(chars[j])) {
                    break;
                } else {
                    characterSet.add(chars[j]);
                }
            }
            int length = characterSet.size();
            maxLength = Math.max(length, maxLength);
        }

        return maxLength;
    }

    @Test
    public void testCountSubstrings() {
        Assert.assertEquals(0, countSubstrings(""));
        Assert.assertEquals(1, countSubstrings("a"));
        Assert.assertEquals(3, countSubstrings("abc"));
        Assert.assertEquals(6, countSubstrings("aaa"));
    }


    /**
     * 中心扩展法
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (Objects.isNull(s)) {
            return 0;
        }

        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1) {
                int count1 = segmentCount(chars, i, i + 1);
                count += count1;
            }
            int count2 = segmentCount(chars, i, i);
            count += count2;
        }
        return count;
    }

    private int segmentCount(char[] chars, int p, int q) {
        int ret = 0;
        while (p >= 0 && q < chars.length && chars[p] == chars[q]) {
            ret++;
            p--;
            q++;
        }
        return ret;
    }
}
