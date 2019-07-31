package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    public void testMinPathSum() {
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
        // 将状态转移方程翻译为代码
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                distance[i][j] = Math.min(distance[i - 1][j], distance[i][j - 1]) + grid[i][j];
            }
        }
        // 通过状态表的变化逆推出移动路径
        Stack<Integer> pathStack = new Stack<>();
        int i = m - 1;
        int j = n - 1;
        pathStack.push(grid[i][j]);
        while (i >= 0 && j >= 0) {
            if (i > 0 && distance[i - 1][j] == distance[i][j] - grid[i][j]) {
                pathStack.push(grid[i - 1][j]);
                i--;
            } else if (j > 0 && distance[i][j - 1] == distance[i][j] - grid[i][j]) {
                pathStack.push(grid[i][j - 1]);
                j--;
            }else {
                // 回溯至起始位置后，变更i,j确保退出while循环
                i--;
                j--;
            }
        }
        // 输出移动路径
        List<Integer> lstPath = new ArrayList<>(pathStack.size());
        while (!pathStack.isEmpty()) {
            lstPath.add(pathStack.pop());
        }
        System.out.println(lstPath);

        return distance[m - 1][n - 1];
    }
}
