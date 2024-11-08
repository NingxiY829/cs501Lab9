package permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations implements BackAndForthIterator{
  private int previousIndex;
  private int nextIndex;
  private final String sequence;
  private int permutationNumber;

  public Permutations(String sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.sequence = sequence;
    this.previousIndex = -1;
    this.nextIndex = 0;
    calculatePermutationNumber();
  }

  public Permutations(String sequence, int startLength) {
    if (sequence == null || sequence.isEmpty()
            || startLength < 1 || startLength > sequence.length()) {
      throw new IllegalArgumentException();
    }
    this.sequence = sequence;
    int n = sequence.length();
    int targetIndex = 0;
    if (startLength != 1) {
      for (int k = 1; k < startLength; k++) {
        targetIndex += combination(n, k);
      }
    }
    this.previousIndex = targetIndex - 1;
    this.nextIndex = targetIndex;
    calculatePermutationNumber();
  }

  private void calculatePermutationNumber() {
    int totalPermutations = 0;
    int n = sequence.length();
    for (int k = 1; k <= n; k++) {
      totalPermutations += combination(n, k);
    }
    permutationNumber = totalPermutations;
  }

  public int getPermutationNumber() {
    return permutationNumber;
  }

  private int combination(int n, int k) {
    if (k > n) return 0;
    int numerator = 1;
    int denominator = 1;
    for (int i = 0; i < k; i++) {
      numerator *= (n - i);
      denominator *= (i + 1);
    }
    return numerator / denominator;
  }

  public String getPremutationOnIndex(int targetIndex) {
    int n = sequence.length();
    int cumulativeCount = 0;
    int length = 0;

    for (int k = 1; k <= n; k++) {
      int count = combination(n, k);
      if (cumulativeCount + count > targetIndex) {
        length = k;
        targetIndex -= cumulativeCount;
        break;
      }
      cumulativeCount += count;
    }

    StringBuilder combination = new StringBuilder();
    int remainingLength = length;

    for (int i = 0; i < n && remainingLength > 0; i++) {
      int combinationsWithCurrentChar = combination(n - i - 1, remainingLength - 1);

      if (targetIndex < combinationsWithCurrentChar) {
        combination.append(sequence.charAt(i));
        remainingLength--;
      } else {
        targetIndex -= combinationsWithCurrentChar;
      }
    }

    return combination.toString();

  }


  @Override
  public String previous() throws NoSuchElementException {
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    previousIndex --;
    nextIndex --;
    return getPremutationOnIndex(previousIndex + 1);
  }

  @Override
  public boolean hasPrevious() {
    return previousIndex >= 0;
  }

  @Override
  public boolean hasNext() {
    return nextIndex < permutationNumber;
  }

  @Override
  public String next() {
    if (! hasNext()) {
      throw new NoSuchElementException();
    }
    nextIndex ++;
    previousIndex ++;
    return getPremutationOnIndex(nextIndex - 1);
  }
}
