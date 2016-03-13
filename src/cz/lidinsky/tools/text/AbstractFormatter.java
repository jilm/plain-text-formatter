/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 *
 */
abstract class AbstractFormatter {

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer and it is empty,
   *            true otherwise
   */
  abstract protected boolean formatLine(char[] buffer, int offset, int length);


}
