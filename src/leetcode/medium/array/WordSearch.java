package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-08-10 星期六 18:23
 **/
public class WordSearch {

    @Test
    public void testExist2A() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        Assert.assertTrue(this.exist(board, "ABCCED"));
        Assert.assertTrue(this.exist(board, "SEE"));
        Assert.assertFalse(this.exist(board, "ABCB"));
    }

    @Test
    public void testExist2B() {
        char[][] board = {
                {'A', 'B'},
                {'C', 'D'}};
        Assert.assertTrue(this.exist(board, "ACDB"));
    }


    @Test
    public void testExist1A() {
        char[][] board = {{'A'}};
        Assert.assertFalse(this.exist(board, "AB"));
    }

    @Test
    public void testExist1B() {
        char[][] board = {{'A', 'B'}};
        Assert.assertTrue(this.exist(board, "BA"));
    }

    /**
     * 穷举+回溯
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null) {
            return false;
        }
        // 一维字符阵列特殊处理
        if (board.length == 1) {
            boolean isMatch = false;
            // 普通字符串匹配-从左至右
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < word.length(); j++) {
                    if (i + j >= board[0].length) {
                        isMatch = false;
                        break;
                    }
                    if (board[0][i + j] == word.charAt(j)) {
                        isMatch = true;
                    } else {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    return isMatch;
                }
            }

            // 普通字符串匹配-从右至左
            for (int i = board[0].length - 1; i >= 0; i--) {
                for (int j = 0; j < word.length(); j++) {
                    if (i - j < 0) {
                        isMatch = false;
                        break;
                    }
                    if (board[0][i - j] == word.charAt(j)) {
                        isMatch = true;
                    } else {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    return isMatch;
                }
            }
            return isMatch;
        }

        // 标记已被访问过的位置，避免重复访问
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }

        // 从二维阵列的每个位置开始穷举搜索是否存在可匹配的单词
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean isExist = this.exist_(visited, board, i, j, word, 0);
                if (isExist) {
                    return isExist;
                }
            }
        }
        return false;
    }

    /**
     * 回溯方法
     *
     * @param visited 结点是否被访问过状态标志
     * @param board   字符阵列
     * @param curI    当前匹配的字符坐标
     * @param curJ    当前匹配的字符坐标
     * @param word    待匹配单词
     * @param index   待匹配字符的序号
     * @return
     */
    private boolean exist_(boolean[][] visited, char[][] board, int curI, int curJ, String word, int index) {
        if (curI < 0 || curI >= board.length) {
            return false;
        }
        if (curJ < 0 || curJ >= board[curI].length) {
            return false;
        }

        if (visited[curI][curJ]) {
            return false;
        }


        if (board[curI][curJ] != word.charAt(index)) {
            return false;
        }

        if (index + 1 == word.length()) {
            return true;
        }

        // 设置当前位置被访问过
        visited[curI][curJ] = true;

        // 上
        boolean isExist = this.exist_(visited, board, curI - 1, curJ, word, index + 1);
        if (isExist) {
            return isExist;
        }
        // 下
        isExist = this.exist_(visited, board, curI + 1, curJ, word, index + 1);
        if (isExist) {
            return isExist;
        }
        // 左
        isExist = this.exist_(visited, board, curI, curJ - 1, word, index + 1);
        if (isExist) {
            return isExist;
        }
        // 右
        isExist = this.exist_(visited, board, curI, curJ + 1, word, index + 1);


        // 恢复当前位置访问状态
        visited[curI][curJ] = false;
        return isExist;
    }
}
