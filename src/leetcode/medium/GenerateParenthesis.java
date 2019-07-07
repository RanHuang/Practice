package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
     * 回溯算法
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(n * n);
        generate("", n * 2, result);
        return result;
    }

    public void generate(String value, int size, List<String> result) {
        if (value.length() == size) {
            // 判断是否为有效结果
            if (isValid(value)) {
                result.add(value);
            }
            return;
        }
        generate(value + "(", size, result);
        generate(value + ")", size, result);
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
        Deque<Character> deque = new LinkedList<>();
        deque.push(value.charAt(0));
        int index = 1;
        while (index < value.length()) {
            char character = value.charAt(index);
            if (character == '(') {
                deque.push(character);
            } else {
                // ')'匹配栈中的'('
                if (Objects.equals(deque.peek(), '(')) {
                    deque.pop();
                } else {
                    return false;
                }
            }
            index++;
        }
        if (deque.isEmpty()) {
            return true;
        }
        return false;
    }
}
