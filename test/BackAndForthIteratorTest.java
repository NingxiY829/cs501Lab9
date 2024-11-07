import org.junit.Test;

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
    assertEquals(64, testPermutation.getPermutationNumber());
    Permutations testPermutation2 = new Permutations("qwer", 2);
    assertEquals(60, testPermutation2.getPermutationNumber());
    Permutations testPermutation3 = new Permutations("qwer", 3);
    assertEquals(48, testPermutation3.getPermutationNumber());
  }

  @Test
  public void testGetPremutationOnIndex() {
    Permutations testPermutation = new Permutations("qwer");
    // test length 1
    assertEquals("q", testPermutation.getPremutationOnIndex(0));
    assertEquals("w", testPermutation.getPremutationOnIndex(6));
    assertEquals("wq", testPermutation.getPremutationOnIndex(10));
    assertEquals("ewq", testPermutation.getPremutationOnIndex(30));
    assertEquals("wrqe", testPermutation.getPremutationOnIndex(50));
  }


  @Test
  public void testHasNext() {
    Permutations testPermutation = new Permutations("qw");
    int count1 = testPermutation.getPermutationNumber();
    for (int i = 0; i < count1 - 2; i++) {
      testPermutation.next();
      assertTrue(testPermutation.hasNext());
    }
    testPermutation.next();
    assertFalse(testPermutation.hasNext());
    Permutations testPermutation2 = new Permutations("qwer", 4);
    assertTrue(testPermutation2.hasNext());
    int count = testPermutation2.getPermutationNumber();
    for (int i = 0; i < count - 2; i++) {
      testPermutation2.next();
      assertTrue(testPermutation2.hasNext());
    }
    testPermutation2.next();
    assertFalse(testPermutation2.hasNext());
  }

  @Test
  public void testNext() {
    Permutations testPermutation = new Permutations("qwer");
    String result = testPermutation.next();
    assertEquals("q", result);
    result = testPermutation.next();
    assertEquals("w", result);
  }
  @Test
  public void testHasPrevious() {
    Permutations testPermutation = new Permutations("qwer");
    assertFalse(testPermutation.hasPrevious());
  }


}