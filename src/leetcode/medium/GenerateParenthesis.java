package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-07 星期日 23:32
 **/
public class GenerateParenthesis {

    @Test
    public void testGenerateParenthesis() {
        List<String> result = generateParenthesis(3);
        Assert.assertEquals(5, result.size());
    }

    /**
     * 暴力搜索
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(n * n);
        //generateBrute("", n * 2, result);
        generateLookBack("", 0, 0, n, result);
        return result;
    }

    public void generateLookBack(String value, int left, int right, int size, List<String> result) {
        if (value.length() == size * 2) {
            result.add(value);
            return;
        }

        if (left < size) {
            generateLookBack(value + "(", left + 1, right, size, result);
        }
        if (right < left) {
            generateLookBack(value + ")", left, right + 1, size, result);
        }
    }

    /**
     * 暴力搜索
     *
     * @param value
     * @param size
     * @param result
     */
    public void generateBrute(String value, int size, List<String> result) {
        if (value.length() == size) {
            // 判断是否为有效结果
            if (isValid(value)) {
                result.add(value);
            }
            return;
        }
        generateBrute(value + "(", size, result);
        generateBrute(value + ")", size, result);
    }

    @Test
    public void testIsValid() {
        Assert.assertTrue(isValid("((()))"));
        Assert.assertTrue(isValid("()()()"));
        Assert.assertTrue(isValid("()(())"));
        Assert.assertTrue(isValid("(())()"));
        Assert.assertTrue(isValid("(()())"));

        Assert.assertFalse(isValid("(()(()"));
    }

    private boolean isValid(String value) {
        if (value.isEmpty()) {
            return true;
        }
        int num = 0;
        for (char ch : value.toCharArray()) {
            if (ch == '(') {
                num++;
            } else {
                num--;
            }
            if (num < 0) {
                return false;
            }
        }
        return num == 0;
    }
}
