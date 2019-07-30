package leetcode.simple.list;

import leetcode.structure.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 206. 反转链表
 *
 * @author nick
 * @date 2019-07-30 星期二 16:38
 **/
public class ReverseList {
    @Test
    public void testReverseList() {
        int[] vals = {1, 2, 3, 4, 5};
        ListNode head = ListNode.createList(vals);
        Assert.assertTrue(Arrays.equals(new int[]{5, 4, 3, 2, 1}, ListNode.toArray(this.reverseList(head))));
    }

    /**
     * 迭代法
     * 时间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode p = head.next;
        while (p != null) {
            ListNode pp = pre;
            pre = p;
            p = p.next;
            // 反转指针
            pre.next = pp;
        }
        head.next = null;
        head = pre;
        return head;
    }

    @Test
    public void testReverseListRecursive() {
        int[] vals = {1, 2, 3, 4, 5};
        ListNode head = ListNode.createList(vals);
        Assert.assertTrue(Arrays.equals(new int[]{5, 4, 3, 2, 1}, ListNode.toArray(this.reverseListRecursive(head))));
    }

    /**
     * 递归法
     * 时间负载度O(n^2)
     *
     * @param head
     * @return
     */
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tail = head;

        head = this.reverseListRecursive(head.next);
        ListNode p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = tail;
        tail.next = null;

        return head;
    }
}
