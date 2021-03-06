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
 * An ordered or unordered list builder object. This may not be a root object.
 */
public class ListBuilder extends AbstractBuilder {

  /** Title of the article, may be null or empty, in such a case, article
      doesn't have a title. */
  private final String title;

  /** true if this list is ordered (numbered) or not. */
  private final boolean ordered;

  /**
   * Initialize new list builder object.
   *
   * @param parent
   * @param ordered
   */
  ListBuilder(AbstractBuilder parent, boolean ordered) {
    super(parent);
    this.ordered = ordered;
    this.title = "";
  }

  public ItemBuilder appendItem() {
    return new ItemBuilder(this);
  }

  @Override
  protected final void serializePrior(final StrBuffer sb) {
    sb.append(ordered ? StrCode.LIST_ORDERED : StrCode.LIST_UNORDERED);
  }

  @Override
  protected final void serializePost(final StrBuffer sb) {
    sb.append(StrCode.END);
  }

}
