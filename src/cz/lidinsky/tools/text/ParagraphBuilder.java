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

import java.util.ArrayList;
import java.util.List;

/**
 * Helps to create a paragraph.
 */
public class ParagraphBuilder extends AbstractBuilder {

  private final List<String> children;

  private final List<StrCode> codes;

  ParagraphBuilder(AbstractBuilder parent) {
    super(parent);
    children = new ArrayList<>();
    codes = new ArrayList<>();
  }

  /**
   * Adds plain text.
   *
   * @param text
   *            text to add
   *
   * @return this object
   */
  public ParagraphBuilder add(String text) {
    if (!StrUtils.isBlank(text)) {
      children.add(text);
      codes.add(StrCode.TEXT);
    }
    return this;
  }

  @Override
  protected void serializePrior(final StrBuffer sb) {
    sb.append(StrCode.PARAGRAPH);
  }

  @Override
  protected void serializePost(final StrBuffer sb) {
    sb.append(StrCode.END);
  }

}
