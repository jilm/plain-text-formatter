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
 * @author jilm
 */
class ItemTextFormatter extends AbstractTextFormatter {

  private final List<AbstractTextFormatter> children;

  /** Line number relative to the first number of this list. */
  private int lineCounter;

  /** Order number of the item formatted. */
  private int childCounter;

  private int lineOfChildCounter;

  ItemTextFormatter() {
    this.children = new ArrayList<>();
    this.lineCounter = 0;
    this.childCounter = 0;
    this.lineOfChildCounter = 0;
  }

  @Override
  protected int formatLine(
      char[] buffer, final int offset, final int length) {

    if (isEmpty()) return 0;
    int prefSize = getPrefix(buffer, offset, length);
    int size = getChild().formatLine(buffer, offset + prefSize, length - prefSize);
    lineCounter ++;
    lineOfChildCounter ++;
    return size;
  }

  protected int getPrefix(char[] buffer, int offset, int length) {
    return 0;
  }

  protected AbstractTextFormatter getChild() {
    if (isEmpty()) return null;
    return children.get(0);
  }

  @Override
  protected boolean isEmpty() {
    if (children.isEmpty()) {
      return true;
    } else {
      if (children.get(0).isEmpty()) {
        childCounter ++;
        lineOfChildCounter ++;
        while (!children.isEmpty() && children.get(0).isEmpty()) {
          children.remove(0);
        }
      }
    }
    return children.isEmpty();
  }

  @Override
  protected int getLength(int length) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  protected int getWords(int length) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  void add(final AbstractTextFormatter text) {
    if (text != null && !text.isEmpty()) {
      children.add(text);
    }
  }

}
