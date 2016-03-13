/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.Arrays;

/**
 * Main formatter class.
 */
class PageFormatter {

  private final CountingIterator iterator;
  private AbstractFormatter formatter;
  private final int leftMargin = 2;
  private final int rightMargin = 2;
  private final char[] lineBuffer;
  private int indent;

  PageFormatter(AbstractIterator iterator) {
    this.iterator = new CountingIterator(iterator);
    lineBuffer = new char[79];
    this.indent = 0;
  }

  String format() {
    StringBuilder sb = new StringBuilder();
    formatter = getNestedFormatter();
    while (formatLine(lineBuffer, 0, 79)) {
      sb.append(lineBuffer)
        .append("\n");
    }
    return sb.toString();
  }

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer, true otherwise
   */
  protected boolean formatLine(char[] buffer, int offset, int length) {
    // erase the buffer
    Arrays.fill(buffer, ' ');
    // fill-in the prefix
    Arrays.fill(buffer, offset, offset + leftMargin, ' ');
    // pass the buffer on to the nested formatter
    while (formatter != null) {
      if (formatter.formatLine(
            buffer, offset + leftMargin + indent, length - leftMargin - indent - rightMargin)) {
        // fill-in suffix
        Arrays.fill(buffer, length - rightMargin, length, ' ');
        // return result
        return true;
      } else {
        formatter = getNestedFormatter();
      }
    }
    return false;
  }

  protected AbstractFormatter getNestedFormatter() {
    if (!iterator.isAtTheEnd()) {
      StrCode code = iterator.getCode();
      switch (code) {
        case HEAD0:
        case HEAD1:
        case HEAD2:
        case HEAD3:
        case HEAD4:
          //iterator.back();
          indent = 0;
          return new HeadFormatter(iterator);
        case LIST_UNORDERED:
        case LIST_ORDERED:
          indent = 2;
          return new ListFormatter(iterator);
        case PARAGRAPH:
          indent = 2;
          return new ParagraphFormatter(iterator);
        default:
          System.out.println("!!!" + code);
          iterator.next();
          if (!iterator.isAtTheEnd()) {
            return getNestedFormatter();
          } else {
            return null;
          }
      }
    } else {
      return null;
    }
  }

}
