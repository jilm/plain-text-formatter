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

  public void build(Throwable ex) {
    if (ex == null) {

    } else {
      String message = ex.getMessage();
      boolean blankMessage = StrUtils.isBlank(message);
      Throwable cause = ex.getCause();
      //ArticleBuilder parent = new ArticleBuilder();
      //parent.setTitle("This is the exception statement");

      if (isBlank(message)) {
        //parent.appendParagraph()
          //.append(
          //String.format("Class of the exception: %s, exception is withnout any message.", ex.getClass().getName()));
      } else {
        //parent.appendTable()
        //      .append("Class", ex.getClass().getName())
        //      .append("Message", message);
      }
    }
    // parent.appendParagraph().append("The class of the exception: ")
    //   .append(ex.getClass().getName());

  }


}
