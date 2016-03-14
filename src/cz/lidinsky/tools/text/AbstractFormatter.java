/*
 *  Copyright 2016 Jiri Lidinsky
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

/**
 * Common predecessor of all of the particular formatters.
 */
abstract class AbstractFormatter {

  /**
   * Format one line of text into the given buffer.
   *
   * @param buffer
   *            an array for the output line of text
   *
   * @param offset
   *            index into the buffer of the first character to fill-in.
   *            Elements from zero to the index should stay untouched
   * 
   * @param length
   *            how may character should be filled-in
   *
   * @return false if it was not possible to fill the buffer and it is empty,
   *            true otherwise. It means, true is return if the buffer contains
   *            at least one character
   */
  abstract protected boolean formatLine(char[] buffer, int offset, int length);


}
