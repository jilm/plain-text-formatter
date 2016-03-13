/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import static cz.lidinsky.tools.text.StrUtils.isBlank;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * It formats a sequence of paragraphs.
 */
class ItemFormatter extends AbstractFormatter {

  private final StrIterator iterator;

  private AbstractFormatter childFormatter;

  ItemFormatter(StrIterator iterator) {
    this.iterator = iterator;
  }

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer, true otherwise
   */
  @Override
  protected boolean formatLine(
      char[] buffer, final int offset, final int length) {

    while (!iterator.isAtTheEnd()) {

      AbstractFormatter formatter = getFormatter();

      if (formatter != null) {
        if (formatter.formatLine(buffer, offset, length)) {
          return true;
        }
      } else {
        return false;
      }
    }
    return false;
  }

  protected AbstractFormatter getFormatter() {
    if (childFormatter != null) {
      return childFormatter;
    } else {
      switch (iterator.getCode()) {
        case PARAGRAPH:
        case TEXT:
        case EMPHASIZE:
        case STRONG:
          childFormatter = new ParagraphFormatter(iterator);
          return childFormatter;
        default:
          return null;
      }
    }
  }

}
