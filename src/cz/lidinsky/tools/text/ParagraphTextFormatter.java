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
import java.util.List;

/**
 *
 */
public class ParagraphTextFormatter extends AbstractTextFormatter {

  final int ALLIGN_LEFT = 0;
  final int ALLIGN_RIGHT = 1;
  final int ALLIGN_CENTER = 2;

  /** It may not contain empty children! */
  private final List<TextFormatter> children;
  private boolean indent;
  private final int alignment;

  ParagraphTextFormatter(final boolean indentFirst, final int alignment) {
    this.children = new ArrayList<>();
    this.indent = indentFirst && alignment == ALLIGN_LEFT;
    this.alignment = alignment;
  }

  @Override
  protected int formatLine(char[] buffer, int offset, int length) {
    if (isEmpty()) return 0;
    int leading = indent  ? 4 : 0;
    indent = false;
    // check parameters
    // find out, how many characters are there awaylable
    int avaylable = getLength(length - leading);
    // if there is a word that is longer than free space
    boolean hyphen = avaylable == 0; // if it is necessary to break the long word
    if (hyphen) {
      getChild().getChars(buffer, offset + leading, length - leading);
      return length;
    } else {
      // compute leading spaces
      switch (alignment) {
        case ALLIGN_LEFT:
          break;
        case ALLIGN_RIGHT:
          leading = length - avaylable;
          break;
        case ALLIGN_CENTER:
          leading = (length - avaylable) / 2;
          break;
        default:
          assert false;
      }
      // fill-in words
      int filled = 0;
      int delimiter = 0;
      while (filled < avaylable) {
        filled += delimiter;
        filled += getChild().getWord(buffer, offset + leading + filled);
        delimiter = 1;
      }
      return filled;
    }
  }

  @Override
  protected boolean isEmpty() {
    while (!children.isEmpty()) {
      if (children.get(0).isEmpty()) {
        children.remove(0);
      } else {
        return false;
      }
    }
    return true;
  }

  @Override
  protected int getLength(int length) {
    if (children == null || children.isEmpty()) return 0;
    int awaylable = 0;
    int delimiter = 0;
    for (TextFormatter textFormatter : children) {
      int chars = textFormatter.getLength(length - awaylable);
      if (chars == 0) {
        return awaylable;
      } else {
        awaylable += delimiter + chars;
      }
      delimiter = 1;
    }
    return awaylable;
  }

  @Override
  protected int getWords(int length) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  private TextFormatter getChild() {
    if (isEmpty()) {
      return null;
    } else {
      return children.get(0);
    }
  }

  /**
   *
   * @param text
   *            text formatter to add. Null or empty arguments are silently
   *            ignored.
   */
  void add(final TextFormatter text) {
    if (text != null && !text.isEmpty()) {
      children.add(text);
    }
  }

}
