/*
 *  Copyright 2016 Jiri Lidinsky
 *
 *  This file is part of java tools library.
 *
 *  java tools is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  java tools library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with java tools library.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.tools.text;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Iterate over the str buffer.
 */
public class StrIterator extends AbstractIterator {

  protected final String buffer;

  /**
   * Creates new empty buffer.
   *
   * @param buffer
   */
  public StrIterator(String buffer) {
    this.buffer = buffer;
    this.cursor = 0;
    this.cursorStack = new ArrayDeque<>();
  }

  @Override
  String getBuffer() {
    return buffer;
  }

  //--------------------------------------------------------- Public Interface.

  /** Actual index into the buffer. */
  protected int cursor;

  @Override
  public boolean next() {
    if (hasNext(cursor)) {
      cursor = next(cursor);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public StrCode getCode() {
    return getCode(cursor);
  }

  @Override
  public String getText() {
    return getText(cursor);
  }

  @Override
  public WordIterator getWordIterator() {
    return new WordIterator(this.buffer, this.cursor + 3, getLength(cursor));
  }

  /**
   * Copy actual text into the given array buffer.
   *
   * @param buffer
   *            an array where the text will be placed
   *
   * @param offset
   *            an index where the first char will be placed
   *
   * @param length
   *            max number of characters that are available in the buffer
   *
   * @return the number of characters that was placed into the buffer
   */
  @Override
  public int getText(char[] buffer, int offset, int length) {
    if (isAtTheEnd()) {
      return 0;
    } else {
      int size = Math.min(length, getLength(cursor));
      this.buffer.getChars(cursor + 3, cursor + 3 + size, buffer, offset);
      return size;
    }
  }

  public void back() {
    if (cursor > 0) {
      int index = 0;
      int nextIndex = next(index);
      while (nextIndex != cursor) {
        index = nextIndex;
        nextIndex = next(index);
      }
      cursor = index;
    }
  }

  @Override
  boolean isAtTheEnd() {
    return cursor >= buffer.length() - 3;
  }

  /**
   * Returns false if the actual text content is not empty.
   *
   * @return true if the given text will be zero length
   */
  @Override
  public boolean isEmpty() {
    return getLength(cursor) == 0;
  }


  //--------------------------------------- Internal Mark Manipulation Methods.

  private static final int MAX_LENGTH = 70 * 70;
  private static final String ZERO_LENGTH_MARK = "00";

  private StrCode getCode(int index) {
    validateIndex(index);
    return StrCode.getStrCode(buffer.charAt(index));
  }

  private int getLength(int index) {
    validateIndex(index);
    return (int)(buffer.charAt(index + 1) - '0') * 70
      + (int)(buffer.charAt(index + 2) - '0');
  }

  private int next(int index) {
    validateIndex(index);
    return index + getLength(index) + 3;
  }

  private int validateIndex(int index) {
    if (index >= buffer.length() || index < 0) {
      throw new IndexOutOfBoundsException();
              //.setCode(ExceptionCode.INDEX_OUT_OF_BOUNDS)
              //.set("index", index)
              //.set("buffer size", buffer.length());
    } else {
      return index;
    }
  }

  private String getText(int index) {
    validateIndex(index);
    int length = getLength(index);
    String text = buffer.substring(index + 3, index + 3 + length);
    return text;
  }

  private boolean hasNext(int index) {
    return index < buffer.length();
  }

  //------------------------------------------------------------ Stack support.

  private final Deque<Integer> cursorStack;

  @Override
  void push() {
    cursorStack.push(cursor);
  }

  @Override
  void pop() {
    if (!cursorStack.isEmpty()) {
      cursor = cursorStack.pop();
    }
  }
}
