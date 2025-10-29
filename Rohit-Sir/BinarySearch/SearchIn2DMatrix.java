public class SearchIn2DMatrix {
  /**
   * Given a 2D matrix where each row is sorted in ascending order and the first element of each row is greater than the last element of the previous row,
   * write a method to search for a target value. Return true if the target exists in the matrix, otherwise false.
   *
   * Example:
   * [
   *   [1, 3, 5],
   *   [7, 9, 11],
   *   [13, 15, 17]
   * ]
   * target = 9 -> returns true.
   *
   * @param matrix - 2D matrix with sorted rows and row-wise ordering.
   * @param target - The integer to search for.
   * @returns boolean - True if found, false otherwise.
   */
  public boolean searchMatrix(int[][] matrix, int target) {
    int row = matrix.length;
    int col = matrix[0].length;
    int st = 0;
    int end = row * col - 1;

    while (st <= end){
        int mid = st + (end-st)/2;
        int colIndex = mid % col;
        int rowIndex = mid / col;
        if(matrix[rowIndex][colIndex] == target) {
            return true;
        }else if(matrix[rowIndex][colIndex] < target) {
            st = mid + 1;
        }else{
            end = mid - 1;
        }
    }
    return false;
  }

  /**
   * Main method for testing the SearchIn2DMatrix class.
   */
  public static void main(String[] args) {
    SearchIn2DMatrix s = new SearchIn2DMatrix();
    int[][] matrix = {
      {1, 3, 5},
      {7, 9, 11},
      {13, 15, 17}
    };
    assert s.searchMatrix(matrix, 9) == true : "Test case 1 failed";
    assert s.searchMatrix(matrix, 2) == false : "Test case 2 failed";
    assert s.searchMatrix(matrix, 17) == true : "Test case 3 failed";
    assert s.searchMatrix(matrix, 10) == false : "Test case 4 failed";
  }
}

