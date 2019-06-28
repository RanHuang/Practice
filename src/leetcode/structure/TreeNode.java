package leetcode.structure;

import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author nick
 * @date 2019-06-28 星期五 21:32
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode createBinaryTree(Integer[] vals) {
        if (vals == null || vals.length == 0) {
            return null;
        }

        TreeNode[] treeNodes = new TreeNode[vals.length];
        for (int index = 0; index < vals.length; index++) {
            if (vals[index] == null) {
                treeNodes[index] = null;
            } else {
                treeNodes[index] = new TreeNode(vals[index]);
            }
        }

        for (int i = 0; i < treeNodes.length / 2; i++) {
            treeNodes[i].left = treeNodes[2 * i + 1];
            treeNodes[i].right = treeNodes[2 * i + 2];
        }
        return treeNodes[0];
    }

    public static void showTreeNodeVal(TreeNode root) {
        List<String> result = new LinkedList<>();
        before(root, result);
        String dispData = "[" + StringUtils.join(result, ",");
        System.out.println(dispData);
    }

    private static void before(TreeNode root, List<String> lstData) {
        if (root == null) {
            lstData.add(null);
            return;
        }
        lstData.add(String.valueOf(root.val));
        before(root.left, lstData);
        before(root.right, lstData);
    }
}
