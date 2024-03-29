package leetc.top;

public class P19_RemoveNthNodeFromEndOfList {
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head, pre = null;
        boolean enough = false;
        while (cur != null) {
            n--;
            if (n <= 0) {
                if (n == 0) {
                    enough = true;
                } else if (n == -1) {
                    pre = head;
                } else {
                    pre = pre.next;
                }
            }
            cur = cur.next;
        }
        if (!enough) {
            return head;
        }
        if (pre == null) {
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
