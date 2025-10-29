public class SearchInRotatedSortedArrayWithDuplicates {
    /**
     * Given a sorted array that is rotated at some pivot and may contain
     * duplicates, find the index of a target value. If the target is not found,
     * return -1.
     *
     * Example: [2, 5, 6, 0, 0, 1, 2], target = 0 -> returns 3 or 4.
     *
     * @param nums   - Rotated sorted integer array (may contain duplicates).
     * @param target - The integer to search for.
     * @returns int - Index of target or -1 if not found.
     */
    public int search(int[] nums, int target) {
        int st = 0, end = nums.length - 1;

        while (st <= end) {
            int mid = st + (end - st) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[st] == nums[mid] && nums[mid] == nums[end]) {
                st++;
                end--;
            }

            else if (nums[st] <= nums[mid]) {
                if (target >= nums[st] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    st = mid + 1;
                }
            }
            else {
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
     * Main method for testing the SearchInRotatedSortedArrayWithDuplicates class.
     */
    public static void main(String[] args) {
        SearchInRotatedSortedArrayWithDuplicates s = new SearchInRotatedSortedArrayWithDuplicates();
        int[] arr1 = { 2, 5, 6, 0, 0, 1, 2 };
        assert (s.search(arr1, 0) == 3 || s.search(arr1, 0) == 4) : "Test case 1 failed";
        int[] arr2 = { 2, 2, 2, 3, 4, 2 };
        assert s.search(arr2, 3) == 3 : "Test case 2 failed";
        assert s.search(arr2, 5) == -1 : "Test case 3 failed";
    }
}

