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

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class LineTest {

  public LineTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of reset method, of class Line.
   */
  @Test
  public void testReset() {
    System.out.println("reset");
    Line instance = null;
    instance.reset();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class Line.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    Line instance = null;
    int expResult = 0;
    int result = instance.getLength();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendWord method, of class Line.
   */
  @Test
  public void testAppendWord_String() {
    System.out.println("appendWord");
    String word = "";
    Line instance = null;
    boolean expResult = false;
    boolean result = instance.appendWord(word);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendWord method, of class Line.
   */
  @Test
  public void testAppendWord_3args() {
    System.out.println("appendWord");
    String word = "";
    int offset = 0;
    int length = 0;
    Line instance = null;
    boolean expResult = false;
    boolean result = instance.appendWord(word, offset, length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendGlue method, of class Line.
   */
  @Test
  public void testAppendGlue() {
    System.out.println("appendGlue");
    Line line = new Line(10);
    line.appendGlue();
    line.appendWord("abcd");
    String result = line.toString();
    assertEquals("      abcd", result);

    line.reset();
    line.appendWord("ab");
    line.appendGlue();
    line.appendWord("cd");
    result = line.toString();
    assertEquals("ab      cd", result);

    line.reset();
    line.appendWord("abcd");
    line.appendGlue();
    result = line.toString();
    assertEquals("abcd      ", result);

    line.reset();
    line.appendGlue();
    line.appendWord("abcd");
    line.appendGlue();
    result = line.toString();
    assertEquals("   abcd   ", result);

    line.reset();
    line.appendGlue();
    line.appendWord("ab");
    line.appendGlue();
    line.appendWord("cd");
    line.appendGlue();
    result = line.toString();
    assertEquals(" ab   cd  ", result);
  }

  /**
   * Test of skip method, of class Line.
   */
  @Test
  public void testSkip() {
    System.out.println("skip");
    int number = 0;
    Line instance = null;
    instance.skip(number);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Line.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Line instance = null;
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of append method, of class Line.
   */
  @Test
  public void testAppend() {
    System.out.println("append");
    char c = ' ';
    Line instance = null;
    instance.append(c);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of fill method, of class Line.
   */
  @Test
  public void testFill() {
    System.out.println("fill");
    int length = 0;
    char c = ' ';
    Line instance = null;
    instance.fill(length, c);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of appendWord method, of class Line.
   */
  @Test
  public void testAppendWord_String_int() {
    System.out.println("appendWord");
    String word = "";
    int width = 0;
    Line instance = null;
    boolean expResult = false;
    boolean result = instance.appendWord(word, width);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
