package leetcode.medium.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * <p>
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-17 星期三 20:23
 **/
public class LengthOfLIS {

    @Test
    public void testLengthOfLIS() {
        Assert.assertEquals(2, lengthOfLIS(new int[]{-2, -1}));
        Assert.assertEquals(0, lengthOfLIS(new int[]{}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        int table[] = new int[nums.length];
        // 初始化
        for (int i = 0; i < nums.length; i++) {
            table[i] = 1;
        }

        // 根据递推关系求解以i结尾的序列的最长子序列
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (table[i] < table[j] + 1) {
                        table[i] = table[j] + 1;
                    }
                }
            }
        }

        // 获取最长递增子序列的长度
        int max = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] > max) {
                max = table[i];
            }
        }
        return max;
    }

    @Test
    public void testLengthOfLCS() {
        int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int sortedNums[] = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int length = lengthOfLCS(nums, sortedNums);
        Assert.assertEquals(4, length);
    }

    @Test
    public void testLengthOfLISByLCS() {
        Assert.assertEquals(2, lengthOfLISByLCS(new int[]{-2, -1}));
        Assert.assertEquals(0, lengthOfLISByLCS(new int[]{}));
        Assert.assertEquals(4, lengthOfLISByLCS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    /**
     * 将输入序列进行排序，问题转化为求原序列与排序序列的最长公共子序列
     * 前提条件：输入序列排序不存在相同的元素
     *
     * @param nums
     * @return
     */
    public int lengthOfLISByLCS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int sortedNums[] = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int length = lengthOfLCS(nums, sortedNums);
        return length;
    }

    /**
     * 求解最长公共子序列
     *
     * @param A
     * @param B
     * @return
     */
    public int lengthOfLCS(int[] A, int[] B) {
        // 备忘录数组
        int table[][] = new int[A.length + 1][B.length + 1];

        // 初始化
        for (int i = 0; i <= A.length; i++) {
            table[i][B.length] = 0;
        }
        for (int j = 0; j <= B.length; j++) {
            table[0][A.length] = 0;
        }

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    table[i][j] = table[i + 1][j + 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i][j + 1], table[i + 1][j]);
                }

            }
        }
        return table[0][0];
    }
}
