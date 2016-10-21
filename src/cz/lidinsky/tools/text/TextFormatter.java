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

/**
 *
 */
public class TextFormatter extends AbstractTextFormatter {

  /** The whole string that will be split. */
  private final String buffer;

  /**
   * First character in the buffer. Always positive number that is less than
   * the buffer.length
   */
  private int offset;

  /**
   * Length of the given sentence. Always positive number, where offset
   * lenght is less or equal than buffer.length
   */
  private int length;

  /** Pointer to the first character of the actual word. The pointer is
   relative, it means, you must add an offset to obtain the buffer index. */
  private int cursor;

  /** Length of the actual word. */
  private int wordLength;

  /**
   * Finds the first word in the given buffer, so the method getWord returns
   * the first word.
   *
   * @param parent
   * @param buffer
   *            the string to be split into the words
   *
   * @param offset
   *            the index of the first character in the buffer
   *
   * @param length
   *            the lenght of the string
   */
  public TextFormatter(AbstractTextFormatter parent, String buffer, int offset, int length) {
    super(parent);
    this.buffer = buffer == null ? "" : buffer;
    this.offset = StrUtils.validateArrayOffset(buffer.length(), offset);
    this.length = StrUtils.validateArrayLength(buffer.length(), offset, length);
    trim();
    this.cursor = 0;
    this.wordLength = calculateWordLength(); // calculate the word length
  }

  TextFormatter(AbstractTextFormatter parent, String buffer) {
    this(parent, buffer, 0, buffer.length());
  }

  /**
   * Change offset and length properties such that they points to non whitespace
   * charecters.
   */
  private void trim() {
    int size = skipWhitespace(0);
    offset += size;
    length -= size;
    while (length > 0 && Character.isWhitespace(buffer.charAt(offset + length - 1))) {
      length --;
    }
  }

  /**
   * Finds first non whitespace character.
   */
  private void skipWhitespace() {
    cursor += skipWhitespace(cursor);
  }

  private int skipWhitespace(int position) {
    int size = 0;
    while (position + size < length) {
      if (Character.isWhitespace(buffer.charAt(offset + position + size))) {
        size ++;
      } else {
        return size;
      }
    }
    return size;
  }

  private int skipWord(int position) {
    int size = 0;
    while (position + size < length) {
      if (!Character.isWhitespace(buffer.charAt(offset + position + size))) {
        size ++;
      } else {
        return size;
      }
    }
    return size;
  }

  /**
   * Returns characters remaining.
   *
   * @return
   */
  public int size() {
    return length;
  }

  /**
   * Returns true if there is no character left.
   *
   * @return
   */
  public boolean isEmpty() {
    return cursor >= length;
  }

  /**
   * Reset pointer
   */
  public void reset() {
    this.cursor = 0;
    this.wordLength = calculateWordLength(); // calculate the word length
  }

  public int getChars(char[] buffer, int offset, int length) {
    int size = Math.min(length, this.length - cursor);
    this.buffer.getChars(this.offset + cursor, this.offset + cursor + size, buffer, offset);
    cursor += size;
    skipWhitespace();
    this.wordLength = calculateWordLength(); // calculate the word length
    return size;
  }

  public int getWord(char[] buffer, int offset, int length) {
    int size = getChars(buffer, offset, wordLength);
    return size;
  }

  private int calculateWordLength() {
    return calculateWordLength(cursor);
  }

  int calculateWordLength(int position) {
    int count = 0;
    while (position + count < length) {
      if (!Character.isWhitespace(buffer.charAt(offset + position + count))) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  /**
   * Returns length of the word that is available. Returns zero, if the
   * end of the string has been reached.
   *
   * @return lenght of the actual word or zero, if there are no more words
   */
  public int getLength() {
    return wordLength;
  }

  public int getLength(int length) {
    int available = 0;
    int delimiter = 0;
    int position = cursor + skipWhitespace(cursor);
    while (true) {
      int size = skipWord(position);
      if (size == 0) {
        return available;
      } else if (available + delimiter + size > length) {
        return available;
      } else {
        available += delimiter + size;
        position += size;
        position += skipWhitespace(position);
        delimiter = 1;
      }
    }
  }


  /**
   * TODO: what if the first word of the line is longer than the line itself !
   *
   * @param line
   *
   * @return false if there is some space left, but there are no words to add
   */
  protected boolean formatLine(Line line) {
    while (!isEmpty()) {
      if (line.appendWord(buffer, cursor+offset, wordLength)) {
        cursor += wordLength;
        skipWhitespace();
        wordLength = calculateWordLength();
      } else {
        return true;
      }
    }
    return false;
  }

}
