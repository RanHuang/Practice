package leetcode.medium.tree;

import leetcode.structure.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 236. 二叉树的最近公共祖先
 *
 * @author nick
 * @date 2019-07-28 星期日 16:42
 **/
public class LowestCommonAncestor {
    @Test
    public void testLowestCommonAncestor() {
        Integer[] vals = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = TreeNode.createBinaryTree(vals);
        TreeNode lowestCommonAncestor1 = this.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4));
        Assert.assertEquals(5, lowestCommonAncestor1.val);

        TreeNode lowestCommonAncestor2 = this.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));
        Assert.assertEquals(3, lowestCommonAncestor2.val);
    }

    /**
     * 递归遍历
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = this.lowestCommonAncestor(root.left, p, q);
        TreeNode right = this.lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    @Test
    public void testLowestCommonAncestorWithParent() {
        Integer[] vals = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = TreeNode.createBinaryTree(vals);
        TreeNode lowestCommonAncestor1 = this.lowestCommonAncestorWithParent(root, new TreeNode(5), new TreeNode(4));
        Assert.assertEquals(5, lowestCommonAncestor1.val);

        TreeNode lowestCommonAncestor2 = this.lowestCommonAncestorWithParent(root, new TreeNode(5), new TreeNode(1));
        Assert.assertEquals(3, lowestCommonAncestor2.val);
    }

    /**
     * 首先通过遍历构建父子结点关系，通过Map存储 当前结点->父结点关系
     * 然后通过遍历分别获取p,q结点，然后根据父子结点关系构建从根结点到当前结点的路径
     * 通过从根结点的迭代遍历，获取最后一个公共父结点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorWithParent(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        this.preOrderForParent(null, root, parentMap);

        TreeNode pNode = this.inorderSearch(root, p);
        Deque<TreeNode> pAncestor = new LinkedList<>();
        pAncestor.push(pNode);
        while (parentMap.get(pNode) != null) {
            pNode = parentMap.get(pNode);
            pAncestor.push(pNode);
        }

        TreeNode qNode = this.inorderSearch(root, q);
        Deque<TreeNode> qAncestor = new LinkedList<>();
        qAncestor.push(qNode);
        while (parentMap.get(qNode) != null) {
            qNode = parentMap.get(qNode);
            qAncestor.push(qNode);
        }

        TreeNode ancestor = null;
        while (!pAncestor.isEmpty() && !qAncestor.isEmpty()) {
            if (pAncestor.peekFirst() == qAncestor.peekFirst()) {
                ancestor = pAncestor.pollFirst();
                qAncestor.pollFirst();
            } else {
                break;
            }
        }
        return ancestor;
    }

    /**
     * 中序遍历搜索指定结点
     *
     * @param root
     * @param node
     * @return
     */
    public TreeNode inorderSearch(TreeNode root, TreeNode node) {
        if (root == null) {
            return null;
        }
        TreeNode left = this.inorderSearch(root.left, node);
        if (left != null) {
            return left;
        }
        if (root.val == node.val) {
            return root;
        }
        return this.inorderSearch(root.right, node);
    }

    /**
     * 递归前序遍历构建父指针关系
     *
     * @param parent
     * @param root
     * @param parentMap
     */
    public void preOrderForParent(TreeNode parent, TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) {
            return;
        }
        parentMap.put(root, parent);
        this.preOrderForParent(root, root.left, parentMap);
        this.preOrderForParent(root, root.right, parentMap);
    }

}
