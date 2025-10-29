import java.util.*;

/**
 * Validates juggling siteswap patterns.
 */
public class SiteswapValidator {

  /**
   * Validates a given siteswap pattern.
   *
   * @param pattern String of digits representing throws.
   * @return Validation result.
   */
  public static String validate(String pattern) {
    int n = pattern.length();
    int sum = 0;

    int[] throwsArr = new int[n];
    for (int i = 0; i < n; i++) {
      throwsArr[i] = pattern.charAt(i) - '0';
      sum += throwsArr[i];
    }

    // Check integer number of balls
    if (sum % n != 0) {
      return pattern + ": invalid # of balls";
    }
    int balls = sum / n;

    // Check collisions (at most one ball per landing beat)
    boolean[] occupied = new boolean[n];
    for (int i = 0; i < n; i++) {
      int landing = (i + throwsArr[i]) % n;
      if (occupied[landing]) {
        return pattern + ": invalid pattern";
      }
      occupied[landing] = true;
    }

    return pattern + ": valid with " + balls + " balls";
  }

  /** Example usage with asserts. */
  public static void main(String[] args) {
    assert validate("3").equals("3: valid with 3 balls");
    assert validate("33333").equals("33333: valid with 3 balls");
    assert validate("345").equals("345: valid with 4 balls");
    assert validate("542").equals("542: invalid # of balls");
    assert validate("543").equals("543: invalid pattern");
    assert validate("55550").equals("55550: valid with 4 balls");

    System.out.println("All sample tests passed.");
  }
}

