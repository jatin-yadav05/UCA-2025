/**
 * Implementation of a Max Priority Queue using a binary heap.
 */
public class MaxPriorityQueue {
  private int[] heap;
  private int size;
  private int capacity;

  /** Initializes the max priority queue with given capacity. */
  public MaxPriorityQueue(int capacity) {
    this.capacity = capacity;
    this.heap = new int[capacity + 1]; // 1-based indexing
    this.size = 0;
  }

  /** Returns true if the queue is empty. */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Returns the current number of elements. */
  public int size() {
    return size;
  }

  /** Returns the maximum element without removing it. */
  public int peekMax() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return heap[1];
  }

  /** Inserts a new key into the max priority queue. */
  public void insert(int key) {
    if (size == capacity) {
      throw new IllegalStateException("Queue is full");
    }
    heap[++size] = key; // insert at end
    swim(size); // restore heap property
  }

  /** Extracts and returns the maximum element. */
  public int extractMax() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    int max = heap[1];
    swap(1, size);
    size--;
    sink(1);
    return max;
  }

  /** Restores heap order by swimming up. */
  private void swim(int k) {
    while (k > 1 && heap[k / 2] < heap[k]) {
      swap(k, k / 2);
      k = k / 2;
    }
  }

  /** Restores heap order by sinking down. */
  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && heap[j] < heap[j + 1]) {
        j++;
      }
      if (heap[k] >= heap[j]) {
        break;
      }
      swap(k, j);
      k = j;
    }
  }

  /** Swaps two elements in the heap. */
  private void swap(int i, int j) {
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  /** Example usage of MaxPriorityQueue. */
  public static void main(String[] args) {
    MaxPriorityQueue pq = new MaxPriorityQueue(10);

    pq.insert(10);
    pq.insert(40);
    pq.insert(20);
    pq.insert(50);

    System.out.println("Peek max: " + pq.peekMax()); // 50

    System.out.println("Extract max elements:");
    while (!pq.isEmpty()) {
      System.out.println(pq.extractMax());
    }

    // Re-insertion demo
    pq.insert(5);
    pq.insert(100);
    System.out.println("Peek max after re-insertion: " + pq.peekMax());
  }
}

