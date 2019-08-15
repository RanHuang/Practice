package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1004. 最大连续1的个数 III
 *
 * @author nick
 * @date 2019-08-15 星期四 09:12
 **/
public class LongestOnes {
    @Test
    public void testLongestOnes() {
        Assert.assertEquals(4, this.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 0));
        Assert.assertEquals(6, this.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        Assert.assertEquals(10, this.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    /**
     * 双指针滑动窗口法
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int maxLength = Integer.MIN_VALUE;

        for (int left = 0, right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                if (K > 0) {
                    K--;
                } else {
                    // 将左指针置于跳过一个为0的位置
                    while (A[left] == 1) {
                        left++;
                    }
                    // 当前位置为0则后移一个位置
                    left += 1;
                }
            }

            int length = right - left + 1;
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }

}
