import java.util.*;

/**
 * MedianFinder maintains a data stream and allows finding the median efficiently.
 * Uses two heaps approach: max-heap for the left half and min-heap for the right half.
 */
public class MedianFinder {

  private PriorityQueue<Integer> leftMaxHeap; // Max-heap (store smaller half)
  private PriorityQueue<Integer> rightMinHeap; // Min-heap (store larger half)

  /** Initializes the MedianFinder object. */
  public MedianFinder() {
    leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
    rightMinHeap = new PriorityQueue<>();
  }

  /**
   * Adds a number from the data stream.
   *
   * @param num The integer to add.
   */
  public void addNum(int num) {
    // Always add to max-heap first
    leftMaxHeap.offer(num);

    // Ensure ordering property: max(left) <= min(right)
    rightMinHeap.offer(leftMaxHeap.poll());

    // Balance sizes: left can have 1 more element than right
    if (leftMaxHeap.size() < rightMinHeap.size()) {
      leftMaxHeap.offer(rightMinHeap.poll());
    }
  }

  /**
   * Finds the median of all elements so far.
   *
   * @return The median value.
   */
  public double findMedian() {
    if (leftMaxHeap.size() == rightMinHeap.size()) {
      return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
    } else {
      return leftMaxHeap.peek();
    }
  }

  /**
   * Example usage of MedianFinder with simple assertions.
   */
  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(1);
    medianFinder.addNum(2);
    assert Math.abs(medianFinder.findMedian() - 1.5) < 1e-5 : "Test 1 failed";

    medianFinder.addNum(3);
    assert Math.abs(medianFinder.findMedian() - 2.0) < 1e-5 : "Test 2 failed";

    medianFinder.addNum(4);
    medianFinder.addNum(5);
    assert Math.abs(medianFinder.findMedian() - 3.0) < 1e-5 : "Test 3 failed";

    System.out.println("All tests passed.");
  }
}
