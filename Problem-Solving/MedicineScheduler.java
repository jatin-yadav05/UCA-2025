import java.util.*;

/**
 * MedicineScheduler determines the order of medicines Tobby must take.
 */
public class MedicineScheduler {

  /** Represents a medicine event. */
  static class Medicine {
    int time;
    int priority;
    String name;

    Medicine(int time, int priority, String name) {
      this.time = time;
      this.priority = priority;
      this.name = name;
    }
  }

  /**
   * Schedules the first k medications.
   *
   * @param medicines Array of {name, frequency}.
   * @param k Number of doses to output.
   * @return List of (time, name) pairs.
   */
  public static List<String> schedule(List<String[]> medicines, int k) {
    PriorityQueue<Medicine> pq =
        new PriorityQueue<>(
            (a, b) ->
                a.time != b.time ? Integer.compare(a.time, b.time)
                    : Integer.compare(a.priority, b.priority));

    // Insert first occurrence of each medicine
    for (int i = 0; i < medicines.size(); i++) {
      String name = medicines.get(i)[0];
      int freq = Integer.parseInt(medicines.get(i)[1]);
      pq.offer(new Medicine(freq, i, name));
    }

    List<String> result = new ArrayList<>();

    // Generate k events
    for (int i = 0; i < k; i++) {
      Medicine m = pq.poll();
      result.add(m.time + " " + m.name);
      int freq = Integer.parseInt(medicines.get(m.priority)[1]);
      pq.offer(new Medicine(m.time + freq, m.priority, m.name));
    }
    return result;
  }

  /** Example test. */
  public static void main(String[] args) {
    List<String[]> medicines = new ArrayList<>();
    medicines.add(new String[] {"Acetaminophen", "20"});
    medicines.add(new String[] {"Loratadine", "30"});
    int k = 5;

    List<String> output = schedule(medicines, k);

    List<String> expected =
        Arrays.asList(
            "20 Acetaminophen",
            "30 Loratadine",
            "40 Acetaminophen",
            "60 Acetaminophen",
            "60 Loratadine");

    assert output.equals(expected) : "Test failed";

    for (String line : output) {
      System.out.println(line);
    }
    System.out.println("All tests passed.");
  }
}

