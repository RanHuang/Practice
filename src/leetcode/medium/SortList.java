package leetcode.medium;

import leetcode.structure.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author nick
 * @date 2019-07-21 星期日 21:43
 **/
public class SortList {
    @Test
    public void testSortList() {
        Assert.assertTrue(Arrays.equals(new int[]{-1, 0, 3, 4, 5}, ListNode.toArray(this.sortList(ListNode.createList(new int[]{-1, 5, 3, 4, 0})))));
        Assert.assertTrue(Arrays.equals(new int[]{-1, 0, 3, 4, 5, 6}, ListNode.toArray(this.sortList(ListNode.createList(new int[]{6, -1, 5, 3, 4, 0})))));
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4}, ListNode.toArray(this.sortList(ListNode.createList(new int[]{4, 2, 1, 3})))));
        Assert.assertTrue(Arrays.equals(new int[]{1, 2, 3, 4}, ListNode.toArray(this.sortList(ListNode.createList(new int[]{1, 2, 3, 4})))));
        Assert.assertTrue(Arrays.equals(new int[]{-1, 6}, ListNode.toArray(this.sortList(ListNode.createList(new int[]{-1, 6})))));
    }

    /**
     * 要求:O(n log n) 时间复杂度和常数级空间复杂度
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        ListNode start = head.next;
        // 对除头节点以外的其它链表节点进行排序
        this.sort(head, start, end);
        // 将头节点插入到有序链表的合适位置上
        if (head.val > head.next.val) {
            ListNode p = head;
            head = head.next;

            ListNode cur = head;
            ListNode prev = cur;
            while (cur != null && cur.val < p.val) {
                prev = cur;
                cur = cur.next;
            }
            p.next = cur;
            prev.next = p;
        }

        return head;
    }

    private void sort(ListNode prev, ListNode start, ListNode end) {
        if (start == null || start == end) {
            return;
        }
        ListNode prevS = prev;
        ListNode pivotS = end;

        while (start != pivotS) {
            if (start.val <= pivotS.val) {
                prev = start;
                start = start.next;
            } else {
                prev.next = start.next;
                start.next = end.next;
                end.next = start;
                start = prev.next;
                end = end.next;
            }
        }
        // 获取pivotS的前一个节点
        ListNode prevOfpivotS = prevS;
        while (prevOfpivotS.next != pivotS) {
            prevOfpivotS = prevOfpivotS.next;
        }
        if (prevOfpivotS != prevS) {
            this.sort(prevS, prevS.next, prevOfpivotS);
        }
        if (pivotS != end) {
            this.sort(pivotS, pivotS.next, end);
        }
    }
}
