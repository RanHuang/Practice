package leetcode.medium.matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * @author nick
 * @date 2019-07-30 星期二 17:08
 **/
public class SpiralOrder {
    @Test
    public void testSpiralOrder1() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = this.spiralOrder(matrix);
        Integer[] ret = new Integer[result.size()];
        result.toArray(ret);
        Assert.assertTrue(Arrays.equals(new Integer[]{1, 2, 3, 6, 9, 8, 7, 4, 5}, ret));
    }

    @Test
    public void testSpiralOrder2() {
        int[][] matrix = {{1}, {2}, {3}, {4}, {5}};
        List<Integer> result = this.spiralOrder(matrix);
        Integer[] ret = new Integer[result.size()];
        result.toArray(ret);
        Assert.assertTrue(Arrays.equals(new Integer[]{1, 2, 3, 4, 5}, ret));
    }

    /**
     * 逐层顺时针输出数值
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        while (i < m && j < n) {
            List<Integer> lstVal = this.spiralOrderSingleLevel(matrix, i, j, m - 1, n - 1);
            result.addAll(lstVal);
            i++;
            j++;
            m--;
            n--;
        }
        return result;
    }

    public List<Integer> spiralOrderSingleLevel(int[][] matrix, int x, int y, int m, int n) {
        List<Integer> result = new ArrayList<>();
        //
        int i;
        int j;
        // 1
        i = x;
        for (j = y; j <= n; j++) {
            result.add(matrix[i][j]);
        }
        // 2
        j = n;
        for (i = x + 1; i <= m; i++) {
            result.add(matrix[i][j]);
        }

        // 3
        if (m > x) {
            i = m;
            for (j = n - 1; j >= x; j--) {
                result.add(matrix[i][j]);
            }
        }
        // 4
        if (n > y) {
            j = y;
            for (i = m - 1; i >= x + 1; i--) {
                result.add(matrix[i][j]);
            }
        }

        return result;
    }
}
