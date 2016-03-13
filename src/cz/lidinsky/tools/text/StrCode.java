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
enum StrCode {

  HEAD0 (0),
  HEAD1 (1),
  HEAD2 (2),
  HEAD3 (3),
  HEAD4 (4),
  LIST_ORDERED (5),
  LIST_UNORDERED (5),
  ITEM (6),
  PARAGRAPH (7),
  APPENDIX (100),
  EMPHASIZE (100),
  END (100),
  EXTENDED (100),
  HORIZONTAL_RULE (100),
  LITERAL (100),
  NESTED (100),
  NEW_LINE (100),
  STRONG (100),
  TABLE (100),
  TEXT (100),
  LIST_END (100),
  MARK (100),
  REFERENCE (100)
    ;

  int level;

  static final int HEAD_LEVELS = 5;

  StrCode(int level) {
    this.level = level;
  }

  char getCode() {
    return (char)((int)'0' + ordinal());
  }

  static StrCode getStrCode(char code) {
    return StrCode.class.getEnumConstants()[(int)(code - '0')];
  }

  int getLevel() {
    return this.level;
  }

}
