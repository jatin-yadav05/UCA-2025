public class SearchInRotatedSortedArray {
  /**
   * Given a sorted array that is rotated at some pivot (with no duplicate
   * values), find the index of a target value. If the target is not found, return
   * -1.
   *
   * Example: [4, 5, 6, 7, 0, 1, 2], target = 0 -> returns 4.
   *
   * @param nums   - Rotated sorted integer array (no duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   */
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int st = 0;
    int end = nums.length - 1;

    while (st <= end) {
      int mid = st + (end - st) / 2;

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[st] <= nums[mid]) {
        if (target >= nums[st] && target < nums[mid]) {
          end = mid - 1;
        } else {
          st = mid + 1;
        }
      } else {
        if (target > nums[mid] && target <= nums[end]) {
          st = mid + 1;
        } else {
          end = mid - 1;
        }
      }
    }

    return -1;
  }

  /**
   * Main method for testing the SearchInRotatedSortedArray class.
   */
  public static void main(String[] args) {
    SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
    int[] arr1 = { 4, 5, 6, 7, 0, 1, 2 };
    assert s.search(arr1, 0) == 4 : "Test case 1 failed";
    assert s.search(arr1, 3) == -1 : "Test case 2 failed";
    int[] arr2 = { 3, 1 };
    assert s.search(arr2, 3) == 0 : "Test case 3 failed";
  }
}

