package leetcode.simple.list;

import leetcode.structure.ListNode;

/**
 * 160. 相交链表
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-07-23 星期二 12:35
 **/
public class IntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int cntA = 0;
        ListNode p = headA;
        while (p != null) {
            cntA++;
            p = p.next;
        }
        int cntB = 0;
        ListNode q = headB;
        while (q != null) {
            cntB++;
            q = q.next;
        }
        // 确保链表A始终不比链表B短
        if (cntA < cntB) {
            ListNode nodetmp = headA;
            headA = headB;
            headB = nodetmp;
            int itmp = cntA;
            cntA = cntB;
            cntB = itmp;
        }

        // 链表长度差值
        int diff = cntA - cntB;
        // 根据差值在长链表先跑diffbu
        p = headA;
        while (diff > 0) {
            p = p.next;
            diff--;
        }

        q = headB;
        // 同时向后迭代，p==q时为相交节点
        while (p != q && p != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
