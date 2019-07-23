package leetcode.simple.tree;

import leetcode.structure.TreeNode;
import org.junit.Test;

/**
 * 226. 翻转二叉树
 *
 * @author nick
 * @date 2019-07-23 星期二 12:20
 **/
public class InvertTree {
    @Test
    public void testInvertTree() {
        Integer[] vals = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = TreeNode.createBinaryTree(vals);
        TreeNode inverted = this.invertTree(root);
    }

    public TreeNode invertTree(TreeNode root) {
        this.invert(root);
        return root;
    }

    /**
     * 采用递归算法，依次交换当前节点的左右子节点
     *
     * @param node
     */
    private void invert(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = right;
        node.right = left;

        this.invert(right);
        this.invert(left);
    }
}
