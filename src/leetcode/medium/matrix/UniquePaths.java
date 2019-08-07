package leetcode.medium.matrix;

import org.junit.Assert;
import org.junit.Test;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author nick
 * @date 2019-08-07 星期三 21:15
 **/
public class UniquePaths {
    @Test
    public void testUniquePaths() {
        Assert.assertEquals(3, this.uniquePaths(3, 2));
        Assert.assertEquals(28, this.uniquePaths(7, 3));
    }

    public int uniquePaths(int m, int n) {
        int[][] matrix = new int[m][n];
        matrix[0][0] = 1;
        for (int i = 1; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int j = 1; j < n; j++) {
            matrix[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
            }
        }
        return matrix[m - 1][n - 1];
    }
}
