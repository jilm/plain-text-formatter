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

import cz.lidinsky.tree.Node;
import java.util.List;

/**
 * Common prodecessor for all of the text formatters. Fotmatters are organized
 * into the tree structure.
 */
public abstract class AbstractTextFormatter {

  /** The node of the tree. */
  private Node<AbstractTextFormatter> node;

  AbstractTextFormatter(AbstractTextFormatter parent) {
    if (parent != null) {
      node = new Node<>(parent.node);
    } else {
      node = new Node<>();
    }
    node.setDecorated(this);
  }

  int getLevel() {
    return getParent().getLevel() + 1;
  }

  /**
   * Returns parent formatter object or null if this object is the root object.
   *
   * @return
   */
  public AbstractTextFormatter getParent() {
    if (node.isRoot()) {
      return null;
    } else {
      return node.getParent().getDecorated();
    }
  }

  /**
   * Returns true if there is not any text content inside this block.
   *
   * @return
   */
  protected boolean isEmpty() {
    if (node.isLeaf()) {
      return true;
    } else {
      if (node.getDecoratedChildren().get(0).isEmpty()) {
        while (!node.isLeaf() && node.getDecoratedChildren().get(0).isEmpty()) {
          node.remove(0);
        }
      }
    }
    return node.isLeaf();
  }

  /**
   * Returns first non empty child formatter or null.
   *
   * @return
   */
  protected AbstractTextFormatter getChild() {
    if (isEmpty()) return null;
    return node.getDecoratedChildren().get(0);
  }

  protected List<AbstractTextFormatter> getChildren() {
    return node.getDecoratedChildren();
  }

  /**
   * Fill-in one line of the final document.
   *
   * @param line
   *            a char buffer to fill-in result
   *
   * @return true, if the line is finished, false if there was nothing to
   *            fill-in
   */
  abstract boolean formatLine(Line line);

}