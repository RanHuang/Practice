package leetcode.structure;

/**
 * @author nick
 * @date 2019-06-22 星期六 20:40
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
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

    public static int[] toArray(ListNode listNode) {
        if (listNode == null) {
            return new int[0];
        }
        int count = 0;
        ListNode p = listNode;
        while (p != null) {
            count++;
            p = p.next;
        }
        int[] values = new int[count];
        p = listNode;
        count = 0;
        while (p != null) {
            values[count++] = p.val;
            p = p.next;
        }
        return values;
    }

    public static ListNode createList(int[] vals) {
        if (null == vals || vals.length == 0) {
            return null;
        }

        ListNode head = new ListNode(vals[0]);

        ListNode pre = head;
        for (int i = 1; i < vals.length; i++) {
            ListNode node = new ListNode(vals[i]);
            pre.next = node;
            pre = node;
        }
        return head;
    }
}
