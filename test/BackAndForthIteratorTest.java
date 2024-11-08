import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import permutations.Permutations;

import static org.junit.Assert.*;

public class BackAndForthIteratorTest {
  @Test
  public void testConstructor() {
    Permutations testPermutation = new Permutations("qwer");
    assertNotNull(testPermutation);
    Permutations testPermutation2 = new Permutations("qwer", 2);
    assertNotNull(testPermutation2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Permutations testPermutation = new Permutations("");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Permutations testPermutation = new Permutations("", 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructor4() {
    Permutations testPermutation = new Permutations("qwer", -1);
  }

  @Test
  public void testCalculatePermutationNumber() {
    Permutations testPermutation = new Permutations("qwer");
    assertEquals(15, testPermutation.getPermutationNumber());
    Permutations testPermutation2 = new Permutations("qwer", 2);
    assertEquals(11, testPermutation2.getPermutationNumber());
    Permutations testPermutation3 = new Permutations("qwer", 3);
    assertEquals(5, testPermutation3.getPermutationNumber());
  }

  @Test
  public void testGetPremutationOnIndex() {
    Permutations testPermutation = new Permutations("qwer");
//    int count = testPermutation.getPermutationNumber();
//    StringBuilder sb = new StringBuilder();
//    for (int i = 0; i < count; i++) {
//      sb.append(testPermutation.getPremutationOnIndex(i)).append('\n');
//    }
//    assertEquals("", sb.toString());
    assertEquals("we", testPermutation.getPremutationOnIndex(7));

    // test length 1
    assertEquals("q", testPermutation.getPremutationOnIndex(0));
    assertEquals("qr", testPermutation.getPremutationOnIndex(6));
    assertEquals("qwe", testPermutation.getPremutationOnIndex(10));
    assertEquals("qwer", testPermutation.getPremutationOnIndex(14));
    Permutations testPermutation2 = new Permutations("qwer", 2);
    assertEquals("qw", testPermutation2.getPremutationOnIndex(0));
  }


  @Test
  public void testHasNext() {
    Permutations testPermutation = new Permutations("qw");
    int count1 = testPermutation.getPermutationNumber();
    for (int i = 1; i < count1 - 1; i++) {
      testPermutation.next();
      assertTrue(testPermutation.hasNext());
    }
    testPermutation.next();
    assertFalse(testPermutation.hasNext());
  }

  @Test
  public void testHasNext2() {
    Permutations testPermutation2 = new Permutations("qwer", 4);
    assertTrue(testPermutation2.hasNext());
    assertEquals("qwer", testPermutation2.next());
    Permutations testPermutation3 = new Permutations("qwer", 3);
    assertTrue(testPermutation3.hasNext());
    assertEquals("qwe", testPermutation3.next());
  }

  @Test
  public void testNext() {
    Permutations testPermutation = new Permutations("qwer");
    String result = testPermutation.next();
    assertEquals("q", result);
    result = testPermutation.next();
    assertEquals("w", result);
    Permutations testPermutation2 = new Permutations("qwer", 2);
    result = testPermutation2.next();
    assertEquals("qw", result);
    result = testPermutation2.next();
    assertEquals("qe", result);
  }

  @Test (expected = NoSuchElementException.class)
  public void testNext2() {
    Permutations testPermutation = new Permutations("qw");
    int count1 = testPermutation.getPermutationNumber();
    for (int i = 0; i < count1; i++) {
      testPermutation.next();
    }
    testPermutation.next();
  }

  @Test (expected = NoSuchElementException.class)
  public void testNext3() {
    Permutations testPermutation = new Permutations("qwer", 2);
    int count1 = testPermutation.getPermutationNumber();
    for (int i = 0; i < count1; i++) {
      testPermutation.next();
    }
    testPermutation.next();
  }

  @Test
  public void testHasPrevious() {
    Permutations testPermutation = new Permutations("qwer");
    assertFalse(testPermutation.hasPrevious());
    testPermutation.next();
    assertTrue(testPermutation.hasPrevious());
    Permutations testPermutation2 = new Permutations("qwer", 2);
    assertFalse(testPermutation2.hasPrevious());
    testPermutation2.next();
    assertTrue(testPermutation2.hasPrevious());
  }

  @Test
  public void testPrevious() {
    Permutations testPermutation = new Permutations("qwer");
    int count1 = testPermutation.getPermutationNumber();
    ArrayList<String> result = new ArrayList<>();
    for (int i = 1; i < count1; i++) {
      result.add(testPermutation.next());
    }
    for (int i = 1; i < count1 - 1; i++) {
      assertEquals(result.get(count1 -1 - i), testPermutation.previous());
    }
  }

  @Test
  public void testPrevious3() {
    Permutations testPermutation = new Permutations("qwer", 2);
    int count1 = testPermutation.getPermutationNumber();
    ArrayList<String> result = new ArrayList<>();
    for (int i = 1; i < count1; i++) {
      result.add(testPermutation.next());
    }
    for (int i = 1; i < count1 - 1; i++) {
      assertEquals(result.get(count1 -1 - i), testPermutation.previous());
    }
  }

  @Test (expected = NoSuchElementException.class)
  public void testPrevious2() {
    Permutations testPermutation = new Permutations("qwer");
    int count1 = testPermutation.getPermutationNumber();
    for (int i = 0; i < count1 - 1; i++) {
      testPermutation.next();
    }
    for (int i = 0; i < count1 - 1; i++) {
      testPermutation.previous();
    }
    testPermutation.previous();
  }

  @Test
  public void twoArhumentMoveBackord() {
    Permutations testPermutation = new Permutations("qwer", 2);
    assertTrue(testPermutation.hasPrevious());
    assertEquals("r", testPermutation.previous());
    assertEquals("e", testPermutation.previous());
    assertEquals("w", testPermutation.previous());
    assertEquals("q", testPermutation.previous());
    assertFalse(testPermutation.hasPrevious());
  }




}