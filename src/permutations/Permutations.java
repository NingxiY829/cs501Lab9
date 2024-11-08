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

  public String getPremutationOnIndex(int index) {
    // check if this start the index form 1
    int sequenceLength = sequence.length();
    if (startLength != 1) {
      for (int i = startLength - 1; i > 0; i--) {
        index = index + factorial(sequenceLength) / factorial(sequenceLength - i);
      }
    }
    // determine the length
    int premutationLength = 1;

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
    if (premutationLength == 1) {
      result.append(chars.get(index));
    } else if (premutationLength == 2) {
      int combinationNuber = chars.size() - 1;
      int firstIndex = index / combinationNuber;
      result.append(chars.get(firstIndex));
      chars.remove(firstIndex);
      int secondIndex = index % combinationNuber;
      result.append(chars.get(secondIndex));

    } else {
      for (int i = premutationLength; i > 0; i--) {
        int factorial = factorial(chars.size() - 1);
        int charIndex = index / factorial;
        result.append(chars.get(charIndex));
        chars.remove(charIndex);
        index %= factorial;
      }

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
    return previousIndex >= 0;
  }

  @Override
  public boolean hasNext() {
    return nextIndex < permutationNumber - 1;
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
