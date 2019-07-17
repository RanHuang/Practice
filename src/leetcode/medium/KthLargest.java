package leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import util.NumberUtil;

import java.util.Arrays;

/**
 * @author nick
 * @date 2019-07-16 星期二 23:06
 **/
public class KthLargest {

    /**
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestBySort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    @Test
    public void testFindKthLargestBySort() {
        Assert.assertEquals(5, findKthLargestBySort(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assert.assertEquals(4, findKthLargestBySort(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    public void testFindKthLargest() {
        int kth = findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        Assert.assertEquals(5, kth);
        Assert.assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));

        Assert.assertEquals(2, findKthLargest(new int[]{2, 1}, 1));
    }

    /**
     * 采用类似快排的分治思想，通过对序列按大小进行分类快速缩小搜索范围
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int kth = this.findKth(nums, 0, nums.length - 1, k-1);
        return kth;
    }

    private int findKth(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }

        int part = this.partition(nums, start, end);

        if (part < k) {
            return findKth(nums, part + 1, end, k);
        } else if (part > k) {
            return findKth(nums, start, part - 1, k);
        }
        // part == k
        return nums[k];
    }

    /**
     * 根据pivot对序列进行分组，大数在前，小数在后
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int partition(int nums[], int start, int end) {
        if (start == end) {
            return start;
        }
        // 采用类似快排的算法将数据根据pivot分组
        int pivot = nums[start];
        int p = start + 1;
        int q = end;
        while (p < q) {
            while (p <= end && nums[p] > pivot) {
                p++;
            }
            while (q >= start && nums[q] <= pivot) {
                q--;
            }
            if (p < q) {
                NumberUtil.swap(nums, p, nums, q);
            }
        }

        if (q > start && nums[q] > nums[start]) {
            NumberUtil.swap(nums, q, nums, start);
            return q;
        }
        return start;
    }


    @Test
    public void testPartition1() {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int p = this.partition(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        System.out.println(p);
    }

    @Test
    public void testPartition2() {
        int[] nums = {1, 2};
        int p = this.partition(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        System.out.println(p);
    }
}
