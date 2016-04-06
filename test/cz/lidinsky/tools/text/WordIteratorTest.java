/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import junit.framework.TestCase;

/**
 *
 * @author jilm
 */
public class WordIteratorTest extends TestCase {

  public WordIteratorTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * Test of isAtTheEnd method, of class WordIterator.
   */
  public void testIsAtTheEnd() {
    System.out.println("isAtTheEnd");
    WordIterator instance = new WordIterator("", 10, 15);
    assertTrue(instance.isAtTheEnd());
  }

  /**
   * Test of isAtTheEnd method, of class WordIterator.
   */
  public void testIsAtTheEnd1() {
    System.out.println("isAtTheEnd");
    WordIterator instance = new WordIterator("blablabla", 5, 15);
    assertFalse(instance.isAtTheEnd());
  }


  /**
   * Test of next method, of class WordIterator.
   */
  public void testNext() {
    System.out.println("next");
    WordIterator instance = new WordIterator("", 15, 10);
    assertFalse(instance.next());
  }

  /**
   * Test of next method, of class WordIterator.
   */
  public void testNext1() {
    System.out.println("next");
    WordIterator instance = new WordIterator("toto", 0, 11);
    assertFalse(instance.next());
  }


  /**
   * Test of next method, of class WordIterator.
   */
  public void testNext2() {
    System.out.println("next");
    WordIterator instance = new WordIterator("toto   proto", 0, 110);
    assertTrue(instance.next());
    assertFalse(instance.next());
  }

  /**
   * Test of getChars method, of class WordIterator.
   */
  public void testGetChars() {
    System.out.println("getChars");
    char[] buffer = new char[10];
    int offset = 0;
    int length = 10;
    WordIterator instance = new WordIterator("abc", 0, 10);
    int expResult = 3;
    int result = instance.getChars(buffer, offset, length);
    assertEquals(expResult, result);
    assertEquals('a', buffer[0]);
    assertEquals('b', buffer[1]);
    assertEquals('c', buffer[2]);
  }


  /**
   * Test of getChars method, of class WordIterator.
   */
  public void testGetChars2() {
    System.out.println("getChars");
    char[] buffer = new char[10];
    int offset = 0;
    int length = 10;
    WordIterator instance = new WordIterator("abc def", 0, 10);
    int expResult = 3;
    int result = instance.getChars(buffer, offset, length);
    assertEquals(expResult, result);
    assertEquals('a', buffer[0]);
    assertEquals('b', buffer[1]);
    assertEquals('c', buffer[2]);
    assertTrue(instance.next());
    assertEquals(3, instance.getChars(buffer, offset, length));
    assertEquals('d', buffer[0]);
    assertEquals('e', buffer[1]);
    assertEquals('f', buffer[2]);
  }
  /**
   * Test of getLength method, of class WordIterator.
   */
  public void testGetLength() {
    System.out.println("getLength");
    WordIterator instance = new WordIterator("", 15, 8);
    int expResult = 0;
    int result = instance.getLength();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLength method, of class WordIterator.
   */
  public void testGetLength1() {
    System.out.println("getLength");
    WordIterator instance = new WordIterator("    ", 0, 18);
    int expResult = 0;
    int result = instance.getLength();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLength method, of class WordIterator.
   */
  public void testGetLength2() {
    System.out.println("getLength");
    WordIterator instance = new WordIterator("    ab", 0, 18);
    int expResult = 2;
    int result = instance.getLength();
    assertEquals(expResult, result);
  }

}
