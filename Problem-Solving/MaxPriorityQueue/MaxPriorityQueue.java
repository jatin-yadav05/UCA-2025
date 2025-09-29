package priorityqueue.generic;

import java.util.Comparator;

public class MaxPriorityQueue<T> {
  private T[] pq;
  private int size;
  private Comparator<T> comparator;

  public MaxPriorityQueue(int capacity) {
    this.pq = (T []) new Object[capacity + 1];
    this.size = 0;
    this.comparator = null;
    }
  public MaxPriorityQueue(int capacity, Comparator<T> comparator) {
    this.pq = (T []) new Object[capacity + 1];
    this.size = 0;
    this.comparator = comparator;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }


  public int size() {
    return this.size;
  }

  private void exch(int i, int j) {
    T temp = this.pq[i];
    this.pq[i] = this.pq[j];
    this.pq[j] = temp; 
  }
  
  private boolean less(int i, int j) {
    if (comparator != null) {
      return comparator.compare(this.pq[i], this.pq[j]) < 0;
    }
    return ((Comparable<T>) this.pq[i]).compareTo(((T) this.pq[j])) < 0;
  }

 
  public void insert(T x) {
    this.pq[++size] = x;
    swim(size);
  }

  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  public T delMax() {
    T elem = this.pq[1];
    exch(1, size--);
    sink(1);

    return elem;
  }
 

  private void sink(int k) {
    while (2 * k <= size) {
      int j = 2 * k;
      
      if (j < size && less(j, j + 1)) {
        j++;
      }
      if (!less(k, j)) {
        break;
      }
      exch(k, j); 
      k = j;
    }
  }
  

  public static void main(String[] args) {
    MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<Integer>(32);
    pq.insert(2);
    pq.insert(7);
    pq.insert(4);
    pq.insert(5);
    pq.insert(3);
    pq.insert(3);
    pq.insert(2);
    pq.insert(9);

    assert pq.delMax() == 9;
    
    pq.insert(8);

    assert pq.isEmpty() == false;
    assert pq.size() == 8;
    assert pq.delMax() == 8;
    assert pq.delMax() == 7;
    assert pq.delMax() == 5;
    assert pq.delMax() == 4;
    assert pq.delMax() == 3;
    assert pq.delMax() == 3;
    assert pq.delMax() == 2;
    assert pq.delMax() == 2;
  }
}

