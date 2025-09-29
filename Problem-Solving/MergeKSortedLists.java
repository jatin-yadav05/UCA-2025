import java.util.*;

/**
 * MergeKSortedLists merges k sorted linked lists into one sorted linked list.
 */
public class MergeKSortedLists {

  /** Definition for singly-linked list. */
  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  /**
   * Merges k sorted linked lists.
   *
   * @param lists Array of ListNode heads.
   * @return The head of the merged sorted list.
   */
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.val, b.val));

    // Add first node of each list to the heap
    for (ListNode node : lists) {
      if (node != null) {
        minHeap.offer(node);
      }
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();
      current.next = node;
      current = current.next;
      if (node.next != null) {
        minHeap.offer(node.next);
      }
    }

    return dummy.next;
  }

  /**
   * Utility method to create a linked list from an array.
   */
  public static ListNode buildList(int[] arr) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    for (int val : arr) {
      current.next = new ListNode(val);
      current = current.next;
    }
    return dummy.next;
  }

  /**
   * Utility method to convert a linked list to a List of integers.
   */
  public static List<Integer> toList(ListNode head) {
    List<Integer> result = new ArrayList<>();
    while (head != null) {
      result.add(head.val);
      head = head.next;
    }
    return result;
  }

  /**
   * Example usage with assertions.
   */
  public static void main(String[] args) {
    MergeKSortedLists solver = new MergeKSortedLists();

    ListNode[] lists1 = new ListNode[] {
        buildList(new int[] {1, 4, 5}),
        buildList(new int[] {1, 3, 4}),
        buildList(new int[] {2, 6})
    };
    List<Integer> result1 = toList(solver.mergeKLists(lists1));
    assert result1.equals(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6)) : "Test 1 failed";

    ListNode[] lists2 = new ListNode[] {};
    List<Integer> result2 = toList(solver.mergeKLists(lists2));
    assert result2.isEmpty() : "Test 2 failed";

    ListNode[] lists3 = new ListNode[] {buildList(new int[] {})};
    List<Integer> result3 = toList(solver.mergeKLists(lists3));
    assert result3.isEmpty() : "Test 3 failed";

    System.out.println("All tests passed.");
  }
}

