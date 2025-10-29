public class DynamicQueue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public DynamicQueue() {
        capacity = 2;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        rear = (rear + 1) % capacity;
        arr[rear] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        int val = arr[front];
        front = (front + 1) % capacity;
        size--;
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(front + i) % capacity];
        }
        arr = newArr;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }

    public static void main(String[] args) {
        DynamicQueue queue = new DynamicQueue();

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

        System.out.println("DynamicQueue: All assertions passed!");
    }
}

