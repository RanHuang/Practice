package leetcode.medium.list;

import leetcode.structure.ListNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * @author nick
 * @date 2019-08-10 星期六 17:36
 **/
public class DeleteDuplicates {
    @Test
    public void testDeleteDuplicates() {
        int[] vals = {1, 2, 3, 3, 4, 4, 5, 5};
        ListNode head = ListNode.createList(vals);
        ListNode noDuplicate = this.deleteDuplicates(head);
        int[] ret = ListNode.toArray(noDuplicate);
        Assert.assertTrue(Arrays.equals(ret, new int[]{1, 2}));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 增加头结点哨兵
        ListNode pivot = new ListNode(Integer.MAX_VALUE);
        pivot.next = head;
        head = pivot;
        //
        ListNode pre = head;
        ListNode p = head.next;
        boolean isToDelete = false;
        while (p.next != null) {
            int pVal = p.val;
            if (pVal == p.next.val) {
                // 结点数值相等需删除
                isToDelete = true;
                p = p.next;
            } else {
                if (isToDelete) {
                    pre.next = p.next;
                    isToDelete = false;
                    p = p.next;
                } else {
                    pre = p;
                    p = p.next;
                }
            }
        }
        // 尾结点需删除的情况特殊处理
        if (p.next == null && isToDelete) {
            if (pre.next.val == p.val) {
                pre.next = p.next;
            } else {
                pre.next = p;
            }
        }

        // 去掉哨兵头结点
        head = pivot.next;
        return head;
    }
}
