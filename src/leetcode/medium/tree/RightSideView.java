package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * @author nick
 * @date 2019-08-05 星期一 21:00
 **/
public class RightSideView {

    @Test
    public void testRightSideView() {
        Integer[] vals = new Integer[]{1, 2, 3, null, 5, null, 4};
        TreeNode root = TreeNode.createBinaryTree(vals);
        List<Integer> rightSide = this.rightSideView(root);
        System.out.println(rightSide);
    }

    /**
     * 算法：层次遍历二叉树，搜集每层的最后一个结点
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> lstData = new ArrayList<>();

        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.add(root);
        levelQueue.add(null);
        TreeNode preNode = levelQueue.poll();
        if (preNode.left != null) {
            levelQueue.add(preNode.left);
        }
        if (preNode.right != null) {
            levelQueue.add(preNode.right);
        }

        while (!levelQueue.isEmpty()) {
            TreeNode current = levelQueue.poll();
            if (preNode == null && current == null) {
                break;
            }

            if (current == null) {
                lstData.add(preNode.val);
                // 当访问到上一层的结束标志结点null时，为下一层添加层次结束的标识结点
                levelQueue.add(null);
            } else {
                if (current.left != null) {
                    levelQueue.add(current.left);
                }
                if (current.right != null) {
                    levelQueue.add(current.right);
                }
            }
            preNode = current;
        }

        return lstData;
    }
}
