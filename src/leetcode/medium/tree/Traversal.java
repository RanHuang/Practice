package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Test;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * @author nick
 * @date 2019-07-25 星期四 13:56
 **/
public class Traversal {
    @Test
    public void testBuildTree() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = this.buildTree(preorder, inorder);
        TreeNode.showTreeNodeVal(root);
    }

    /**
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 注意:可以假设树中没有重复的元素。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = this.build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }

    private TreeNode build(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
        if (pstart > pend || istart > iend) {
            return null;
        }
        TreeNode parent = new TreeNode(preorder[pstart]);
        int inIndex = this.search(preorder[pstart], inorder, istart, iend);
        int length = inIndex - istart;
        TreeNode left = this.build(preorder, pstart + 1, pstart + length, inorder, istart, inIndex - 1);
        parent.left = left;
        TreeNode right = this.build(preorder, pstart + length + 1, pend, inorder, inIndex + 1, iend);
        parent.right = right;
        return parent;
    }

    /**
     * 在无序数组中的搜索给定数值的位置
     *
     * @param vals
     * @param start
     * @param end
     * @return
     */
    private int search(int val, int[] vals, int start, int end) {
        if (start > end) {
            return -1;
        }
        for (int index = start; index <= end; index++) {
            if (vals[index] == val) {
                return index;
            }
        }
        return -1;
    }
}
