package permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations implements BackAndForthIterator{
  private int currentIndex;
  private final List<String> permutations;

  public Permutations(String sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException();
    }
    permutations = new ArrayList<>();
    generatePermutations("", sequence);
    this.currentIndex = -1;
  }

  public Permutations(String sequence, int startLength) {
    if (sequence == null || sequence.isEmpty()
            || startLength < 1 || startLength > sequence.length()) {
      throw new IllegalArgumentException();
    }
    permutations = new ArrayList<>();

    generatePermutations("", sequence, startLength);
    this.currentIndex = -1;
  }

  public void generatePermutations(String prefix, String suffix) {
    int n = suffix.length();
    if (n == 0) {
      permutations.add(prefix);
    } else {
      for (int i = 0; i < n; i++) {
        generatePermutations(prefix + suffix.charAt(i),
                suffix.substring(0, i) + suffix.substring(i+1, n));
      }
    }
  }

  public void generatePermutations(String prefix, String suffix, int permutationLength) {
    int n = suffix.length();
    if (n == permutationLength) {
      permutations.add(prefix);
    } else {
      for (int i = 0; i < n; i++) {
        generatePermutations(prefix + suffix.charAt(i),
                suffix.substring(0, i) + suffix.substring(i+1, n));
      }
    }
  }


  @Override
  public Object previous() throws NoSuchElementException {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Object next() {
    return null;
  }
}
