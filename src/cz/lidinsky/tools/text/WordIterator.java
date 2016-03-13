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
public class WordIterator {

  private final String buffer;
  private final int offset;
  private final int length;
  private int cursor;
  private int wordLength;

  public WordIterator(String buffer, int offset, int length) {
    this.buffer = buffer == null ? "" : buffer;
    this.offset = Math.min(offset, this.buffer.length());
    this.length = Math.min(length, this.buffer.length() - this.offset);
    this.cursor = 0;
    skipWhitespace();
    this.wordLength = calculateWordLength();
  }

  /**
   * Finds first non whitespace character.
   */
  private void skipWhitespace() {
    while (cursor < length) {
      if (Character.isWhitespace(buffer.charAt(offset + cursor))) {
        cursor++;
      } else {
        return;
      }
    }
  }

  public boolean isAtTheEnd() {
    return cursor >= length;
  }

  /**
   * Muve cursor to the next word.
   *
   * @return false if the end of the text was reached and no more word is
   *            available.
   */
  public boolean next() {
    // skip actual word
    cursor += wordLength;
    // skip whitespaces
    skipWhitespace();
    // calculate word length
    this.wordLength = calculateWordLength();
    // return
    return this.wordLength > 0;
  }

  private int calculateWordLength() {
    int count = 0;
    while (cursor + count < length) {
      if (!Character.isWhitespace(buffer.charAt(offset + cursor + count))) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int getChars(char[] buffer, int offset, int length) {
    int size = Math.min(wordLength, length);
    if (size > 0) {
      this.buffer.getChars(
              this.offset + cursor,
              this.offset + cursor + wordLength, buffer, offset);
    }
    return size;
  }

  /**
   * Returns length of the word that is available. Returns zero, if the
   * end of the string has been reached.
   *
   * @return lenght of the actual word or zero, if there are no more words
   */
  public int getLength() {
    return wordLength;
  }

}
