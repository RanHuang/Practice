package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 102. 二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）
 * <p>
 * 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author nick
 * @date 2019-07-24 星期三 22:04
 **/
public class LevelOrder {

    @Test
    public void testLevelOrder() {
        Integer[] vals = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.createBinaryTree(vals);
        List<List<Integer>> result = this.levelOrder(root);
        System.out.println(result);
    }

    /**
     * 使用队列进行层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // null作为第一层节点的边界
        queue.add(null);

        while (!queue.isEmpty() && queue.peek() != null) {
            List<Integer> levelValues = new ArrayList<>();
            TreeNode node = queue.poll();
            while (node != null) {
                levelValues.add(node.val);
                // 将下层的孩子节点加入队列中去
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                node = queue.poll();
            }
            // null作为层次边界
            queue.add(null);
            result.add(levelValues);
        }

        return result;
    }

    @Test
    public void testZigzagLevelOrder() {
        Integer[] vals = new Integer[]{1, 2, 3, 4, null, null, 5};
        TreeNode root = TreeNode.createBinaryTree(vals);
        List<List<Integer>> result = this.zigzagLevelOrder(root);
        System.out.println(result);
    }

    /**
     * 二叉树的锯齿形层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();

        // 层次遍历顺序，左-->右
        boolean rightoLeft = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // null作为第一层节点的边界
        queue.add(null);
        while (queue.peek() != null) {
            List<Integer> levelValues = new ArrayList<>();

            TreeNode node = queue.poll();
            while (node != null) {
                levelValues.add(node.val);
                // 将下层的孩子节点加入队列中去
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                node = queue.poll();
            }
            queue.add(null);

            // 逆转层次访问方向
            rightoLeft = !rightoLeft;
            // 逆转序列
            if (rightoLeft) {
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < levelValues.size(); i++) {
                    stack.push(levelValues.get(i));
                }
                levelValues.clear();
                while (!stack.isEmpty()) {
                    levelValues.add(stack.pop());
                }
            }
            result.add(levelValues);
        }

        return result;
    }
}
