package algorithms.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author nick
 * @date 2019-07-10 星期三 21:58
 **/
public class MergeSort {

    @Test
    public void testMergeSort1() {
        int[] nums = {1, 3, 5, 2, 4, 6};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(sorted));
    }

    @Test
    public void testMergeSort2() {
        int[] nums = {1};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(sorted));
    }

    @Test
    public void testMergeSort3() {
        int[] nums = {5, 3};
        int[] sorted = mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(sorted));
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
