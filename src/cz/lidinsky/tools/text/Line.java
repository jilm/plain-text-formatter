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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents one line of text.
 */
class Line {

  /** The content of the line. */
  private final char[] buffer;

  /** Index of the first non empty element in the buffer. */
  private int cursor;

  /** How many spaces it is necessary to insert in front of the next word. */
  private int delimiter;

  /** Contains indices of the glues. */
  private final List<Integer> glues;

  /**
   * Initialize the object.
   *
   * @param length
   *            line length, how many characters
   */
  Line(final int length) {
    buffer = new char[length];
    cursor = 0;
    Arrays.fill(buffer, ' ');
    delimiter = 0;
    glues = new ArrayList();
  }

  /**
   * Erase the line object, so it could be used once again.
   */
  public void reset() {
    cursor = 0;
    Arrays.fill(buffer, ' ');
    delimiter = 0;
    glues.clear();
  }

  /**
   * Returns number of chars remaining.
   *
   * @return number of characters still available
   */
  public int getLength() {
    return buffer.length - cursor;
  }

  /**
   * Appends given word at the end of the line. Appends a white character
   * in front of the word if necessary.
   *
   * @param word
   *            s word to append
   *
   * @return false if there was not enough space to append the given word,
   *            true otherwise
   */
  public boolean appendWord(String word) {
    int wordLength = word.length();
    if (wordLength > buffer.length - cursor) {
      return false;
    } else {
      cursor += delimiter;
      word.getChars(0, wordLength, buffer, cursor);
      cursor += wordLength;
      delimiter = 1;
      return true;
    }
  }

  /**
   * Appends given word at the end of the line. Appends a white character
   * in front of the word if necessary.
   *
   * @param word
   *            a word to append
   *
   * @param offset
   *            index of the first character of the word
   *
   * @param length
   *            the length of the word
   *
   * @return false if there was not enough space to append given word,
   *            true otherwise
   */
  public boolean appendWord(String word, int offset, int length) {
    if (length + delimiter >= buffer.length - cursor) {
      return false;
    } else {
      cursor += delimiter;
      word.getChars(offset, length + offset, buffer, cursor);
      cursor += length;
      delimiter = 1;
      return true;
    }
  }

  /**
   * Appends a glue.
   */
  public void appendGlue() {
    glues.add(cursor);
  }

  /**
   * Insert given number of whitespaces.
   *
   * @param number
   */
  public void skip(int number) {
    if (number > 0) {
      cursor += number;
      delimiter = 0;
    } else if (number < 0) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    // expand glues
    int space = getLength();
    int offset = 0;
    int gluesNumber = glues.size();
    if (!glues.isEmpty() && space > 0) {
      for (int glueIndex : glues) {
        int spacePerGlue = space / gluesNumber;
        System.arraycopy(buffer, glueIndex + offset,
              buffer, glueIndex + offset + spacePerGlue,
              buffer.length - glueIndex - offset - spacePerGlue);
        Arrays.fill(buffer, glueIndex + offset,
              glueIndex + offset + spacePerGlue, ' ');
        offset += spacePerGlue;
        space -= spacePerGlue;
        gluesNumber --;
        cursor += spacePerGlue;
      }
    }
    // return string representation
    return new String(buffer);
  }

  public void append(char c) {
    buffer[cursor] = c;
    cursor++;
  }

  public void fill(int length, char c) {
    Arrays.fill(buffer, cursor, cursor + length, c);
    cursor += length;
  }

  public boolean appendWord(String word, int width) {
    int wordLength = word.length();
    if (wordLength > buffer.length - cursor) {
      return false;
    } else {
      cursor += delimiter;
      word.getChars(0, wordLength, buffer, cursor);
      cursor += width;
      delimiter = 1;
      return true;
    }
  }

}
