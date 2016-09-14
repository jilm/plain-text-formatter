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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jilm
 */
public class ParagraphTextFormatterTest {

  ParagraphTextFormatter emptyParagraph;
  ParagraphTextFormatter par1;
  ParagraphTextFormatter par2;
  ParagraphTextFormatter par3;

  public ParagraphTextFormatterTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    emptyParagraph = new ParagraphTextFormatter(true, 0);
    par1 = new ParagraphTextFormatter(true, 0);
    par1.add(new TextFormatter("This is the first sentence of the par1. ", 0, 0));
    par1.add(new TextFormatter("The Evans Gambit is an aggressive line of the Giuoco Piano, which normally continues with the positional moves 4.c3 or 4.d3. White offers a pawn to divert the Black Bishop on c5. If Black accepts, White can follow up with c3 and d4, ripping open the center, while also opening diagonals to play Ba3 or Qb3 at some point, preventing Black from castling kingside and threatening the f7-pawn respectively. If Black declines, the b4 pawn stakes out space on the queenside, and White can follow up with a4 later in the game, potentially gaining a tempo by threatening to trap Black's dark-square Bishop. According to Reuben Fine, the Evans Gambit poses a challenge for Black since the usual defenses (play ...d6 and/or give back the gambit pawn) are more difficult to pull off than with other gambits. (Interestingly, Fine was beaten by this gambit in a friendly game against Bobby Fischer, in just 17 moves.[1])", 0, 0));
    par2 = new ParagraphTextFormatter(true, 0);
    par2.add(new TextFormatter("Short paragraph.", 0, 0));
    par3 = new ParagraphTextFormatter(true, 0);
    par3.add(new TextFormatter("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 0, 0));
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of formatLine method, of class ParagraphTextFormatter.
   */
  @Test
  public void testFormatLine() {
    System.out.println("formatLine");
    char[] buffer = new char[80];
    Arrays.fill(buffer, ' ');
    int offset = 2;
    int length = 70;
    Arrays.fill(buffer, offset, length + offset, '-');
    System.out.println(new String(buffer));
    int[] expResults = new int[] {62, 70, 70, 67, 68, 62, 68};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = par1.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      assertEquals(expResults[i], result);
    }
    offset = 4; length = 10;
    Arrays.fill(buffer, ' ');
    Arrays.fill(buffer, offset, length + offset, '-');
    System.out.println(new String(buffer));
    expResults = new int[] {10, 8, 3, 10, 9, 10, 10};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = par1.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      assertEquals(expResults[i], result);
    }
    offset = 0; length = 70;
    Arrays.fill(buffer, ' ');
    Arrays.fill(buffer, offset, length + offset, '-');
    System.out.println(new String(buffer));
    expResults = new int[] {16, 0};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = par2.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      assertEquals(expResults[i], result);
    }
    offset = 0; length = 20;
    Arrays.fill(buffer, ' ');
    Arrays.fill(buffer, offset, length + offset, '-');
    System.out.println(new String(buffer));
    expResults = new int[] {20, 20, 20, 1, 0};
    for (int i = 0; i<expResults.length; i++) {
      Arrays.fill(buffer, ' ');
      int result = par3.formatLine(buffer, offset, length);
      System.out.println(new String(buffer));
      assertEquals(expResults[i], result);
    }

  }

  /**
   * Test of isEmpty method, of class ParagraphTextFormatter.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    ParagraphTextFormatter instance = null;
    boolean expResult = false;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLength method, of class ParagraphTextFormatter.
   */
  @Test
  public void testGetLength() {
    System.out.println("getLength");
    int length = 0;
    ParagraphTextFormatter instance = new ParagraphTextFormatter(false, 0);
    int expResult = 0;
    int result = instance.getLength(length);
    instance.add(new TextFormatter("Ahoj, jak se mas?", 0, 0));
    assertEquals(expResult, result);
    result = instance.getLength(80);
    assertEquals(17, result);
    result = instance.getLength(11);
    assertEquals(9, result);
    instance.add(new TextFormatter("The second sentence.", 0, 0));
    result = instance.getLength(80);
    assertEquals(38, result);
  }

  /**
   * Test of getWords method, of class ParagraphTextFormatter.
   */
  @Test
  public void testGetWords() {
    System.out.println("getWords");
    int length = 0;
    ParagraphTextFormatter instance = null;
    int expResult = 0;
    int result = instance.getWords(length);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of add method, of class ParagraphTextFormatter.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    TextFormatter text = null;
    ParagraphTextFormatter instance = null;
    instance.add(text);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

}
