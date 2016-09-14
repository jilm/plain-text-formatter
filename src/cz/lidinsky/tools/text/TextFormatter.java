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

import static cz.lidinsky.tools.text.StrUtils.isBlank;

/**
 *
 */
public class TextFormatter {

  private final WordIterator2 words;
  private final int alignment;
  private final int decoration;

  TextFormatter(String text, int alignment, int decoration) {
    if (isBlank(text)) {
      throw new IllegalArgumentException();
    }
    this.words = new WordIterator2(text, 0, text.length());
    this.alignment = alignment;
    this.decoration = decoration;
  }

  protected int formatLine(char[] buffer, int offset, int length) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  protected boolean isEmpty() {
    return words.isEmpty();
  }

  protected int getLength(int length) {
    return words.getLength(length);
  }

  protected int getWords(int length) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  int getWord(char[] buffer, int offset) {
    return words.getWord(buffer, offset, buffer.length - offset);
  }

  void getChars(char[] buffer, int offset, int length) {
    words.getChars(buffer, offset, length);
  }

}
