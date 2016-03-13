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
 *  along with java tools library. If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.tools.text;

/**
 */
class Buffer {

  private final StringBuilder sb;

  /**
   *  Creates new empty buffer.
   */
  Buffer() {
    this.sb = new StringBuilder();
  }

  //--------------------------------------- Internal Mark Manipulation Methods.

  Buffer append(final StrCode code) {
    sb.append(code.getCode());
    sb.append(ZERO_LENGTH_MARK);
    return this;
  }

  private static final int MAX_LENGTH = 70 * 70;
  private static final String ZERO_LENGTH_MARK = "00";

  Buffer append(final StrCode code, String text) {
    int length = text.length();
    if (length > MAX_LENGTH) {
      append(code, text.substring(0, MAX_LENGTH));
      append(StrCode.EXTENDED, text.substring(MAX_LENGTH));
    } else {
      sb.append(code.getCode());
      sb.append((char)('0' + (int)(length / 70)));
      sb.append((char)('0' + (int)(length % 70)));
      sb.append(text);
    }
    return this;
  }

  protected boolean isEmpty(String text) {
    return text == null || text.length() == 0;
  }

  private StrCode getCode(int index) {
    validateIndex(index);
    return StrCode.getStrCode(sb.charAt(index));
  }

  private int getLength(int index) {
    validateIndex(index);
    return (int)(sb.charAt(index + 1) - '0') * 70
      + (int)(sb.charAt(index + 2) - '0');
  }

  private int next(int index) {
    validateIndex(index);
    return index + getLength(index) + 3;
  }

  private int validateIndex(int index) {
    if (index >= sb.length() || index < 0) {
      throw new IndexOutOfBoundsException();
              //.setCode(ExceptionCode.INDEX_OUT_OF_BOUNDS)
              //.set("index", index)
              //.set("buffer size", sb.length());
    } else {
      return index;
    }
  }

  private String getText(int index) {
    validateIndex(index);
    int length = getLength(index);
    String text = sb.substring(index + 3, index + 3 + length);
    if (getCode(next(index)) == StrCode.EXTENDED) {
      text += getText(next(index));
    }
    return text;
  }

  @Override
  public String toString() {
    return sb.toString();
  }

}
