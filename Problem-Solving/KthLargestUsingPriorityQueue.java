import java.util.*;

class KthLargestUsingPriorityQueue{
	public static int findKthLargest(int[] nums, int k){
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		for(int num : nums){
			minHeap.offer(num);
			if(minHeap.size() > k){
				minHeap.poll();
			}
		}

		return minHeap.peek();
	}

	public static void main(String[] args){
		assert findKthLargest(new int[]{4,2,1,5,6,4}, 2) == 5 : "Test Case 1 failed!";
		assert findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4) == 4 : "Test Case 2 failed!";
		assert findKthLargest(new int[]{1}, 1) == 1 : "Test Case 3 failed!";
		assert findKthLargest(new int[]{2,2,2,2,2,2}, 3) == 2 : "Test Case 4 failed!";
	}
}
