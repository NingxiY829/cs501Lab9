package permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Permutations implements BackAndForthIterator{
  private int previousIndex;
  private int nextIndex;
  private final int startLength;
  private final String sequence;
  private int permutationNumber;

  public Permutations(String sequence) {
    if (sequence == null || sequence.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.sequence = sequence;
    this.startLength = 1;
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
    this.startLength = startLength;
    this.previousIndex = -1;
    this.nextIndex = 0;
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

  // TODO: fix to qqqq issue in length 1
  public String getPremutationOnIndex(int index) {
    // determine the length
    int premutationLength = 1;
    int sequenceLength = sequence.length();
    int countIndex = 0;
    while (premutationLength <= sequenceLength) {
      int countForLength = factorial(sequenceLength)
              / factorial(sequenceLength - premutationLength);
      if (index < countForLength + countIndex) {
        index -= countIndex;
        break;
      }
      countIndex += countForLength;
      premutationLength ++;
    }
    // find the starting character
    List<Character> chars = new ArrayList<Character>();
    for (char c : sequence.toCharArray()) {
      chars.add(c);
    }
    StringBuilder result = new StringBuilder();
    for (int i = premutationLength; i > 0; i--) {
      int factorial = factorial(sequenceLength-1);
      int charIndex = index / factorial;
      result.append(chars.get(charIndex));
      chars.remove(charIndex);
      index %= factorial;
      sequenceLength --;
    }
    return result.toString();
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
    return previousIndex > 0;
  }

  @Override
  public boolean hasNext() {
    return nextIndex < permutationNumber - 1;
  }

  @Override
  public String next() {
    nextIndex ++;
    previousIndex ++;
    return getPremutationOnIndex(nextIndex - 1);
  }
}
