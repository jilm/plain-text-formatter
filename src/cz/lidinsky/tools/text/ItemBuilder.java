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
 * It is inteded to be a list item content. This object may contain only
 * paragraphs, nested lists or tables. It may not contain nested chapter
 * objects.
 */
public class ItemBuilder extends AbstractBuilder {

  ItemBuilder(AbstractBuilder parent) {
    super(parent);
  }

  public ParagraphBuilder appendParagraph() {
    return new ParagraphBuilder(this);
  }

  public ListBuilder appendList(boolean ordered) {
    return new ListBuilder(this, ordered);
  }

}
