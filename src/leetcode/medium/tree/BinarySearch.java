package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author nick
 * @date 2019-07-26 星期五 20:52
 **/
public class BinarySearch {

    static int count = 0;
    static int result = Integer.MIN_VALUE;

    /**
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * 说明： 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        this.findKth(root, k);
        return result;
    }

    /**
     * 采用中序遍历
     *
     * @param root
     * @param k
     */
    private void findKth(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        if (count > k) {
            return;
        }
        this.findKth(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        this.findKth(root.right, k);
    }


    @Test
    public void testKthSmallest() {
        Integer[] vals = {5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = TreeNode.createBinaryTree(vals);
        int kth = this.kthSmallest(root, 3);
        Assert.assertEquals(3, kth);
    }
}
