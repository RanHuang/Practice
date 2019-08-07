package leetcode.medium.tree;

import leetcode.structure.TreeNode;

/**
 * @author nick
 * @date 2019-08-07 星期三 20:36
 **/
public class Flatten {

    /**
     * 后序递归遍历
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        this.flatten(root.left);
        this.flatten(root.right);

        if (root.left != null) {
            // 寻找左子树链表的最后一个结点
            TreeNode pre = root.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            // 将右子树链表链接到左子树链表末尾
            pre.right = root.right;
            // 将根结点的右指针指向后续链表的头结点
            root.right = root.left;
            // 将根结点的左指针置为null
            root.left = null;
        }
    }

}
