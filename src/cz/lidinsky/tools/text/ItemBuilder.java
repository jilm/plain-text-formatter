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
 * Small, simple text with a title, which may be devided into more than one
 * chapter.
 */
public class ItemBuilder extends AbstractBuilder {

  ItemBuilder(AbstractBuilder parent) {
    super(parent);
  }

  public ParagraphBuilder appendParagraph() {
    ParagraphBuilder builder = new ParagraphBuilder(this);
    addChild(builder);
    return builder;
  }

  public ListBuilder appendList(boolean ordered) {
    ListBuilder builder = new ListBuilder(this, ordered);
    addChild(builder);
    return builder;
  }

  @Override
  void serialize(StrBuffer sb) {
    getChildren().stream().forEach(child -> child.serialize(sb));
  }

  @Override
  protected StrCode getCode() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
