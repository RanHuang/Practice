package leetcode.medium.list;

import leetcode.structure.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 给定 1->2->3->4, 返回 2->1->4->3.
 *
 * @author nick
 * @date 2019-08-05 星期一 20:36
 **/
public class SwapPairs {
    @Test
    public void testSwapPairs() {
        int[] vals = {1, 2, 3, 4};
        ListNode head = ListNode.createList(vals);
        ListNode swapedList = this.swapPairs(head);
        int[] swapData = ListNode.toArray(swapedList);
        Assert.assertTrue(Arrays.equals(swapData, new int[]{2, 1, 4, 3}));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode p = head;
        while (p != null && p.next != null) {
            ListNode after = p.next;

            p.next = after.next;
            after.next = p;
            if (pre == null) {
                head = after;
            } else {
                pre.next = after;
            }
            pre = p;
            p = p.next;
        }

        return head;
    }
}
