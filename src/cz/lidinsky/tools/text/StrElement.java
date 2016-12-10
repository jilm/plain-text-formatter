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
public abstract class StrElement {

  /**
   *
   * @param code
   * @param text
   * @return
   */
  public static StrElement create(final StrCode code, final String text) {
    return new InlineElement(code, text);
  }

  /**
   *
   * @param code
   * @return
   */
  public static StrElement create(final StrCode code) {
    return new BlockElement(code);
  }

  /**
   *
   * @return
   */
  public abstract String getText();

  /**
   *
   * @return
   */
  public abstract StrCode getCode();

  private static class BlockElement extends StrElement {

    private final StrCode code;

    public BlockElement(final StrCode code) {
      this.code = code;
    }

    @Override
    public String getText() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StrCode getCode() {
      return code;
    }

  }

  private static class InlineElement extends BlockElement {

    private final String text;

    public InlineElement(final StrCode code, final String text) {
      super(code);
      this.text = text;
    }

    @Override
    public String getText() {
      return text;
    }

  }

}
