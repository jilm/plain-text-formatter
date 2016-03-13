/*
 *  Copyright 2015, 2016 Jiri Lidinsky
 *
 *  This file is part of java tools library.
 *
 *  java tools is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  java tools library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with java tools library. If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.tools.text;

import static cz.lidinsky.tools.text.StrUtils.isBlank;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  String buffer which places a special marks between the characters. These
 *  marks are then used to format the whole text.
 *
 *  <p>Some rules follow:
 *  <ul>
 *    <li>There are some inline codes: TEXT, STRONG, EMPHASIZE, LITERAL.
 *    <li>Text is organized into the paragraphs. The paragraph code doesn'n
 *        contain any text. The paragraph may be followed by inline codes, any
 *        other code finishes the paragraph.
 *    <li>Empty paragraphs are deleted.
 *    <li>Appendices are placed at the end of the buffer.
 *    <li>Following codes may be marked in order to reference them later:
 *        HEAD, APPENDIX, ITEM
 *  </ul>
 */
public class StrBuffer {

  private final StringBuilder sb;

  /**
   * At first, each code is written into the pending queue, and after it is
   * obvious, that it is not an empty code, it is moved into the output buffer
   * and into the code stack.
   */
  private final Deque<StrCode> pendingCodes;
  private final Deque<String> pendingTexts;

  /** Contains a sequence of opened codes. */
  private final Deque<StrCode> codeStack;

  /**
   *  Creates new empty buffer.
   */
  public StrBuffer() {
    this.sb = new StringBuilder();
    this.headLevel = 0;
    this.cursor = 0;
    this.appendices = false;
    this.pendingCodes = new ArrayDeque<>();
    this.pendingTexts = new ArrayDeque<>();
    this.codeStack = new ArrayDeque<>();
  }

  //--------------------------------------------------------- Public Interface.

  /** Indicate that there are some appendices at the end of the text. */
  private boolean appendices;

  /** Actual head level. */
  private int headLevel = 0;

  //----------------------------------------------------------- Heads handling.

  /**
   * Appends a head of the actual head level.
   *
   * @param head
   *            a text of the head, may be empty or null
   *
   * @return this object
   */
  public StrBuffer appendHead(String head) {
    return appendHead(headLevel, head);
  }

  /**
   * Appends a head, one level below the actual level. The actual head level
   * will be subtracted by one.
   *
   * @param head
   *            a text of the head, may be empty or null
   *
   * @return this object
   */
  public StrBuffer appendSubHead(String head) {
    return appendHead(headLevel + 1, head);
  }

  @Deprecated
  public StrBuffer appendUpperHead(String head) {
    return appendHead(headLevel - 1, head);
  }

  /**
   * Moves actual head pointer one level up. If it already is at the level zero,
   * nothing happens.
   *
   * @return this object
   */
  public StrBuffer closeHead() {
    headLevel --;
    return this;
  }

  /**
   * Appends given head together with the given head level.
   *
   * @param level
   *            head level. Heads may be arranged hierarchicaly where smaller
   *            number denotes head that is hierarchically higher. Valid values
   *            are between 0 and 4. Level parameter is validated and if less
   *            than 0, zero is used, if higher than 4, 4 is used.
   *
   * @param head
   *            text of the head. Should not be too long. If null, empty string
   *            is used instead.
   */
  protected StrBuffer appendHead(int level, String head) {
    level = level < 0 ? 0 : level;
    level = level > 4 ? 4 : level;
    StrCode code = StrCode.HEAD4;
    switch (level) {
      case 0:
        code = StrCode.HEAD0;
        break;
      case 1:
        code = StrCode.HEAD1;
        break;
      case 2:
        code = StrCode.HEAD2;
        break;
      case 3:
        code = StrCode.HEAD3;
        break;
      case 4:
        code = StrCode.HEAD4;
        break;
    }
    close(code.getLevel());
    pendingCodes.add(code);
    pendingTexts.add(head == null ? "" : head);
    headLevel = level;
    return this;
  }

  /**
   *
   */
  protected void closeLastCode() {
    if (!pendingCodes.isEmpty()) {
      pendingCodes.removeLast();
      pendingTexts.removeLast();
      if (!pendingCodes.isEmpty() && pendingCodes.getLast() == StrCode.MARK) {
        pendingCodes.removeLast();
        pendingTexts.removeLast();
      }
    } else if (!codeStack.isEmpty()) {
      codeStack.pop();
    } else {
    }
  }

  /**
   * Closes all of the open elements up to the given level.
   */
  protected void close(int level) {
    StrCode code = getLastOpenedCode();
    while (code != null && code.getLevel() > level) {
      closeLast();
      code = getLastOpenedCode();
    }
  }

  protected StrCode getLastOpenedCode() {
    if (!pendingCodes.isEmpty()) {
      return pendingCodes.getLast();
    } else if (!codeStack.isEmpty()) {
      return codeStack.getFirst();
    } else {
      return null;
    }
  }

  protected boolean isOpened() {
    return !pendingCodes.isEmpty() || !codeStack.isEmpty();
  }

  /**
   *
   */
  public StrBuffer appendAsAppendix(StrIterator iterator) {
    appendAtTheEnd(StrCode.APPENDIX);
    while (!iterator.isAtTheEnd()) {
      appendAtTheEnd(iterator.getCode(), iterator.getText());
      iterator.next();
    }
    this.appendices = true;
    return this;
  }

  public int getHeadLevel() {
    return headLevel;
  }

  public StrBuffer appendInBrackets(String text) {
    if (!isEmpty(text)) {
      append(StrCode.TEXT, "(" + text + ")");
    }
    return this;
  }

  protected void closeLast() {
    // get latest open code
    StrCode lastCode;
    if (!pendingCodes.isEmpty()) {
      lastCode = pendingCodes.getLast();
    } else if (!codeStack.isEmpty()) {
      lastCode = codeStack.getFirst();
    } else {
      // nothing to close
      return;
    }
    // close it
    switch (lastCode) {
      case PARAGRAPH:
        closeParagraph();
        break;
      case ITEM:
        closeItem();
        break;
      case LIST_ORDERED:
      case LIST_UNORDERED:
        closeList();
        break;
      case HEAD0:
      case HEAD1:
      case HEAD2:
      case HEAD3:
      case HEAD4:
        closeLastCode();
        break;
      default:
        closeLastCode();
        break;
    }
  }

  //------------------------------------------------------- Paragraph handling.

  /**
   * Starts new paragraph.
   */
  public StrBuffer startParagraph() {
    closeParagraph();
    pendingCodes.add(StrCode.PARAGRAPH);
    pendingTexts.add("");
    return this;
  }

  protected void closeParagraph() {
    if (isParagraphPending()) {
      pendingCodes.removeLast();
      pendingTexts.removeLast();
    } else if (isParagraphOpen()) {
      codeStack.pop();
    } else {
    }
  }

  /**
   * Starts new paragraph and immediatly add the given text.
   */
  public StrBuffer startParagraph(String text) {
    startParagraph();
    append(text);
    return this;
  }

  /**
   * Append given text into the latest open paragraph.
   */
  public StrBuffer append(String text) {
    if (!isBlank(text)) {
      if (!isParagraphOpen()) {
        startParagraph();
      }
      writePendingCodes();
      append(StrCode.TEXT, text);
    }
    return this;
  }

  /**
   * Returns true if and only if the latest structural code was paragraph.
   */
  protected boolean isParagraphOpen() {
    return (!codeStack.isEmpty() && codeStack.getFirst() == StrCode.PARAGRAPH)
      || isParagraphPending();
  }

  protected boolean isParagraphPending() {
    return !pendingCodes.isEmpty()
      && pendingCodes.getLast() == StrCode.PARAGRAPH;
  }

  protected void writePendingCodes() {
    while (!pendingCodes.isEmpty()) {
      StrCode code = pendingCodes.removeFirst();
      String text = pendingTexts.removeFirst();
      append(code, text);
      if (code != StrCode.MARK) {
        codeStack.push(code);
      }
    }
  }

  //------------------------------------------------------------ List handling.

  public StrBuffer startOrderedList() {
    closeParagraph();
    pendingCodes.add(StrCode.LIST_ORDERED);
    pendingTexts.add("");
    pendingCodes.add(StrCode.ITEM);
    pendingTexts.add("");
    return this;
  }

  public StrBuffer startUnorderedList() {
    closeParagraph();
    pendingCodes.add(StrCode.LIST_UNORDERED);
    pendingTexts.add("");
    pendingCodes.add(StrCode.ITEM);
    pendingTexts.add("");
    return this;
  }

  protected boolean isListOpen() {
    return codeStack.contains(StrCode.LIST_ORDERED)
      || codeStack.contains(StrCode.LIST_UNORDERED)
      || isListPending();
  }

  protected boolean isListPending() {
    return pendingCodes.contains(StrCode.LIST_ORDERED)
            || pendingCodes.contains(StrCode.LIST_UNORDERED);
  }

  public StrBuffer startItem() {
    if (isListOpen()) {
      closeParagraph();
      closeItem();
      pendingCodes.add(StrCode.ITEM);
      pendingTexts.add("");
    }
    return this;
  }

  public StrBuffer startItem(int mark) {
    if (isListOpen()) {
      closeParagraph();
      closeItem();
      mark(mark);
      pendingCodes.add(StrCode.ITEM);
      pendingTexts.add("");
    }
    return this;
  }

  public StrBuffer startItem(String key) {
    if (isListOpen()) {
      closeParagraph();
      closeItem();
      pendingCodes.add(StrCode.ITEM);
      pendingTexts.add(key);
    }
    return this;
  }

  public StrBuffer startItem(String key, int mark) {
    if (isListOpen()) {
      closeParagraph();
      closeItem();
      mark(mark);
      pendingCodes.add(StrCode.ITEM);
      pendingTexts.add(key);
    }
    return this;
  }

  protected void closeItem() {
    if (!pendingCodes.isEmpty() && pendingCodes.getLast() == StrCode.ITEM) {
      pendingCodes.removeLast();
      pendingTexts.removeLast();
    } else if (!codeStack.isEmpty() && codeStack.getFirst() == StrCode.ITEM) {
      codeStack.pop();
    } else {
    }
  }

  public StrBuffer closeList() {
    if (isListOpen()) {
      closeParagraph();
      closeItem();
      if (isListPending()) {
        pendingCodes.removeLast();
        pendingTexts.removeLast();
      } else {
        codeStack.pop();
        append(StrCode.LIST_END);
      }
    }
    return this;
  }

  //---------------------------------------- Marks and references manipulation.

  private int markCounter;

  public int reserveMark() {
    return markCounter++;
  }

  protected void mark(int mark) {
    pendingCodes.add(StrCode.MARK);
    pendingTexts.add(Integer.toHexString(mark));
  }

  public StrBuffer reference(int mark) {
    if (!isParagraphOpen()) {
       startParagraph();
    }
    writePendingCodes();
    append(StrCode.REFERENCE, Integer.toHexString(mark));
    return this;
  }

  @Override
  public String toString() {
    while (isOpened()) {
      closeLast();
    }
    return sb.toString();
  }

  //--------------------------------------- Internal Mark Manipulation Methods.

  /** An index into the buffer where the next elemnet will be appended. */
  private int cursor;

  protected void append(final StrCode code) {
    sb.insert(cursor ++, code.getCode());
    sb.insert(cursor, ZERO_LENGTH_MARK);
    cursor += 2;
  }

  protected void appendAtTheEnd(final StrCode code) {
    sb.append(code.getCode());
    sb.append(ZERO_LENGTH_MARK);
  }

  private static final int MAX_LENGTH = 70 * 70;
  private static final String ZERO_LENGTH_MARK = "00";

  protected void append(final StrCode code, String text) {
    int length = text.length();
    if (length > MAX_LENGTH) {
      append(code, text.substring(0, MAX_LENGTH));
      append(StrCode.EXTENDED, text.substring(MAX_LENGTH));
    } else {
      sb.insert(cursor ++, code.getCode());
      sb.insert(cursor ++, (char)('0' + (int)(length / 70)));
      sb.insert(cursor ++, (char)('0' + (int)(length % 70)));
      sb.insert(cursor, text);
      cursor += length;
    }
  }

  protected void appendAtTheEnd(final StrCode code, String text) {
    sb.append(code.getCode());
    int length = text.length();
    sb.append((char)('0' + (int)(length / 70)));
    sb.append((char)('0' + (int)(length % 70)));
    sb.append(text);
  }

  private boolean isEmpty(String text) {
    return text == null || text.length() == 0;
  }

  private StrCode getCode(int index) {
    validateIndex(index);
    return StrCode.getStrCode(sb.charAt(index));
  }

  private int getLength(int index) {
    validateIndex(index);
    return (int)(sb.charAt(index + 1) - '0') * 70
      + (int)(sb.charAt(index + 2) - '0');
  }

  private int next(int index) {
    validateIndex(index);
    return index + getLength(index) + 3;
  }

  private int validateIndex(int index) {
    if (index >= sb.length() || index < 0) {
      throw new IndexOutOfBoundsException();
              //.setCode(ExceptionCode.INDEX_OUT_OF_BOUNDS)
              //.set("index", index)
              //.set("buffer size", sb.length());
    } else {
      return index;
    }
  }

  private String getText(int index) {
    validateIndex(index);
    int length = getLength(index);
    String text = sb.substring(index + 3, index + 3 + length);
    if (getCode(next(index)) == StrCode.EXTENDED) {
      text += getText(next(index));
    }
    return text;
  }

}
