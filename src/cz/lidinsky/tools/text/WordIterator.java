/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 * Splits the given string into words and iterates over tham.
 *
 * @author jilm
 */
public class WordIterator {

  /** The whole string that will be split. */
  private final String buffer;

  /**
   * First character in the buffer. Always positive number that is less than
   * the buffer.length
   */
  private final int offset;

  /**
   * Length of the given sentence. Always positive number, where offset
   * lenght is less or equal than buffer.length
   */
  private final int length;

  /** Pointer to the first character of the actual word. */
  private int cursor;

  /** Length of the actual word. */
  private int wordLength;

  /**
   * Finds the first word in the given buffer, so the method getWord returns
   * the first word.
   *
   * @param buffer
   *            the string to be split into the words
   *
   * @param offset
   *            the index of the first character in the buffer
   *
   * @param length
   *            the lenght of the string
   */
  public WordIterator(String buffer, int offset, int length) {
    this.buffer = buffer == null ? "" : buffer;
    this.offset = StrUtils.validateArrayOffset(buffer.length(), offset);
    this.length = StrUtils.validateArrayLength(buffer.length(), offset, length);
    this.cursor = 0;
    skipWhitespace(); // find first non whitespace character
    this.wordLength = calculateWordLength(); // calculate the word length
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

  private int skipWhitespace(int position) {
    int size = 0;
    while (position + size < length) {
      if (Character.isWhitespace(buffer.charAt(position + size))) {
        size ++;
      } else {
        return size;
      }
    }
    return size;
  }

  private int skipWord(int position) {
    int size = 0;
    while (position + size < length) {
      if (!Character.isWhitespace(buffer.charAt(position + size))) {
        size ++;
      } else {
        return size;
      }
    }
    return size;
  }


  /**
   * Returns true is there are no more words to iterate over.
   */
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
    return calculateWordLength(cursor);
  }

  int calculateWordLength(int position) {
    int count = 0;
    while (position + count < length) {
      if (!Character.isWhitespace(buffer.charAt(offset + position + count))) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int getChars(char[] buffer, int offset, int length) {
    offset = StrUtils.validateArrayOffset(buffer.length, offset);
    length = StrUtils.validateArrayLength(buffer.length, offset, length);
    int size = Math.min(wordLength, length);
    if (size > 0) {
      this.buffer.getChars(
              this.offset + cursor,
              this.offset + cursor + size, buffer, offset);
    }
    return size < 0 ? 0 : size;
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

  public int getLength(int length) {
    int avaylable = 0;
    int delimiter = 0;
    int position = cursor + skipWhitespace(cursor);
    while (true) {
      int size = skipWord(position);
      if (size == 0) {
        return avaylable;
      } else if (avaylable + delimiter + size > length) {
        return avaylable;
      } else {
        avaylable += delimiter + size;
        position += size;
        position += skipWhitespace(position);
        delimiter = 1;
      }
    }
  }

}
