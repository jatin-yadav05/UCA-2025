public class EvenFibonacciSum {
    public static long sumEvenFibonacci(int limit) {
        int a = 0, b = 1;
        long sum = 0;
        while (b <= limit) {
            if (b % 2 == 0) {
                sum += b;
            }
            int next = a + b;
            a = b;
            b = next;
        }
        return sum;
    }

    public static void main(String[] args) {
        assert sumEvenFibonacci(10) == 10 : "Test case 1 failed";
        assert sumEvenFibonacci(34) == 44 : "Test case 2 failed";
        assert sumEvenFibonacci(1) == 0 : "Test case 3 failed";
        System.out.println("All test cases passed!");
    }
}

