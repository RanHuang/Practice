package leetcode.simple;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-06 星期六 12:40
 **/
public class ValidBrackets {
    private Map<Character, Character> bracketMap = new HashMap<>(8);
    private Set<Character> leftBracket = new HashSet<>(4);

    @Before
    public void init() {
        bracketMap.put('(', ')');
        bracketMap.put('[', ']');
        bracketMap.put('{', '}');
        leftBracket.addAll(bracketMap.keySet());
    }

    @Test
    public void isValid() {
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertTrue(isValid(""));
        Assert.assertTrue(isValid("()"));
    }

    @Test
    public void isValidDebug() {
        Assert.assertFalse(isValid(")}{({))[{{[}"));
    }

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        int length = s.length();
        // 奇数个字符必定不符合要求
        if ((length & 1) == 1) {
            return false;
        }
        Deque<Character> deque = new LinkedList<>();
        int i = 0;
        deque.push(s.charAt(i));
        i++;
        while (i < length) {
            char c = s.charAt(i);
            if (leftBracket.contains(c)) {
                deque.push(c);
            } else {
                char left = deque.peek();
                // 使用基本数据类型可能出现NullPointerException
                Character right = bracketMap.get(left);
                if (Objects.equals(c, right)) {
                    deque.pop();
                } else {
                    return false;
                }
            }
            i++;
        }
        if (deque.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
