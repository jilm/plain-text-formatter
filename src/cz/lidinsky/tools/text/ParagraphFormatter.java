/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 * Formats a continuous sequence of paragraphs. Paragraphs may be formatted
 * in two ways. It may be separated by an empty line, or the first line may
 * be intended.
 */
class ParagraphFormatter extends AbstractFormatter {

  private final AbstractIterator iterator;
  private WordIterator words;
  private boolean end;
  private int paragraphCounter;
  private int indentFirstLine;
  private int lineCounter;
  private boolean paragraphEnd;

  /**
   * Initialize the formatter of the paragraph.
   * @param iterator
   */
  ParagraphFormatter(AbstractIterator iterator) {
    this.iterator = iterator;
    this.words = collectWords(iterator);
    this.end = false;
    this.paragraphCounter = -1;
    this.indentFirstLine = 4;
    this.lineCounter = 0;
    this.paragraphEnd = true;
    while (!isEnd() && (words == null || words.isAtTheEnd())) this.next();
  }

  private WordIterator collectWords(AbstractIterator iterator) {
    ConcatWordIterator iterators = new ConcatWordIterator();
    while (!iterator.isAtTheEnd()) {
      StrCode code = iterator.getCode();
      switch (code) {
        case TEXT:
        case EMPHASIZE:
        case STRONG:
        case LITERAL:
          iterators.add(iterator.getWordIterator());
          iterator.next();
          break;
        case REFERENCE:
          iterators.add(new WordIterator(
              "see " + iterator.getText(), 0, 4 + iterator.getText().length()));
          iterator.next();
          break;
        default:
          return iterators;
      }
    }
    return iterators;
  }

  /**
   * Format one line of text into the given buffer.
   *
   * @return false if it was not possible to fill the buffer, true otherwise
   */
  @Override
  protected boolean formatLine(
      char[] buffer, final int offset, final int length) {

    if (!words.isAtTheEnd()) {
      StrUtils.fillLine(words, buffer, offset, length);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Finds next text to format.
   */
  private void next() {
    if (iterator.isAtTheEnd()) {
      end = true;
    } else {
      StrCode code = iterator.getCode();
      switch (code) {
        case TEXT:
        case EMPHASIZE:
        case STRONG:
        case LITERAL:
          words = iterator.getWordIterator();
          iterator.next();
          break;
        case REFERENCE:
          words = new WordIterator(
                  "see " + iterator.getText(), 0, 4 + iterator.getText().length());
          iterator.next();
          break;
        case PARAGRAPH:
          //filled = 100;
          paragraphEnd = true;
          //paragraphCounter++;
          words = iterator.getWordIterator();
          iterator.next();
          break;
        default:
          end = true;
          //filled = 100;
          break;
      }
    }

  }

  /** How many characters were placed into the buffer. */
  private int filled;

  /** Delimiter between words. */
  private String delimiter;

  /**
   * Get words from the words iterator to the given buffer until either the
   * buffer is full or the words is empty. It is expected that there is at
   * least one word in the words iterator.
   *
   * @param buffer
   * @param offset
   * @param length
   *
   * @return true, if the buffer is full, false if the buffer is not full, but
   *            there are not more words to fill-in
   */
  protected boolean leftAlign(char[] buffer, final int offset, final int length) {
    filled = 0;
    delimiter = "";
    while (length - filled > getWordLength() + delimiter.length()) {
      // fill-in the delimiter and a word
      filled += StrUtils.getChars(delimiter, buffer, offset + filled);
      filled += getChars(buffer, offset + filled, length - filled);
      delimiter = " ";
      // get next word
      nextWord();
      // if there is no more words in thes paragraph, exit
      if (isParagraphEnd()) return false;
    }
    return true;
  }

  /**
   * Returns true if all of the words from the actual paragraph were used.
   *
   * @return true if there is not anz word left in the current paragraph.
   */
  protected boolean isParagraphEnd() {
    return paragraphEnd || isEnd();
  }

  /**
   * Returns number of characters of the actual word. If there is no any
   * word in the actual paragraph, it returns zero.
   *
   * @return
   */
  protected int getWordLength() {
    return isParagraphEnd() ? 0 : words.getLength();
  }

  /**
   * Copy actual word into the given buffer.
   *
   * @param buffer
   * @param offset
   * @param length
   * @return number of characters really placed into the buffer
   */
  protected int getChars(char[] buffer, int offset, int length) {
    return isParagraphEnd() ? 0 : words.getChars(buffer, offset, length);
  }

  /**
   * Shift internal pointer to the new word of the actual paragraph.
   *
   */
  protected void nextWord() {
    if (!words.isAtTheEnd()) {
      words.next();
    } else {
      while (!isEnd() && words.isAtTheEnd()) next();
    }
  }

  /**
   * Shift internal pointer to the next paragraph.
   */
  protected void nextParagraph() {
    if (!isEnd()) {
      paragraphEnd = false;
      paragraphCounter++;
      lineCounter = 0;
    }
  }

  /**
   * Returns true, if there is not any paragraph left.
   *
   * @return
   */
  protected boolean isEnd() {
    return end && (words == null || words.isAtTheEnd());
  }

}
