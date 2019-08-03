package interview.bytedance;

/**
 * 字节跳动第二面，手写代码题
 * 根据前序遍历和中序遍历还原二叉树
 * 根据获得的前序遍历和中序遍历结果输出一颗二叉树
 * 输入描述
 * 第一行为二叉树前序遍历的结果
 * 第二行为二叉树中序遍历的结果
 * <p>
 * 输出描述
 * 二叉树后续遍历的结果
 *
 * @author nick
 * @date 2019-08-02 星期五 13:12
 **/
public class RoundSecond {
    public static void main(String[] args) {
        RoundSecond treeBuild = new RoundSecond();
        treeBuild.buildTree();
    }

    public void buildTree() {
        char[] before = {'G', 'D', 'A', 'F', 'E', 'M', 'H', 'Z'};
        char[] inorder = {'A', 'D', 'E', 'F', 'G', 'H', 'M', 'Z'};
        Node root = this.build(before, 0, before.length - 1, inorder, 0, inorder.length - 1);
        // 后序遍历输出树内容
        this.afterPrint(root);
        // AEFDHZMG
    }

    private void afterPrint(Node root) {
        if (root == null) {
            return;
        }
        this.afterPrint(root.left);
        this.afterPrint(root.right);
        System.out.print(root.data);
    }

    private Node build(char[] before, int bstart, int bend, char[] inorder, int istart, int iend) {
        if (bstart > bend) {
            return null;
        }

        // 创建当前子树的根结点
        Node root = new Node(before[bstart], null, null);
        if (bstart == bend) {
            return root;
        }
        // 通过前序序列的第一个结点确定根结点
        // 在中序序列中确定根节的位置
        int indexInorder = this.search(inorder, before[bstart]);

        // 将序列划分为两个部分，递归调用该方法分别生存左子树根结点和右子树根结点
        Node left = this.build(before, bstart + 1, bstart + indexInorder - istart, inorder, istart, indexInorder - 1);
        Node right = this.build(before, bstart + indexInorder - istart + 1, bend, inorder, indexInorder + 1, iend);

        root.left = left;
        root.right = right;
        // 返回根结点
        return root;
    }

    // 搜索字符在给定序列中的位置
    private int search(char[] arrays, char ch) {
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == ch) {
                return i;
            }
        }
        return -1;
    }


    private static class Node {
        public char data;
        Node left;
        Node right;

        public Node(char data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
