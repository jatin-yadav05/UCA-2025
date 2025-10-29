public class DynamicStackUsingArray{
    private int[] arr;
    private int size;
    private int capacity;

    public DynamicStackUsingArray() {
        capacity = 2;
        arr = new int[capacity];
        size = 0;
    }

    public void push(int value) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        arr[size++] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        int val = arr[--size];
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return arr[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        capacity = newCapacity;
    }

    public static void main(String[] args) {
        DynamicStackUsingArray stack = new DynamicStackUsingArray();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        assert stack.peek() == 30 : "Peek failed";
        assert stack.pop() == 30 : "Pop failed";
        assert stack.pop() == 20 : "Pop failed";
        assert stack.getSize() == 1 : "Size check failed";

        stack.push(40);
        stack.push(50);
        assert stack.getSize() == 3 : "Push size failed";
        assert stack.peek() == 50 : "Peek after push failed";

        stack.pop();
        stack.pop();
        stack.pop();
        assert stack.isEmpty() : "Stack should be empty";

        System.out.println("All assertions passed!");
    }
}

