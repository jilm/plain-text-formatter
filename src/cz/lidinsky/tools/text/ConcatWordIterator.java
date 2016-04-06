/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Splits the given string into words and iterates over tham.
 *
 * @author jilm
 */
public class ConcatWordIterator extends WordIterator {

  private final Queue<WordIterator> iterators;

  private WordIterator actualIterator;

  public ConcatWordIterator() {
    super("", 0, 0);
    iterators = new ArrayDeque<>();
    actualIterator = new WordIterator("", 0, 0);
  }

  public void add(WordIterator iterator) {
    iterators.add(iterator);
  }

  /**
   * Returns true is there are no more words to iterate over.
   */
  @Override
  public boolean isAtTheEnd() {
    return actualIterator.isAtTheEnd() && iterators.isEmpty();
  }

  /**
   * Muve cursor to the next word.
   *
   * @return false if the end of the text was reached and no more word is
   *            available.
   */
  @Override
  public boolean next() {
    actualIterator.next();
    while (actualIterator.isAtTheEnd() && !iterators.isEmpty()) {
      actualIterator = iterators.poll();
    }
    return actualIterator.getLength() > 0;
  }

  @Override
  public int getChars(char[] buffer, int offset, int length) {
    return actualIterator.getChars(buffer, offset, length);
  }

  /**
   * Returns length of the word that is available. Returns zero, if the
   * end of the string has been reached.
   *
   * @return lenght of the actual word or zero, if there are no more words
   */
  @Override
  public int getLength() {
    return actualIterator.getLength();
  }

}
