/*
 * Copyright (C) 2016 jilm
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.lidinsky.tools.text;

import static cz.lidinsky.tools.text.StrUtils.isBlank;
import java.util.List;
import java.util.ArrayList;

/**
 * Class of the exception: NullPointerException,
 * the message: ...
 *
 * Class of the exception: NullPointerException,
 * there is no message.
 *
 *
 *
 *
 */
public class ExceptionStatementBuilder {

  public String build(Throwable ex) {
    ArticleBuilder builder = new ArticleBuilder("Exception Statement");
    if (ex == null) {

    } else {
      List<Throwable> chain = getExceptionChain(ex);
      if (chain.size() > 1) {
        builder.appendParagraph().add(
            String.format("It is a chain of %d exceptions:", chain.size()));
        // exception classes
        ListBuilder listBuilder = builder.appendChapter("Exception classes")
          .appendList(true);
        for (Throwable exception : chain) {
          listBuilder.appendItem().appendParagraph().add(exception.getClass().getName());
        }
        // exception messages
        listBuilder = builder.appendChapter("Messages").appendList(true);
        for (Throwable exception : chain) {
          listBuilder.appendItem().appendParagraph().add(exception.getMessage());
        }
        // more info
        TableBuilder tableBuilder = builder.appendChapter("More info")
          .appendTable();
        // stack trace
        listBuilder = builder.appendChapter("Stack trace").appendList(true);
        for (Throwable exception : chain) {
          listBuilder.appendItem().appendParagraph().add(
              exception.getStackTrace()[0].toString());
        }

      } else {

        TableBuilder tableBuilder = builder.appendTable();
        tableBuilder.appendValue("class", ex.getClass().getName());
        tableBuilder.appendValue("message", ex.getMessage());
        tableBuilder.appendValue("stack", ex.getStackTrace()[0].toString());

      }
    }
    return builder.serialize();
  }


  public String toString(Throwable ex) {
    String buffer = build(ex);
    return new Formatter().format(buffer);
  }


  private static List<Throwable> getExceptionChain(Throwable e) {
    List<Throwable> result = new ArrayList<>();
    while (e != null) {
      result.add(e);
      e = e.getCause();
    }
    return result;
  }

  public static void main(String[] args) {
    Exception e = new NullPointerException();
    System.out.println(new ExceptionStatementBuilder().toString(e));
  }

}
