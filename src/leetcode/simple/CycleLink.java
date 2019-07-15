package leetcode.simple;

import leetcode.structure.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-21 星期五 21:05
 * 解法1. 快慢指针方法
 * 时间复杂度O(n)
 * 空间复杂度O(1)
 * 解法2. 遍历链表Set重复值判定
 * 时间复杂度O(n)
 * 空间复杂度O(n)
 **/
public class CycleLink {
    @Test
    public void testHasCycle() {
        ListNode list = createList();
        boolean hasCycle = hasCycle(list);
        System.out.println(hasCycle);
    }

    /**
     * 快慢指针方法
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            // 空链表或仅含有一个节点的链表不为环形链表
            return false;
        }
        // 环形链表判断转换为环形操场跑步的追击问题
        // fast哨兵每次前进两步
        ListNode fast = head.next;
        // slow哨兵每次前进一步
        ListNode slow = head;
        // 如果存在环则fast会追上slow
        while (fast != slow) {
            slow = slow.next;

            fast = fast.next;
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next;
        }
        return true;
    }

    /**
     * 3,2,0,-4
     *
     * @return
     */
    private static ListNode createList() {
        ListNode node1st = new ListNode(3);
        ListNode node2nd = new ListNode(2);
        ListNode node3rd = new ListNode(0);
        ListNode node4th = new ListNode(-4);
        node1st.next = node2nd;
        node2nd.next = node3rd;
        node3rd.next = node4th;
        node4th.next = node2nd;
        return node1st;
    }

    @Test
    public void testDetectCycle() {
        ListNode list = createList();
        ListNode node = detectCycle(list);
        Assert.assertEquals(2, node.val);
    }

    /**
     * 142. 环形链表 II
     * <p>
     * Floyd 算法
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            // 空链表或仅含有一个节点的链表不为环形链表
            return null;
        }
        // 环形链表判断转换为环形操场跑步的追击问题
        // fast哨兵每次前进两步
        ListNode fast = head.next;
        // slow哨兵每次前进一步
        ListNode slow = head;
        // 如果存在环则fast会追上slow
        while (fast != slow) {
            slow = slow.next;

            fast = fast.next;
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next;
        }

        // 从头节点与相遇节点的下一个节点开始向后移动相同数量节点后相遇的节点即为入环点
        ListNode p = head;
        ListNode q = fast.next;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return q;
    }
}
