package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author nick
 * @date 2019-07-31 星期三 16:20
 **/
public class MinPathSum {
    @Test
    public void test() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(7, this.minPathSum(grid));
    }

    /**
     * 动态规划法，状态转移方程为: distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]) + grid[i][j];
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        // 首行和首列数值初始化
        distance[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            distance[i][0] = distance[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            distance[0][j] = distance[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]) + grid[i][j];
            }
        }
        return distance[m - 1][n - 1];
    }
}
