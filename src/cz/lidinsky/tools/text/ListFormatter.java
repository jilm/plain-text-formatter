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
class ListFormatter extends AbstractFormatter {

  private final CountingIterator iterator;
  private AbstractFormatter childFormatter;
  private boolean end;
  private final boolean isOrdered;
  //private int order;
  private int filled;

  ListFormatter(CountingIterator iterator) {
    this.iterator = iterator;
    this.isOrdered = iterator.getCode() == StrCode.LIST_ORDERED;
    this.iterator.next(); // skip the LIST code
    this.end = false;
    //this.order = 0;
  }

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer, true otherwise
   */
  @Override
  protected boolean formatLine(
      char[] buffer, final int offset, final int length) {

    filled = 4;
    while (true) {
      if (end) return false;
      if (childFormatter != null) {
        if (childFormatter.formatLine(
              buffer, offset + filled, length - filled)) {
          return true;
        } else {
          childFormatter = null;
        }
      } else {
        if (iterator.isAtTheEnd()) {
          end = true;
        } else {
          switch (iterator.getCode()) {
            case TEXT:
            case STRONG:
            case EMPHASIZE:
            case LITERAL:
            case REFERENCE:
            case PARAGRAPH:
              //getPrefix(0, order, buffer, offset, 4);
              childFormatter = new ParagraphFormatter(iterator);
              break;
            case ITEM:
              //order++;
              if (iterator.isEmpty()) {
                getPrefix(0, iterator.getListItemCount(), buffer, offset, 4);
              } else {
                filled = iterator.getText(buffer, offset, length);
                buffer[offset + filled] = ':';
                filled += 2;
              }
              iterator.next();
              //childFormatter = new ParagraphFormatter(iterator);
              break;
            case LIST_ORDERED:
            case LIST_UNORDERED:
              childFormatter = new ListFormatter(iterator);
              break;
            case LIST_END:
              end = true;
              if (!iterator.isAtTheEnd()) iterator.next();
              break;
            case MARK:
              iterator.next();
              break;
            default:
              end = true;
              break;
          }
        }
      }
    }
  }

  public static final char[] BULLETS = {'*', 'o', '-', '+', '#', '>'};

  public static char getBullet(int level) {
    return BULLETS[(level >= 0 ? level : -level) % BULLETS.length];
  }

  public static void getBullet(int level, char[] buffer, int offset, int length) {
    if (length == 1) {
      buffer[offset] = getBullet(level);
    } else if (length > 1) {
      buffer[offset + length - 2] = getBullet(level);
    }
  }

  public static String getOrderNumber(int level, int order) {
    return getNumericOrderNumber(order);
  }

  public static void getOrderNumber(int level, int order, char[] buffer, int offset, int length) {
    String number = getOrderNumber(level, order);
    int size = number.length();
    if (size > length) {
      Arrays.fill(buffer, offset, offset + length, '#');
    } else if (size == length) {
      number.getChars(0, size, buffer, offset);
    } else if (size == length + 1) {
      number.getChars(0, size, buffer, offset);
      buffer[offset + size] = ')';
    } else {
      number.getChars(0, size, buffer, offset + length - size - 2);
      buffer[offset + length - 2] = ')';
    }
  }

  public static String getNumericOrderNumber(int order) {
    return Integer.toString(order);
  }

  private void getPrefix(int level, int order, char[] buffer, int offset, int length) {
    if (isOrdered) {
      getOrderNumber(level, order, buffer, offset, length);
    } else {
      getBullet(level, buffer, offset, length);
    }
  }
}
