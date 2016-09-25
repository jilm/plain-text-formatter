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
 * A table builder object.
 */
public class TableBuilder extends AbstractBuilder {

  /** Title of the article, may be null or empty, in such a case, article
      doesn't have a title. */

  private final StrBuffer sb;


  TableBuilder(AbstractBuilder parent) {
    super(parent);
    this.sb = new StrBuffer();
  }

  public TableBuilder appendValue(String key, String value) {
    this.sb.append(StrCode.KEY, key);
    this.sb.append(StrCode.VALUE, value);
    this.sb.append(StrCode.END);
    return this;
  }

  public TableBuilder newRow() {
    this.sb.append(StrCode.ITEM);
    return this;
  }

  @Override
  void serialize(StrBuffer sb) {
    sb.append(StrCode.TABLE);
    sb.append(this.sb);
    sb.append(StrCode.END);
  }

}
