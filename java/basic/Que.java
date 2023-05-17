package basic;

public class Que {
    public static class Node<V> {
        public V val;
        public Node<V> next;

        public Node(V v) {
            this.val = v;
        }
    }

    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
        }

        public void offer(V val) {
            Node<V> cur = new Node<V>(val);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }
    }

    public static class MyStack<V> {
        private Node<V> head;
        private int size;
        public MyStack(){
        }
        public void push(V val){
            Node<V> cur = new Node<>(val);
            if(head == null){
                head=cur;
            } else {
                cur.next=head;
                head = cur;
            }
            size++;
        }
        public V pop(){
            V ans = null;
            if(head !=null){
                ans = head.val;
                head=head.next;
                size--;
            }
            return ans;
        }
    }

    public static void main(String[] args) {

    }
}
