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

import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author jilm
 */
public class StrBufferReader {

  private char[] buffer;

  private int offset;

  private int level;

  private int chars;

  private final Reader reader;

  public StrBufferReader(Reader reader) {
    this.reader = reader;
    this.buffer = new char[255];
    this.offset = 0;
    this.level = 0;
  }

  public StrBuffer2 readBuffer() throws IOException {
    do {
      // read str code
      read(1);
      StrCode code = StrCode.getStrCode(buffer[offset]);
      commit(1);
      if (code == StrCode.END) {
        level--;
      } else if (code.isBlock()) {
        level++;
      } else {
        // read the length of the subsequent text
        read(2);
        int textLength = (int)(buffer[offset] - '0') * 70
          + (int)(buffer[offset + 1] - '0');
        commit(2);
        read(textLength);
        commit(textLength);
      }
    } while (level > 0);
    return createMessage();
  }

  /**
   * Reads characters into the internal buffer;
   *
   * @param length
   */
  private void read(final int length) throws IOException {
    enlargeBuffer(offset + length);
    while (chars < length) {
      int i = reader.read(buffer, offset + chars, length - chars);
      if (i < 0) throw new IOException();
      chars += i;
    }
  }

  private void enlargeBuffer(final int size) {
    if (buffer.length < size) {
      char[] newBuffer = new char[size + 50];
      System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
      buffer = newBuffer;
    }
  }

  private void commit(final int size) {
    offset += size;
    chars -= size;
  }

  private StrBuffer2 createMessage() {
    StrBuffer2 result = new StrBuffer2(new String(buffer, 0, offset));
    System.arraycopy(buffer, offset, buffer, 0, chars);
    offset = 0;
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("This is a StrBufferReader object\n")
            .append(String.format("Offset: %d\n", offset))
            .append(String.format("Chars: %d\n", chars))
            .append(String.format("Level: %d\n", level))
            .append("Buffer content: [")
            .append(new String(buffer, 0, offset + chars))
            .append("]");
    return sb.toString();
  }

}
