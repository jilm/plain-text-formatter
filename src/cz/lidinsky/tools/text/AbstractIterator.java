/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 *
 * @author jilm
 */
abstract class AbstractIterator {

  abstract boolean isAtTheEnd();

  abstract boolean next();

  abstract String getText();

  abstract StrCode getCode();

  abstract WordIterator getWordIterator();

  /**
   * Stores the position into the internal stack.
   */
  abstract void push();

  /**
   * Pops the position from the internal stack.
   */
  abstract void pop();

  abstract int getText(char[] buffer, int offset, int length);

  abstract boolean isEmpty();

}

