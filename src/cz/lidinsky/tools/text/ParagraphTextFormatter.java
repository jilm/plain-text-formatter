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
 * Format one paragraph of text.
 */
public class ParagraphTextFormatter extends AbstractTextFormatter {

  public final int ALLIGN_LEFT = 0;
  public final int ALLIGN_RIGHT = 1;
  public final int ALLIGN_CENTER = 2;

  /** True if the line should be inteded. */
  private boolean intend;

  /** The method of text alignment. */
  private final int alignment;

  ParagraphTextFormatter(AbstractTextFormatter parent, final boolean indentFirst, final int alignment) {
    super(parent);
    this.intend = indentFirst && alignment == ALLIGN_LEFT;
    this.alignment = alignment;
  }

  /**
   * Format one line of text.
   *
   * @param line
   *
   * @return
   */
  @Override
  protected boolean formatLine(final Line line) {
    if (isEmpty()) {
      return false;
    }
    line.skip(intend ? 4 : 0);
    intend = false;
    // allignment
    if (alignment == ALLIGN_RIGHT || alignment == ALLIGN_CENTER) {
      line.appendGlue();
    }
    // fill-in words
    while (!isEmpty() && !getChild().formatLine(line)) { }
    // allignment
    if (alignment == ALLIGN_LEFT || alignment == ALLIGN_CENTER) {
      line.appendGlue();
    }
    return true;
  }

}