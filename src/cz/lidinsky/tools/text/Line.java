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
  }

  /**
   * Erase the line object, so it could be used onece again.
   */
  void reset() {
    cursor = 0;
    Arrays.fill(buffer, ' ');
    delimiter = 0;
  }

  /**
   * Returns number of chars remaining.
   *
   * @return number of characters still available
   */
  int getLength() {
    return buffer.length - cursor;
  }

  /**
   * Appends given word at the end
   *
   * @param word
   *
   * @return false if there was not enough spase to append the given word,
   *            true otherwise
   */
  boolean appendWord(String word) {
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

  boolean appendWord(String word, int offset, int length) {
    if (length > buffer.length - offset) {
      return false;
    } else {
      offset += delimiter;
      word.getChars(offset, length, buffer, cursor);
      cursor += length;
      delimiter = 1;
      return true;
    }
  }

  /**
   * Appends glue.
   */
  void appendGlue() {

  }

  /**
   * Insert given number of whitespaces.
   *
   * @param number
   */
  void skip(int number) {
    if (number > 0) {
      cursor += number;
      delimiter = 0;
    } else if (number < 0) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    return new String(buffer);
  }

  void append(char c) {
    buffer[cursor] = c;
    cursor++;
  }

  void fill(int length, char c) {
    Arrays.fill(buffer, cursor, length, c);
    cursor += length;
  }

  boolean appendWord(String word, int width) {
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
