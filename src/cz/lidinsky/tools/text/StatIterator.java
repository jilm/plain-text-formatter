package cz.lidinsky.tools.text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class StatIterator extends AbstractIterator {

  private final AbstractIterator iterator;

  StatIterator(AbstractIterator iterator) {
    this.iterator = iterator;
    references = new HashMap<>();
    headCount = new int[5];
  }

  private void initialize() {
    references.clear();
    Arrays.fill(headCount, 0);
  }

  private final int[] headCount;
  private final Map<String, String> references;

  void scan() {
    iterator.push();
    String mark = null;
    while (!iterator.isAtTheEnd()) {
      StrCode code = iterator.getCode();
      switch (code) {
        case HEAD0:
        case HEAD1:
        case HEAD2:
        case HEAD3:
        case HEAD4:
          headCount[getHeadLevel(code)]++;
          if (mark != null) {
            references.put(mark, iterator.getText());
            mark = null;
          }
          break;
        case MARK:
          mark = iterator.getText();
          break;
      }
    }
    iterator.pop();
  }

  protected String getReference(String mark) {
    if (references.containsKey(mark)) {
      return "see " + references.get(mark);
    } else {
      return String.format("<A mark for id: %s was not found!>", mark);
    }
  }

  @Override
  public boolean next() {
    return iterator.next();
  }

  @Override
  public StrCode getCode() {
    StrCode code = iterator.getCode();
    switch (code) {
      case REFERENCE:
        return StrCode.TEXT;
      default:
        return code;
    }
  }

  @Override
  public String getText() {
    StrCode code = iterator.getCode();
    switch (code) {
      case REFERENCE:
        return getReference(iterator.getText());
      default:
        return iterator.getText();
    }
  }

  @Override
  public WordIterator getWordIterator() {
    return iterator.getWordIterator();
  }

  /**
   * Copy actual text into the given array buffer.
   *
   * @param buffer
   *            an array where the text will be placed
   *
   * @param offset
   *            an index where the first char will be placed
   *
   * @param length
   *            max number of characters that are available in the buffer
   *
   * @return the number of characters that was placed into the buffer
   */
  @Override
  public int getText(char[] buffer, int offset, int length) {
    return iterator.getText(buffer, offset, length);
  }

  @Override
  boolean isAtTheEnd() {
    return iterator.isAtTheEnd();
  }

  /**
   * Returns false if the actual text content is not empty.
   *
   * @return true if the given text will be zero length
   */
  @Override
  public boolean isEmpty() {
    return iterator.isEmpty();
  }

  protected int getHeadLevel(StrCode code) {
    switch (code) {
      case HEAD0:
        return 0;
      case HEAD1:
        return 1;
      case HEAD2:
        return 2;
      case HEAD3:
        return 3;
      case HEAD4:
        return 4;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  void push() {
    iterator.push();
  }

  @Override
  void pop() {
    iterator.pop();
  }



}
