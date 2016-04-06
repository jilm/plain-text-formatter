/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 * Format given marked input into the plain text.
 */
public class Formatter {

  public Formatter() { }

  /**
   *
   * @param iterator
   * @return
   */
  public String format(AbstractIterator iterator) {
    return new PageFormatter(iterator).format();
  }


}
