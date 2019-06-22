package leetcode.simple;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-22 星期六 20:42
 **/
public class MergeTwoLists {
    public static void main(String[] args) {
        int[] val1st = {};
        int[] val2nd = {0};
        ListNode list1st = createList(val1st);
        ListNode list2nd = createList(val2nd);
        ListNode mergedList = mergeTwoLists(list1st, list2nd);
        ListNode.printList(mergedList);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;

        ListNode head = null;
        ListNode p;
        ListNode pre = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p = p1;
                p1 = p1.next;
            } else {
                p = p2;
                p2 = p2.next;
            }
            if (head == null) {
                head = p;
            } else {
                pre.next = p;
            }
            pre = p;
        }

        if (p1 != null) {
            pre.next = p1;

        }

        if (p2 != null) {
            pre.next = p2;
        }

        return head;
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
