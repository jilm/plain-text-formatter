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
    // find first nested code
    pointer = next(pointer);
    if (getCode(pointer) == StrCode.NESTED) {
      // iterate over all of the ITEM codes
      pointer = next(pointer);
      while (getCode(pointer) == StrCode.ITEM) {
        // should be a key code
        int keyPointer = next(pointer);
        if (getCode(keyPointer) == StrCode.KEY) {
          if (getText(keyPointer).equals(key)) {
            int valuePointer = next(keyPointer); // should be a VALUE code
            valuePointer = next(valuePointer); // should be a TEXT code
            return getText(valuePointer);
          }
        }
        pointer = nextSibling(pointer);
      }
    }
    throw new NoSuchElementException();
  }



}
