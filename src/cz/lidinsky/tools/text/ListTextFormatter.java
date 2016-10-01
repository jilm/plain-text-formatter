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
 *
 * @author jilm
 */
public class ListTextFormatter extends BlockTextFormatter {

  protected static final char[] BULLETS = {'o', '*', '-', '#', 'O', 'x', '>', '+', '~'};

  private final boolean ordered;

  ListTextFormatter(AbstractTextFormatter parent, boolean ordered) {
    super(parent);
    this.ordered = ordered;
  }


  protected char getBullet() {
    return '*'; //BULLETS[depth % BULLETS.length];
  }

  protected int getNumber(char[] buffer, int offset, int length) {
    // calculate number of digits
    int items = getChildren().size() + getChildNumber();
    int digits = Math.round((float)Math.log10(items) - 0.5f) + 1;
    // get the item number
    String number = Integer.toString(getChildNumber() + 1);
    number.getChars(0, number.length(), buffer, offset + digits - number.length());
    return digits;
  }

  @Override
  protected void getPrefix(Line line) {
    if (ordered) {
      // TODO:
    } else {
      if (getChildLineNumber() == 0) {
        line.append('*'); // TODO:
        line.skip(1);
      } else {
        line.skip(2);
      }
    }
  }


}
