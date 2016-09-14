/*
 * Copyright (C) 2016 jilm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.lidinsky.tools.text;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class WordIterator2Test {

  public WordIterator2Test() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  WordIterator2 emptyIter;
  WordIterator2 iter1;
  WordIterator2 iter2;

  @Before
  public void setUp() {
    emptyIter = new WordIterator2("", 0, 0);
    iter1 = new WordIterator2("  Test iterator  ", 0, 17);
    iter2 = new WordIterator2("Test Iterator   aaa bbbbb", 2, 10);
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of size method, of class WordIterator2.
   */
  @Test
  public void testSize() {
    System.out.println("size");
    int expResult = 0;
    int result = emptyIter.size();
    assertEquals(0, result);
    assertEquals(13, iter1.size());
    assertEquals(10, iter2.size());
  }

  /**
   * Test of isEmpty method, of class WordIterator2.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    assertEquals(true, emptyIter.isEmpty());
    assertEquals(false, iter1.isEmpty());
    assertEquals(false, iter2.isEmpty());
  }

  /**
   * Test of reset method, of class WordIterator2.
   */
  @Test
  @Ignore
  public void testReset() {
    System.out.println("reset");
    WordIterator2 instance = null;
    instance.reset();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getChars method, of class WordIterator2.
   */
  @Test
  public void testGetChars() {
    System.out.println("getChars");
    char[] buffer = new char[20];
    char[] expBuffer = new char[20];
    Arrays.fill(buffer, ' ');
    Arrays.fill(expBuffer, ' ');
    int offset = 3;
    int length = 10;
    int result = emptyIter.getChars(buffer, offset, length);
    assertEquals(0, result);
    Assert.assertArrayEquals(expBuffer, buffer);
    result = iter1.getChars(buffer, offset, length);
    "Test itera".getChars(0, 10, expBuffer, 3);
    assertEquals(10, result);
    Assert.assertArrayEquals(expBuffer, buffer);
  }

  /**
   * Test of getWord method, of class WordIterator2.
   */
  @Test
  public void testGetWord() {
    System.out.println("getWord");
    char[] buffer = new char[20];
    char[] expBuffer = new char[20];
    Arrays.fill(buffer, ' ');
    Arrays.fill(expBuffer, ' ');
    int offset = 3;
    int length = 10;
    int result = emptyIter.getWord(buffer, offset, length);
    assertEquals(0, result);
    Assert.assertArrayEquals(expBuffer, buffer);
    result = iter1.getWord(buffer, offset, length);
    "Test".getChars(0, 4, expBuffer, 3);
    assertEquals(4, result);
    Assert.assertArrayEquals(expBuffer, buffer);
    result = iter1.getWord(buffer, offset, length);
    "iterator".getChars(0, 8, expBuffer, 3);
    assertEquals(8, result);
    Assert.assertArrayEquals(expBuffer, buffer);
    result = iter1.getWord(buffer, offset, length);
    assertEquals(0, result);
    Assert.assertArrayEquals(expBuffer, buffer);
  }

  /**
   * Test of calculateWordLength method, of class WordIterator2.
   */
  @Test
  @Ignore
  public void testCalculateWordLength() {
    System.out.println("calculateWordLength");
    int position = 0;
    WordIterator2 instance = null;
    int expResult = 0;
    int result = instance.calculateWordLength(position);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class WordIterator2.
   */
  @Test
  @Ignore
  public void testGetLength_0args() {
    System.out.println("getLength");
    WordIterator2 instance = null;
    int expResult = 0;
    int result = instance.getLength();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class WordIterator2.
   */
  @Test
  public void testGetLength_int() {
    System.out.println("getLength");
    int result = emptyIter.getLength(20);
    assertEquals(0, result);
    result = iter1.getLength(20);
    assertEquals(13, result);
    result = iter1.getLength(10);
    assertEquals(4, result);
    result = iter1.getLength(4);
    assertEquals(4, result);
    result = iter1.getLength(3);
    assertEquals(0, result);
  }

}
