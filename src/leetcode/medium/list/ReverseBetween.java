package leetcode.medium.list;

import leetcode.structure.ListNode;
import org.junit.Test;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * @author nick
 * @date 2019-08-04 星期日 07:15
 **/
public class ReverseBetween {
    @Test
    public void testReverseBetween() {
        int[] vals = {1, 2, 3, 4, 5};
        ListNode head = ListNode.createList(vals);
        ListNode reversed = this.reverseBetween(head, 1, 5);
        ListNode.printList(reversed);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }

        ListNode tail1st = null;
        ListNode head2nd = null;
        ListNode tail2nd = null;
        ListNode head3rd = null;

        ListNode pre = null;
        ListNode current = head;
        ListNode after = current.next;
        int index = 0;
        // 遍历整个链表
        while (current != null) {
            index++;

            // 遍历至m,n结点时单独处理三段链表的首尾指针
            if (index == m) {
                tail1st = pre;
                tail2nd = current;
            } else if (index > n) {
                head2nd = pre;
                head3rd = current;
                // 链表翻转完毕退出while循环
                break;
            }

            // 边界条件，翻转最后一个结点
            if (current.next == null) {
                current.next = pre;
                head3rd = null;
                head2nd = current;
                break;
            }

            if (index > m) {
                // 翻转链表
                after = current.next;
                current.next = pre;

                pre = current;
                current = after;
                after = after.next;
            } else {
                pre = current;
                current = after;
                after = after.next;
            }
        }

        // 构建新链表
        if (m == 1) {
            head = head2nd;
        } else {
            tail1st.next = head2nd;
        }
        tail2nd.next = head3rd;

        return head;
    }
}
