package leetcode.simple.Stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 155. 最小栈
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * * 思路：使用辅助栈记录当前的最小元素
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-23 星期二 12:49
 **/
public class MinStack {
    private Stack<Integer> valStack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        valStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        valStack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        int val = valStack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return valStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    @Test
    public void testMinStack() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        minStack.top();
        Assert.assertEquals(-2, minStack.getMin());
    }
}
