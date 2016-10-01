/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.Stack;

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

  public String format(String buffer) {
    AbstractTextFormatter formatter = deserialize(buffer);
    Line line = new Line(80);
    while (!formatter.isEmpty()) {
      line.reset();
      formatter.formatLine(line);
      System.out.println(line.toString());
    }
    return null;
  }

  private AbstractTextFormatter deserialize(String buffer) {
    AbstractIterator iterator = new StrIterator(buffer);
    AbstractTextFormatter pointer = null;
    String key = null;
    String value = null;
    Stack<Integer> chapterCounter = new Stack<>();
    chapterCounter.push(1);
    boolean tempLevel = false;
    while (true) {
      if (iterator.isAtTheEnd()) return pointer;
      StrCode code = iterator.getCode();
      System.out.println(code);
      switch (code) {
        case END:
          if (pointer instanceof ChapterTextFormatter) {
            chapterCounter.pop();
            chapterCounter.push(chapterCounter.pop() + 1);
          }
          pointer = pointer.getParent();
          break;
        case ARTICLE:
          pointer = new ArticleTextFormatter();
          break;
        case LIST_ORDERED:
          pointer = new ListTextFormatter(pointer, true);
          break;
        case LIST_UNORDERED:
          pointer = new ListTextFormatter(pointer, false);
          break;
        case PARAGRAPH:
          pointer = new ParagraphTextFormatter(pointer, true, 0);
          break;
        case TABLE:
          pointer = new TableTextFormatter(pointer);
          break;
        case TEXT:
          pointer = new TextFormatter(pointer, iterator.getText());
          break;
        case CHAPTER:
          int chapterNumber = chapterCounter.pop();
          chapterCounter.push(chapterNumber);
          pointer = new ChapterTextFormatter(pointer, chapterNumber);
          chapterCounter.push(1);
          break;
        case KEY:
          key = iterator.getText();
          break;
        case VALUE:
          value = iterator.getText();
          ((TableTextFormatter)pointer).putValue(key, value);
          iterator.next();
          break;
      }
      iterator.next();
    }
    //return pointer;
  }


}
