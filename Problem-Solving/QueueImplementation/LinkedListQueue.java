public class LinkedListQueue {
    private Node front;
    private Node rear;
    private int size;

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (rear != null) {
            rear.next = newNode;
        }
        rear = newNode;
        if (front == null) {
            front = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        int val = front.value;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return front.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assert queue.peek() == 10 : "Peek failed";
        assert queue.dequeue() == 10 : "Dequeue failed";
        assert queue.dequeue() == 20 : "Dequeue failed";
        assert queue.getSize() == 1 : "Size check failed";

        queue.enqueue(40);
        queue.enqueue(50);
        assert queue.getSize() == 3 : "Enqueue size failed";
        assert queue.peek() == 30 : "Peek after enqueue failed";

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assert queue.isEmpty() : "Queue should be empty";

        System.out.println("LinkedListQueue: All assertions passed!");
    }
}

