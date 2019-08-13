package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 230. 二叉搜索树中第K小的元素
 * 1008. 先序遍历构造二叉树
 *
 * @author nick
 * @date 2019-07-26 星期五 20:52
 **/
public class BinarySearch {

    static int count = 0;
    static int result = Integer.MIN_VALUE;

    /**
     * 230. 二叉搜索树中第K小的元素
     * <p>
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

    /**
     * 1008. 先序遍历构造二叉树
     * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = this.bst(preorder, 0, preorder.length - 1);
        return root;
    }

    private TreeNode bst(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        if (start == end) {
            return root;
        }

        // 确定右子树的前序遍历区间
        int rightStart = start + 1;
        while (rightStart <= end) {
            if (preorder[start] > preorder[rightStart]) {
                rightStart++;
            } else {
                break;
            }
        }

        TreeNode left = this.bst(preorder, start + 1, rightStart - 1);
        TreeNode right = this.bst(preorder, rightStart, end);
        root.left = left;
        root.right = right;
        return root;
    }

    @Test
    public void testBstFromPreorder() {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = this.bstFromPreorder(preorder);
        TreeNode.showTreeNodeVal(root);
    }
}
