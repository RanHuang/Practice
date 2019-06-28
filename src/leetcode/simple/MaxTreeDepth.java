package leetcode.simple;

import leetcode.structure.TreeNode;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author nick
 * @date 2019-06-28 星期五 21:33
 **/
public class MaxTreeDepth {
    public static void main(String[] args) {
        Integer[] vals = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.createBinaryTree(vals);
        TreeNode.showTreeNodeVal(root);

        int maxDepth = maxDepth(root);
        System.out.println(maxDepth);
    }

    /**
     * 算法： 深度优先搜索
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
