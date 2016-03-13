/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

/**
 * An iterator which encapsulates all of the inlin codes into the paragraph
 * code. It doesn't return empty inline codes or whole paragraphs.
 *
 */
public class StrStateIterator {

  private final StrIterator iterator;

  /** Indicates that the last block code was the paragraph. */
  private boolean paragraph;

  /** Indicates that the end of the stream has been reached. */
  private boolean end;

  private StrCode actualCode;

  private String actualText;

  public StrStateIterator(StrIterator iterator) {
    this.iterator = iterator;
    this.paragraph = false;
    this.end = false;
    findNext();
  }

  public void next() {
    if (end) {
      throw new java.util.NoSuchElementException();
    } else {
      findNext();
    }
  }

  private void findNext() {
    while (true) {
      if (iterator.isAtTheEnd()) {
        end = true;
        return;
      } else if (!isInline(iterator.getCode())) {
        actualCode = iterator.getCode();
        actualText = iterator.getText();
        iterator.next();
        paragraph = iterator.getCode() == StrCode.PARAGRAPH;
        return;
      } else {
        if (iterator.isEmpty()) {
          iterator.next();
        } else if (paragraph) {
          actualCode = iterator.getCode();
          actualText = iterator.getText();
          iterator.next();
          return;
        } else {
          actualCode = StrCode.PARAGRAPH;
          actualText = "";
          paragraph = true;
          return;
        }
      }
    }
  }

  public StrCode getCode() {
    return actualCode;
  }

  public String getText() {
    return actualText;
  }

  public boolean isEtTheEnd() {
    return end;
  }

  /**
   * Returns true if the given code represents inline piece of text.
   * @param code
   * @return
   */
  public static boolean isInline(StrCode code) {
    switch (code) {
      case TEXT:
      case STRONG:
      case EMPHASIZE:
      case LITERAL:
        return true;
      default:
        return false;
    }
  }

}
