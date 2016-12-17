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

import java.util.NoSuchElementException;

/**
 * A class for table traversal.
 *
 */
public class Table extends StrBuffer2 {

  public Table(StrBuffer2 buffer) {
    super(buffer.buffer, buffer.offset);
  }

  public String get(String key) {
    int pointer = offset;
    int level = 0;
    String lastKey = "";
    // find first nested code
    while (pointer < buffer.length()) {
      StrCode code = getCode(pointer);
      System.out.print(code);
      if (code == StrCode.END) {
        level--;
      } else if (code == StrCode.KEY) {
        System.out.print(getText(pointer));
        lastKey = getText(pointer);
      } else if (code == StrCode.TEXT && lastKey.equals(key)) {
        return getText(pointer);
      } else if (code.isBlock()) {
        level++;
      } else {

      }
      System.out.println();
      pointer = next(pointer);
    }
    throw new NoSuchElementException();
  }



}
