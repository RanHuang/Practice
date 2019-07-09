package leetcode.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author nick
 * @date 2019-07-09 星期二 23:31
 **/
public class ClimbStairs {
    /**
     * 设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * 示例：
     * 三级台阶有三种方法可以爬到楼顶：
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        //int num = climbStairs(n - 1) + climbStairs(n - 2);
        // 将递归调用转换为循环

        int next = 1;
        int nextNext = 2;
        int num = 0;
        for (int i = 3; i <= n; i++) {
            num = next + nextNext;
            next = nextNext;
            nextNext = num;
        }
        return num;
    }

    @Test
    public void testClimbStairs() {
        Assert.assertEquals(2, climbStairs(2));
        Assert.assertEquals(3, climbStairs(3));
        Assert.assertEquals(5, climbStairs(4));
        Assert.assertEquals(8, climbStairs(5));
        Assert.assertEquals(1134903170, climbStairs(44));
    }
}
