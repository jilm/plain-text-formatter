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
class HeadFormatter extends AbstractFormatter {

  private String head;

  HeadFormatter(CountingIterator iterator) {
    String text = iterator.getText();
    StrCode code = iterator.getCode();
    int level = iterator.getHeadLevel();
    if (!StrUtils.isBlank(text)) {
      head = iterator.getFullHeadNumber() + " " + getDecorator(level) + " " + text + " " + getDecorator(level);
    } else {
      head = "Chapter" + " " + iterator.getFullHeadNumber();
    }
    iterator.next();
  }

  static String getDecorator(int level) {
    char[] decorator = new char[5 - level];
    Arrays.fill(decorator, '-');
    return new String(decorator);
  }

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer, true otherwise
   */
  @Override
  protected boolean formatLine(char[] buffer, int offset, int length) {
    if (head != null) {
      head.getChars(0, Math.min(head.length(), length), buffer, offset);
      head = null;
      return true;
    } else {
      return false;
    }
  }

}
