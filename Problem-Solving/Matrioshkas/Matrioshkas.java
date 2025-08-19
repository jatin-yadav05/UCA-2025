import java.util.*;

public class Matrioshkas{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

           String[] input = line.split("\\s+");

            if (isValidMatrioshka(input)) {
                System.out.println(":-) Matrioshka!");
            } else {
                System.out.println(":-( Try again.");
            }
        }
    }

    public static boolean isValidMatrioshka(String[] arr) {
        Stack<Integer> dolls = new Stack<>();
        Stack<Integer> innerSums = new Stack<>();

        for (String str : arr) {
            int num = Integer.parseInt(str);

            if (num < 0) {
                dolls.push(-num);
                innerSums.push(0);
            } else {
                if (dolls.isEmpty() || dolls.peek() != num) {
                    return false;
                }

                int lastDoll = dolls.pop();
                int innerSum = innerSums.pop();

                if (innerSum >= lastDoll) {
                    return false;
		}

                if (!innerSums.isEmpty()) {
                    innerSums.push(innerSums.pop() + lastDoll);
                }
            }
        }
        return dolls.isEmpty();
    }
}

