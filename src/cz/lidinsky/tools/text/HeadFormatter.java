/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 * Main formatter class.
 */
class HeadFormatter extends AbstractFormatter {

  private String head;

  HeadFormatter(CountingIterator iterator) {
    this.head = iterator.getText();
    iterator.next();
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
