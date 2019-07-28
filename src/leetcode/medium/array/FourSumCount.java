package leetcode.medium.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-28 星期日 16:16
 **/
public class FourSumCount {
    @Test
    public void testFourSumCount() {
        int[] A = new int[]{1, 2};
        int[] B = new int[]{-2, -1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{0, 2};
        Assert.assertEquals(2, this.fourSumCountBrute(A, B, C, D));
        Assert.assertEquals(2, this.fourSumCountHash(A, B, C, D));
    }

    /**
     * 暴力搜索 O(n^4)
     * 提交时时间超出限制，须进行优化
     * 考虑空间换时间，通过查找表的方式获取两两数组的计算和
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCountBrute(int[] A, int[] B, int[] C, int[] D) {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    for (int l = 0; l < D.length; l++) {
                        int sum = A[i] + B[j] + C[k] + D[l];
                        if (sum == 0) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 使用Hash表索引两两数组的和的数量，然后遍历两Hash表，累加和为0的组合数量
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCountHash(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> abSum = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                abSum.put(sum, abSum.getOrDefault(sum, 0) + 1);
            }
        }
        Map<Integer, Integer> cdSum = new HashMap<>();
        for (int k = 0; k < C.length; k++) {
            for (int l = 0; l < D.length; l++) {
                int sum = C[k] + D[l];
                cdSum.put(sum, cdSum.getOrDefault(sum, 0) + 1);
            }
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> abSumEntry : abSum.entrySet()) {
            cnt += abSumEntry.getValue() * cdSum.getOrDefault(0 - abSumEntry.getKey(), 0);
        }
        return cnt;
    }

}
