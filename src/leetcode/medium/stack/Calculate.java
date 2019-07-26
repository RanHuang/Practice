package leetcode.medium.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 227. 基本计算器 II
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格。 整数除法仅保留整数部分。
 *
 * @author nick
 * @date 2019-07-26 星期五 21:33
 **/
public class Calculate {
    public int calculate(String s) {
        // 使用正则表达式去除空格
        s = s.replaceAll("\\s*", "");
        if (s.length() < 2) {
            return Integer.valueOf(s);
        }

        char[] chars = s.toCharArray();
        // 按顺序分类操作数和操作符
        Queue<Integer> numberQueue = new LinkedList<>();
        Queue<Character> operatorQueue = new LinkedList<>();
        int start = 0;
        int end = 1;
        while (true) {
            if (isOperator(chars[end])) {
                numberQueue.add(Integer.valueOf(s.substring(start, end)));
                operatorQueue.add(chars[end]);
                start = end + 1;
                end = end + 2;
            } else {
                end++;
            }

            if (end >= chars.length) {
                numberQueue.add(Integer.valueOf(s.substring(start, chars.length)));
                break;
            }
        }

        // 特殊情况-表达式仅一个数字
        if (operatorQueue.isEmpty()) {
            return numberQueue.poll();
        }

        // 高优先级运算符直接计算
        Stack<Integer> numStack = new Stack<>();
        numStack.push(numberQueue.poll());
        Stack<Character> oprStack = new Stack<>();
        while (!numberQueue.isEmpty()) {
            if (!operatorQueue.isEmpty()) {
                char operator = operatorQueue.poll();
                if (operator == '*') {
                    numStack.push(numStack.pop() * numberQueue.poll());
                } else if (operator == '/') {
                    numStack.push(numStack.pop() / numberQueue.poll());
                } else {
                    // 计算低优先级运算符
                    while (!oprStack.isEmpty()) {
                        char operator_ = oprStack.pop();
                        int second = numStack.pop();
                        int first = numStack.pop();
                        if (operator_ == '+') {
                            numStack.push(first + second);
                        } else if (operator_ == '-') {
                            numStack.push(first - second);
                        }
                    }
                    numStack.push(numberQueue.poll());
                    oprStack.push(operator);
                }
            }
        }
        // 计算低优先级运算符
        while (!oprStack.isEmpty()) {
            char operator = oprStack.pop();
            int second = numStack.pop();
            int first = numStack.pop();
            if (operator == '+') {
                numStack.push(first + second);
            } else if (operator == '-') {
                numStack.push(first - second);
            }
        }
        return numStack.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    @Test
    public void testCalculate() {
        Assert.assertEquals(1, this.calculate("1-1+1"));
        Assert.assertEquals(-2147483647, this.calculate("0-2147483647"));
        Assert.assertEquals(0, this.calculate("0"));
        Assert.assertEquals(7, this.calculate("3+2*2"));
        Assert.assertEquals(30, this.calculate("32/4+10*2 + 2"));
    }
}
