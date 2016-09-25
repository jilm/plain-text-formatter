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
 * Common predecessor for all of the builder classes. Buildres are organized
 * into the tree structure.
 */
public abstract class AbstractBuilder {

  /** Node of the builders hierarchy. It may not be a null value. */
  private final Node<AbstractBuilder> node;

  /**
   * Creates a node object and connects it to the given parent object.
   *
   * @param parent
   *            parent object for this buildre node. Null value for the root
   *            node.
   */
  AbstractBuilder(final AbstractBuilder parent) {
    if (parent == null) {
      node = new Node<>(null);
    } else {
      node = new Node<>(parent.node);
    }
    node.setDecorated(this);
  }

  /**
   * Appends serialized form of this object and its children into the given
   * str buffer. This default implementation calls serializePrior methid,
   * than it calls serialize method for all of the child classes and after
   * that the serizePost method is called. If diferent behaviour is needed,
   * this method is to be overriden.
   *
   * @param sb
   *            a buffer to append the serialized form of this object
   *
   * @throws NullPointerException
   *            if given paramter contains null value
   */
  void serialize(final StrBuffer sb) {
    serializePrior(sb);
    for (AbstractBuilder builder : getChildren()) {
      builder.serialize(sb);
    }
    serializePost(sb);
  }

  /**
   * This method is called by the serialize method prior the children are
   * processesd. This default implemntation does nothing.
   *
   * @param sb
   */
  protected void serializePrior(final StrBuffer sb) {}

  protected void serializePost(final StrBuffer sb) {}

  /**
   * Returns list of all of the children objects.
   *
   * @return
   */
  List<AbstractBuilder> getChildren() {
    return node.getDecoratedChildren();
  }

  /** TODO: ??? */
  void addChild(AbstractBuilder child) {
    node.addChild(child.node);
  }
}
