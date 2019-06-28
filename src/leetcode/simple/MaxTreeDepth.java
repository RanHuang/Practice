package leetcode.simple;

import javafx.util.Pair;
import leetcode.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-28 星期五 21:33
 **/
public class MaxTreeDepth {
    public static void main(String[] args) {
        Integer[] vals = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.createBinaryTree(vals);
        TreeNode.showTreeNodeVal(root);

        int maxDepth1 = maxDepthRecursion(root);
        System.out.println(maxDepth1);
        int maxDepth2 = maxDepthIteration(root);
        System.out.println(maxDepth2);
    }

    /**
     * 算法： 深度优先搜索
     *
     * @param root
     * @return
     */
    public static int maxDepthRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthRecursion(root.left);
        int rightDepth = maxDepthRecursion(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代方式
     *
     * @param root
     * @return
     */
    public static int maxDepthIteration(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> nodePair = queue.poll();
            TreeNode node = nodePair.getKey();
            int depth = nodePair.getValue();
            if (depth > maxDepth) {
                maxDepth = depth;
            }
            if (node.left != null) {
                queue.add(new Pair<>(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.add(new Pair<>(node.right, depth + 1));
            }
        }

        return maxDepth;
    }
}
