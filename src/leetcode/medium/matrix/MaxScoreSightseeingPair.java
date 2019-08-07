package leetcode.medium.matrix;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1014. 最佳观光组合
 * max(A[i] + A[j] + i - j),其中i<j
 *
 * @author nick
 * @date 2019-08-07 星期三 21:25
 **/
public class MaxScoreSightseeingPair {
    @Test
    public void test() {
        Assert.assertEquals(18, this.maxScoreSightseeingPair(new int[]{6, 9, 10, 5, 9, 10, 4, 5}));
        Assert.assertEquals(11, this.maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
        Assert.assertEquals(3, this.maxScoreSightseeingPair(new int[]{2, 2, 2}));
    }

    public int maxScoreSightseeingPair(int[] A) {
        int[] plus = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            plus[i] = A[i] + i;
        }
        int[] minus = new int[A.length];
        for (int j = 0; j < A.length; j++) {
            minus[j] = A[j] - j;
        }

        int maxValue = Integer.MIN_VALUE;
        int maxP = plus[0];
        for (int i = 1; i < plus.length; i++) {
            maxValue = Math.max(maxValue, maxP + minus[i]);
            maxP = Math.max(maxP, plus[i]);
        }

        return maxValue;
    }
}
