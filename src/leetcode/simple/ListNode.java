package leetcode.simple;

/**
 * @author nick
 * @date 2019-06-22 星期六 20:40
 **/
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    /**
     * 顺序输出节点数据
     *
     * @param listNode
     */
    public static void printList(ListNode listNode) {
        if (listNode == null) {
            System.out.println("List is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(listNode.val);

        ListNode node = listNode.next;
        while (node != null) {
            sb.append("->").append(node.val);
            node = node.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
