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
 * chapter. This object is the root builder object.
 */
public class ArticleBuilder extends AbstractBuilder {

  /** Title of the article, may be null or empty, in such a case, article
      doesn't have a title. */
  private final String title;

  /**
   * Initialize the object.
   *
   * @param title
   *            the title of the article, may be null or blank. In such a case
   *            the article doesn't hava a title.
   */
  public ArticleBuilder(String title) {
    super(null);
    this.title = title;
  }

  /**
   * Appends and returns a paragraph.
   *
   * @return appends and returns an empty paragraph object
   */
  public ParagraphBuilder appendParagraph() {
    return new ParagraphBuilder(this);
  }

  /**
   * Appends and returns a list object.
   *
   * @param ordered
   *            true for ordered (numbered) list, false otherwise.
   *
   * @return appends and returnes a list object
   */
  public ListBuilder appendList(boolean ordered) {
    return new ListBuilder(this, ordered);
  }

  /**
   * Appends and returns a nested chapter object.
   *
   * @param title
   *            title of the chapter, may be null or blank
   *
   * @return new empty nested chapter object
   */
  public ChapterBuilder appendChapter(String title) {
    return new ChapterBuilder(this, title);
  }

  /**
   * Appends and returns new empty table builder.
   *
   * @return new, empty table builder
   */
  public TableBuilder appendTable() {
    return new TableBuilder(this);
  }

  /**
   * Returns serialized form of the article representation.
   *
   * @return serialized form of the whole article object.
   */
  public String serialize() {
    StrBuffer sb = new StrBuffer();
    serialize(sb);
    return sb.toString();
  }

  @Override
  protected void serializePrior(final StrBuffer sb) {
    sb.append(StrCode.ARTICLE);
    // TODO: append title
  }

  @Override
  protected void serializePost(final StrBuffer sb) {
    sb.append(StrCode.END);
  }

}
