package leetcode.structure;

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
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        before(root, sb);
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static void before(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null, ");
            return;
        }
        sb.append(root.val).append(", ");
        before(root.left, sb);
        before(root.right, sb);
    }
}
