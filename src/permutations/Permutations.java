package permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations implements BackAndForthIterator{
  private int currentIndex;
  private final List<String> permutations;

  public Permutations(String sequence) {
    if (sequence == null || sequence.length() == 0) {
      throw new IllegalArgumentException();
    }
    permutations = new ArrayList<>();
    generatePermutations();
    this.currentIndex = -1;
  }

  public Permutations(String sequence, int startLength) {
    if (sequence == null || sequence.length() == 0) {
      throw new IllegalArgumentException();
    }
    permutations = new ArrayList<>();
    generatePermutations();
    this.currentIndex = -1;
  }

  public void generatePermutations() {

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
