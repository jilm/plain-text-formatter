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
 *  along with java tools library.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.tools.text;


/**
 */
public enum StrCode {

  HEAD0 (0, true), // deprecated
  HEAD1 (1, true), // deprecated
  HEAD2 (2, true), // deprecated
  HEAD3 (3, true), // deprecated
  HEAD4 (4, true), // deprecated
  LIST_ORDERED (5, true), // block
  LIST_UNORDERED (5, true), // block
  ITEM (6, true), // block, nested into list element
  PARAGRAPH (7, true), // block
  APPENDIX (100, true), // block
  EMPHASIZE (100, false), // in-line
  END (100, false), // block end
  EXTENDED (100, false), // in-line
  HORIZONTAL_RULE (100, false), // in-line
  LITERAL (100, false), // in-line
  NESTED (100, true), // block
  NEW_LINE (100, false), // in-line
  STRONG (100, false), // in-line
  TABLE (100, true), // block
  TEXT (100, false), // in-line
  LIST_END (100, false), // deprecated
  MARK (100, true), // ???
  REFERENCE (100, true), // ???
  ARTICLE(100, true), // block
  CHAPTER (100, true), // block
  TITLE(100, false), // in-line
  KEY (100, false), // in-line
  VALUE (100, true); // block

  int level;
  boolean block;

  static final int HEAD_LEVELS = 5;

  StrCode(int level, boolean block) {
    this.level = level;
    this.block = block;
  }

  /**
   *
   * @return
   */
  public char getCode() {
    return (char)((int)'0' + ordinal());
  }

  /**
   *
   * @param code
   * @return
   */
  public static StrCode getStrCode(char code) {
    return StrCode.class.getEnumConstants()[(int)(code - '0')];
  }

  int getLevel() {
    return this.level;
  }

  public boolean isBlock() {
    return block;
  }

}
