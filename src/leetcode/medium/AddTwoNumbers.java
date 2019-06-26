package leetcode.medium;

import leetcode.structure.ListNode;

/**
 * 2. 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 输入：(1 -> 8) + (0)
 * 输出：1 -> 8
 * 原因：81 + 0 = 81
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author nick
 * @date 2019-06-26 星期三 21:25
 **/
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode list1st = ListNode.createList(new int[]{3, 7});
        ListNode list2nd = ListNode.createList(new int[]{9, 2});
        ListNode listSum = addTwoNumbers(list1st, list2nd);
        ListNode.printList(listSum);
    }

    /**
     * 模拟按位累加，注意处理进位情况
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // 结果链表头指针
        ListNode head = null;
        // 结果链表尾指针
        ListNode tail = null;

        ListNode p1 = l1;
        ListNode p2 = l2;

        // 进位数值
        int next = 0;
        while (p1 != null && p2 != null) {
            int sum = p1.val + p2.val + next;
            ListNode node = new ListNode(sum % 10);
            next = sum / 10;
            if (head == null) {
                tail = head = node;
            } else {
                tail.next = node;
                tail = node;
            }

            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode left = null;
        if (p1 != null) {
            left = p1;
        } else if (p2 != null) {
            left = p2;
        }

        if (left == null) {
            if (next > 0) {
                left = new ListNode(next);
                tail.next = left;
            }
        } else {
            if (left != null) {
                // 将结果链表与剩余链表链接起来
                tail.next = left;
            }
            if (next > 0) {
                // 存在进位值则循环累加处理可能出现的进位情况
                while (left != null) {
                    int sum = left.val + next;
                    left.val = sum % 10;
                    tail = left;
                    left = left.next;

                    next = sum / 10;
                    if (next == 0) {
                        // 无需进位则停止计算
                        break;
                    }
                }
                if (next > 0) {
                    tail.next = new ListNode(next);
                }
            }
        }
        return head;
    }
}
