class CircularLinkedList<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node current;

    public CircularLinkedList() {
        head = null;
        current = head;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node tail = head;
            while (tail.next != head) {
                tail = tail.next;
            }
            tail.next = newNode;
            newNode.next = head;
        }
        current = head;
    }

    public T getCurrent() {
        return current.data;
    }

    public void moveNext() {
        if (current != null) {
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}