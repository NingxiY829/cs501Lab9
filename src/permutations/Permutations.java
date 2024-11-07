package permutations;

import java.util.NoSuchElementException;

public class Permutations implements BackAndForthIterator{
  private int currentIndex;
  private final int startLength;
  private final String sequence;
  private int permutationNumber;

  public Permutations(String sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.sequence = sequence;
    this.startLength = 1;
    this.currentIndex = 0;
    calculatePermutationNumber();
  }

  public Permutations(String sequence, int startLength) {
    if (sequence == null || sequence.isEmpty()
            || startLength < 1 || startLength > sequence.length()) {
      throw new IllegalArgumentException();
    }
    this.sequence = sequence;
    this.startLength = startLength;
    this.currentIndex = -1;
    calculatePermutationNumber();
  }

  public void calculatePermutationNumber() {
    int total = 0;
    for (int k = startLength; k <= sequence.length(); k++) {
      total += factorial(sequence.length()) / factorial(sequence.length() - k);
    }
    permutationNumber = total;
  }

  public int getPermutationNumber() {
    return permutationNumber;
  }

  private int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }


  @Override
  public String previous() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return currentIndex > 0;
  }

  @Override
  public boolean hasNext() {
    return currentIndex < permutationNumber;
  }

  @Override
  public String next() {
    currentIndex++;
    return null;
  }
}
