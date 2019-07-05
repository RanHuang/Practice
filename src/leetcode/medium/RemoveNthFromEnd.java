package leetcode.medium;

import leetcode.structure.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-05 星期五 12:23
 **/
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.createList(nums);
        int n = 4;
        ListNode head = removeNthFromEnd(listNode, n);
        ListNode.printList(head);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode before = head;
        ListNode after = head;
        int i = n;
        while (i > 0 && before != null) {
            before = before.next;
            i--;
        }
        if (before == null) {
            // 删除表头节点
            head = head.next;
        } else {
            while (before.next != null) {
                after = after.next;
                before = before.next;
            }
            // 删除倒数第N个节点
            after.next = after.next.next;
        }
        return head;
    }
}
