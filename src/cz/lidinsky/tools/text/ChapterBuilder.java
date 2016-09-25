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
public class ChapterBuilder extends AbstractBuilder {

  /** Title of the article, may be null or empty, in such a case, article
      doesn't have a title. */
  private final String title;

  ChapterBuilder(AbstractBuilder parent, String title) {
    super(parent);
    this.title = title;
  }

  public ParagraphBuilder appendParagraph() {
    ParagraphBuilder paragraph = new ParagraphBuilder(this);
    addChild(paragraph);
    return paragraph;
  }

  public ListBuilder appendList(boolean ordered) {
    ListBuilder builder = new ListBuilder(this, ordered);
    addChild(builder);
    return builder;
  }

  public ChapterBuilder appendChapter() {
    ChapterBuilder builder = new ChapterBuilder(this, title);
    addChild(builder);
    return builder;
  }

  @Override
  void serialize(StrBuffer sb) {
    sb.append(StrCode.CHAPTER);
    sb.append(StrCode.TITLE, title);
    getChildren().stream().forEach(child -> child.serialize(sb));
    sb.append(StrCode.END);
  }

  @Override
  protected StrCode getCode() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
