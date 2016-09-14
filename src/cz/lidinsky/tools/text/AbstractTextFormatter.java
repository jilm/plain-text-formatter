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

/**
 * Common prodecessor for all of the text formatters.
 */
public abstract class AbstractTextFormatter {

  /**
   * Format one line of text into the given buffer. This method is called
   * repeatedly, until the whole text is out.
   *
   * @param buffer
   * @param offset
   * @param length
   *
   * @return number of characters written into the buffer
   */
  abstract protected int formatLine(char[] buffer, int offset, int length);

  /**
   *
   * @return
   */
  abstract protected boolean isEmpty();

  /**
   * Returns number of characters which will be written by the next formatLine
   * calls.
   *
   * @param length
   * @return
   */
  abstract protected int getLength(int length);

  /**
   *
   * @param length
   * @return
   */
  abstract protected int getWords(int length);

}
