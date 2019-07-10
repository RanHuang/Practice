package algorithms.math;

import org.junit.Test;

/**
 * @author nick
 * @date 2019-07-10 星期三 10:10
 **/
public class Square {
    /**
     * 使用二分搜索法求解整数的平方根
     *
     * @param n n>0&整数
     * @return
     */
    public double squareRootBinarySearch(int n) {
        double precision = Math.pow(10, -5);
        double s = 0;
        double e = n;
        double root = (s + e) / 2.0;
        double delta = Math.pow(root, 2) - n;
        while (Math.abs(delta) > precision) {
            if (delta < 0) {
                s = root;
            } else {
                e = root;
            }
            root = (s + e) / 2.0;
            delta = Math.pow(root, 2) - n;
        }

        return root;
    }

    @Test
    public void testSquareRootBinarySearch() {
        int n = 2;
        double squareRoot = squareRootBinarySearch(n);
        System.out.println(squareRoot);
    }

    /**
     * 牛顿迭代法求解整数平方根
     *  x(n+1) = x(n) - f(x(n))/f'(x(n))
     * @param n
     * @return
     */
    public double squareRootNewtonRaphsonMethod(int n) {
        // 迭代次数
        int times = 10;
        double root = n/2.0;
        int index = 0;
        while (index < times) {
            index++;
            double f = Math.pow(root, 2) - n;
            double f_ = 2.0 * root;
            root = root - f/f_;
        }
        return root;
    }

    @Test
    public void testSquareRootNewtonRaphsonMethod() {
        int n = 2;
        double squareRoot = squareRootBinarySearch(n);
        System.out.println(squareRoot);
    }
}
