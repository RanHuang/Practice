package leetcode.medium;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-10 星期三 22:30
 **/
public class Rotate {

    @Test
    public void testRotate2() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        printMatrix(matrix, 2);
        rotate(matrix);
        System.out.println("---------");
        printMatrix(matrix, 2);
    }

    @Test
    public void testRotate3() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printMatrix(matrix, 3);
        rotate(matrix);
        System.out.println("---------");
        printMatrix(matrix, 3);
    }

    @Test
    public void testRotate4() {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        printMatrix(matrix, 4);
        rotate(matrix);
        System.out.println("---------");
        printMatrix(matrix, 4);
    }

    @Test
    public void testRotate5() {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };

        printMatrix(matrix, 5);
        rotate(matrix);
        System.out.println("---------");
        printMatrix(matrix, 5);
    }


    public void rotate(int[][] matrix) {
        for (int i = 1; i <= matrix.length / 2; i++) {
            rotateLayer(matrix, i);
        }
    }

    /**
     * 逐层进行旋转
     *
     * @param matrix
     * @param layer
     */
    private void rotateLayer(int[][] matrix, int layer) {
        int start = layer - 1;
        int end = matrix.length - layer;
        // 逐个进行旋转(最后一个不与旋转，否则会导致边角元素旋转两次)
        for (int i = 0; i < end - start; i++) {
            // (start,start+i), (start+i,end), (end,end-i), (end-i,start)
            int tmp = matrix[end - i][start];
            matrix[end - i][start] = matrix[end][end - i];
            matrix[end][end - i] = matrix[start + i][end];
            matrix[start + i][end] = matrix[start][start + i];
            matrix[start][start + i] = tmp;
        }
    }

    private void printMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
