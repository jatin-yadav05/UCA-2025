/**
 * Implementation of a Min Priority Queue using a binary heap.
 */
public class MinPriorityQueue {
  private int[] heap;
  private int size;
  private int capacity;

  /** Initializes the min priority queue with given capacity. */
  public MinPriorityQueue(int capacity) {
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

  /** Returns the minimum element without removing it. */
  public int peekMin() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return heap[1];
  }

  /** Inserts a new key into the min priority queue. */
  public void insert(int key) {
    if (size == capacity) {
      throw new IllegalStateException("Queue is full");
    }
    heap[++size] = key; // insert at end
    swim(size); // restore heap property
  }

  /** Extracts and returns the minimum element. */
  public int extractMin() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    int min = heap[1];
    swap(1, size);
    size--;
    sink(1);
    return min;
  }

  /** Restores heap order by swimming up. */
  private void swim(int k) {
    while (k > 1 && heap[k / 2] > heap[k]) {
      swap(k, k / 2);
      k = k / 2;
    }
  }

  /** Restores heap order by sinking down. */
  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      if (j < size && heap[j] > heap[j + 1]) {
        j++;
      }
      if (heap[k] <= heap[j]) {
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

  /** Example usage of MinPriorityQueue. */
  public static void main(String[] args) {
    MinPriorityQueue pq = new MinPriorityQueue(10);

    pq.insert(10);
    pq.insert(40);
    pq.insert(20);
    pq.insert(5);

    System.out.println("Peek min: " + pq.peekMin()); // 5

    System.out.println("Extract min elements:");
    while (!pq.isEmpty()) {
      System.out.println(pq.extractMin());
    }

    // Re-insertion demo
    pq.insert(50);
    pq.insert(2);
    System.out.println("Peek min after re-insertion: " + pq.peekMin());
  }
}

