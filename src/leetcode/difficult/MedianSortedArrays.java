package leetcode.difficult;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-29 星期六 20:43
 **/
public class MedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {3};
        double val = findMedianSortedArrays(nums1, nums2);
        System.out.println(val);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            throw new RuntimeException("Both array are empty.");
        }
        if (nums1 == null || nums1.length == 0) {
            int s = (nums2.length - 1) / 2;
            int e = nums2.length / 2;
            return (nums2[s] + nums2[e]) / 2.0;
        }
        if (nums2 == null || nums2.length == 0) {
            int s = (nums1.length - 1) / 2;
            int e = nums1.length / 2;
            return (nums1[s] + nums1[e]) / 2.0;
        }

        int length = nums1.length + nums2.length;
        int halfLength = length / 2;
        int p1 = 0;
        int p2 = 0;

        int[] sortedArray = new int[length];
        int i = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                sortedArray[i++] = nums1[p1++];
            } else {
                sortedArray[i++] = nums2[p2++];
            }
        }

        while (p1 < nums1.length) {
            sortedArray[i++] = nums1[p1++];
        }
        while (p2 < nums2.length) {
            sortedArray[i++] = nums2[p2++];
        }

        if (length % 2 == 1) {
            return sortedArray[halfLength];
        } else {
            return (sortedArray[halfLength - 1] + sortedArray[halfLength]) / 2.0;
        }
    }
}
