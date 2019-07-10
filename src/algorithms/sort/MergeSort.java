package algorithms.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author nick
 * @date 2019-07-10 星期三 21:58
 **/
public class MergeSort {
    // 逆序对的数量
    private static int numOfReversedPair;

    @Before
    public void init() {
        numOfReversedPair = 0;
    }

    @Test
    public void testMergeSort1() {
        int[] nums = {1, 3, 5, 2, 4, 6};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 5, 6}, sorted));
        Assert.assertEquals(3, numOfReversedPair);
    }

    @Test
    public void testMergeSort2() {
        int[] nums = {1};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        Assert.assertTrue(Arrays.equals(new int[]{1}, sorted));
    }

    @Test
    public void testMergeSort3() {
        int[] nums = {5, 3};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        Assert.assertTrue(Arrays.equals(new int[]{3, 5}, sorted));
        Assert.assertEquals(1, numOfReversedPair);
    }

    @Test
    public void testMergeSort4() {
        int[] nums = {6, 5, 4, 3, 2, 1};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4, 5, 6}, sorted));
        Assert.assertEquals(15, numOfReversedPair);
    }

    /**
     * 归并排序
     *
     * @param src
     * @param start included
     * @param end   included
     * @return
     */
    public int[] mergeSort(int[] src, int start, int end) {
        if (start == end) {
            return new int[]{src[start]};
        }
        int mid = (start + end) / 2;
        int[] sortedLeft = mergeSort(src, start, mid);
        int[] sortedRight = mergeSort(src, mid + 1, end);
        int[] sorted = merge(sortedLeft, sortedRight);
        return sorted;
    }

    /**
     * 归并时统计逆序对数量
     *
     * @param numsa
     * @param numsb
     * @return
     */
    public int[] merge(int[] numsa, int[] numsb) {
        int[] sorted = new int[numsa.length + numsb.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < numsa.length && j < numsb.length) {
            if (numsa[i] < numsb[j]) {
                sorted[k++] = numsa[i++];
            } else {
                sorted[k++] = numsb[j++];

                // 累加逆序对的数量
                numOfReversedPair += numsa.length - i;
            }
        }

        // 直接追加存在剩余数据的数组
        while (i < numsa.length) {
            sorted[k++] = numsa[i++];
        }
        while (j < numsb.length) {
            sorted[k++] = numsb[j++];
        }

        return sorted;
    }
}
