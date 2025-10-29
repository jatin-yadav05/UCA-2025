public class LinkedListStack {
    private Node top;
    private int size;

    private static class Node {
        int value;
        Node next;
        Node(int value) {
            this.value = value;
        }
    }

    public LinkedListStack() {
        top = null;
        size = 0;
    }

    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        int val = top.value;
        top = top.next;
        size--;
        return val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();

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

