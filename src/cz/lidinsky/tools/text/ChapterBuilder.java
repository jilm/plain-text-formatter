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
 * Object to build a chapter. Chapter may contain anoter nested chapter objects.
 * This object is not intended to be a root object.
 */
public class ChapterBuilder extends AbstractBuilder {

  /** Title of the article, may be null or empty, in such a case, article
      doesn't have a title. */
  private final String title;

  /**
   * Initialize chapter object and connects it to the given parent object.
   *
   * @param parent
   *            parent builder object. It should not be a null value.
   *
   * @param title
   *            a title of the chapter, may be null er blank
   */
  ChapterBuilder(AbstractBuilder parent, String title) {
    super(parent);
    this.title = title;
  }

  public ParagraphBuilder appendParagraph() {
    return new ParagraphBuilder(this);
  }

  public ListBuilder appendList(boolean ordered) {
    return new ListBuilder(this, ordered);
  }

  public ChapterBuilder appendChapter() {
    return new ChapterBuilder(this, title);
  }

  @Override
  protected void serializePrior(final StrBuffer sb) {
    sb.append(StrCode.CHAPTER);
    sb.append(StrCode.TITLE, title);
  }

  @Override
  protected void serializePost(final StrBuffer sb) {
    sb.append(StrCode.END);
  }

}
