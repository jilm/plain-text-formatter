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
 * Common predecessor for all of the text formatters whose children are blocks
 * of text.
 */
abstract class BlockTextFormatter extends AbstractTextFormatter {

  /** Child formatters. */
  //protected final List<AbstractTextFormatter> children;

  /** */
  //protected final int depth;

  /** */
  //protected final int order;

  /** Line number relative to the first number of this list. */
  private int lineCounter;

  /** Order number of the item formatted. */
  private int childCounter;

  /** Line number for the current child. */
  private int lineOfChildCounter;

  /**
   *
   * @param depth
   * @param order
   */
  BlockTextFormatter(AbstractTextFormatter parent) {
    super(parent);
    //this.children = new ArrayList<>();
    //this.depth = depth;
    //this.order = order;
    this.lineCounter = 0;
    this.childCounter = 0;
    this.lineOfChildCounter = 0;
  }

  /**
   * This implementation iterates over the children and calls format line.
   *
   * @param buffer
   * @param offset
   * @param length
   *
   * @return number of characters
   */
  @Override
  boolean formatLine(Line line) {

    if (isEmpty()) return false;
    getPrefix(line);
    if (getChild().formatLine(line)) {
      lineCounter ++;
      lineOfChildCounter ++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * To influence the prefex or just indention of the child block of text
   * override this method.
   *
   * @param buffer
   * @param offset
   * @param length
   * @return
   */
  protected abstract void getPrefix(Line line);




  //BlockTextFormatter add(final AbstractTextFormatter text) {
  //  if (text != null && !text.isEmpty()) {
  //    children.add(text);
  //  }
  //  return this;
  //}

  int getLineNumber() {
    return lineCounter;
  }

  int getChildNumber() {
    return childCounter;
  }

  int getChildLineNumber() {
    return lineOfChildCounter;
  }

}
