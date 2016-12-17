package cz.lidinsky.tools.text;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 *  Copyright 2015, 2016 Jiri Lidinsky
 *
 *  This file is part of java tools library.
 *
 *  java tools is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  java tools library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with java tools library. If not, see <http://www.gnu.org/licenses/>.
 */


/**
 * This object is immutable.
 */
public class StrBuffer2 implements Iterable<StrBuffer2> {

  /** The buffer. */
  protected final String buffer;

  /** Character index into the buffer. */
  protected final int offset;

  /**
   * Create new buffer.
   *
   * @param buffer
   */
  public StrBuffer2(final String buffer) {
    this.buffer = buffer;
    this.offset = 0;
  }

  /**
   * Creates buffer which contains only given code.
   *
   * @param code
   */
  public StrBuffer2(final StrCode code) {
    if (code == StrCode.END) {
      throw new IllegalArgumentException();
    } else if (code.isBlock()) {
      this.buffer
        = new String(new char[] {code.getCode(), StrCode.END.getCode()});
      this.offset = 0;
    } else {
      this.buffer = new String(new char[] {code.getCode(), '0', '0'});
      this.offset = 0;
    }
  }

  /**
   * Creates buffer which contains given code and text.
   *
   * @param code
   * @param text
   */
  public StrBuffer2(final StrCode code, final String text) {
    int textLength = text == null ? 0 : text.length();
    if (code == StrCode.END) {
      throw new IllegalArgumentException();
    } else if (code.isBlock()) {
      throw new IllegalArgumentException();
    } else {
      //if (length > MAX_LENGTH) {
      //  append(code, text.substring(0, MAX_LENGTH));
      //  append(StrCode.EXTENDED, text.substring(MAX_LENGTH));
      //} else {
      this.buffer = new String(new char[] {code.getCode(),
        (char)('0' + (int)(textLength / 70)),
        (char)('0' + (int)(textLength % 70))})
              + text;
      this.offset = 0;
    }
  }

  public static String getLengthCode(int length) {
    return new String(new char[] {
        (char)('0' + (int)(length / 70)),
        (char)('0' + (int)(length % 70))});
  }

  protected StrBuffer2(final String buffer, final int offset) {
    this.buffer = buffer;
    this.offset = offset;
  }

  //--------------------------------------------------------- Public interface.

  public StrCode getCode() {
    return StrCode.getStrCode(buffer.charAt(offset));
  }

  public String getText() {
    if (getCode().isBlock()) {
      return "";
    } else {
      int textLength = getLength(offset);
      return buffer.substring(offset + 3, offset + 3 + textLength);
    }
  }

  /**
   * Returns root element.
   *
   * @return
   */
  public StrBuffer2 getRoot() {
    if (offset == 0) {
      return this;
    } else {
      return new StrBuffer2(buffer, 0);
    }
  }


  public StrBuffer2 getParent() {
    if (offset == 0) {
      return null;
    } else {
      int pointer = 0;
      int parent = 0;
      while (pointer < offset) {
        StrCode code = getCode(pointer);
        if (code == StrCode.END) {
          // do nething
        } else if (code.isBlock()) {
          parent = pointer;
        } else { }
        pointer = next(pointer);
      }
      return new StrBuffer2(buffer, parent);
    }
  }

  public boolean isLeaf() {
    // if the code is not the block code, it is a leaf
    if (!getCode().isBlock()) {
      return true;
    } else if (getCode(next(offset)) == StrCode.END) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Iterator<StrBuffer2> iterator() {
    return new StrBufferIterator();
  }

  public Collection<StrBuffer2> getChildren() {
    return new ChildCollection();
  }

  /**
   * Appends given buffer tree as a last child of this node. Returns given
   * node of the new created tree.
   *
   * @param buffer
   *            a buffer tree to be added into this tree
   *
   * @return a new merged tree, and pointer points to the added subtree
   */
  public StrBuffer2 appendChild(StrBuffer2 buffer) {
    StrCode code = getCode();
    if (code == StrCode.END) {
      throw new IllegalStateException();
    } else if (!code.isBlock()) {
      throw new IllegalStateException();
    } else {
      // find the appropriate end element
      int pointer = end(offset);
      return new StrBuffer2(this.buffer.substring(0, pointer)
        + buffer.getBuffer()
        + this.buffer.substring(pointer, this.buffer.length()), offset);
    }
  }

  public StrBuffer2 appendChild(StrCode code) {
    return appendChild(new StrBuffer2(code));
  }

  public StrBuffer2 appendChild(StrCode code, String text) {
    return appendChild(new StrBuffer2(code, text));
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof StrBuffer2) {
      StrBuffer2 other = (StrBuffer2)object;
      return buffer.equals(other.buffer) && offset == other.offset;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return buffer.hashCode() ^ offset;
  }

  public String getBuffer() {
    return buffer;
  }

  //---------------------------------------------------------- Private methods.

  /**
   * Returns index of the next code in the buffer.
   */
  public int next(int pointer) {
    // get code on the given index
    StrCode code = getCode(pointer);
    if (code == StrCode.END) {
      return pointer + 1;
    } if (code.isBlock()) {
      return pointer + 1;
    } else {
      int length = getLength(pointer);
      return pointer + 3 + length;
    }
  }

  /**
   * Finds index of the next sibling element. What if there is no sibling???
   *
   * @param index
   *
   * @return
   */
  protected int nextSibling(int index) {
    int level = 0;
    int pointer = index;
    do {
      StrCode code = getCode(pointer);
      if (code == StrCode.END) {
        level--;
      } else if (code.isBlock()) {
        level++;
      } else { }
      pointer = next(pointer);
    } while (level > 0);
    return pointer;
  }

  /**
   * Finds and returns index of appropriate end element of the code on the
   * given index.
   *
   * @param index
   *
   * @return
   *
   * @throws IllegalStatusException
   *            if code on the given index is not a block one
   */
  private int end(int index) {
    int pointer = index;
    int level = 0;
    StrCode code = getCode(index);
    if (code.isBlock()) {
      do {
        pointer = next(pointer);
        code = getCode(pointer);
        if (code == StrCode.END) {
          level--;
        } else if (code.isBlock()) {
          level++;
        } else { }
      } while (level > 0);
    }
    return pointer;
  }

  /**
   *
   */
  private int getLength(int index) {
    //validateIndex(index);
    StrCode code = getCode(index);
    if (code.isBlock()) {
      return 0;
    } else {
      return (int)(buffer.charAt(index + 1) - '0') * 70
        + (int)(buffer.charAt(index + 2) - '0');
    }
  }

  protected String getText(int index) {
    if (getCode(index).isBlock()) {
      return "";
    } else {
      int textLength = getLength(index);
      return buffer.substring(index + 3, index + 3 + textLength);
    }
  }

  protected StrCode getCode(int index) {
    return StrCode.getStrCode(buffer.charAt(index));
  }

  /**
   *
   */
  private class ChildCollection
          extends AbstractCollection<StrBuffer2>
          implements Collection<StrBuffer2> {


    @Override
    public int size() {
      int counter = 0;
      int pointer = next(offset);
      while (pointer < buffer.length() && getCode(pointer) != StrCode.END) {
        counter++;
        pointer = nextSibling(pointer);
      }
      return counter;
    }

    @Override
    public Iterator<StrBuffer2> iterator() {

      Iterator<StrBuffer2> iterator = new Iterator() {

        private int pointer = StrBuffer2.this.next(offset);

        @Override
        public StrBuffer2 next() {
          if (pointer < buffer.length() && getCode(pointer) != StrCode.END) {
            StrBuffer2 result = new StrBuffer2(buffer, pointer);
            pointer = nextSibling(pointer);
            return result;
          } else {
            throw new NoSuchElementException();
          }

        }

        @Override
        public boolean hasNext() {
          return pointer < buffer.length() && getCode(pointer) != StrCode.END;
        }

      };
      return iterator;
    }

  }

  private class StrBufferIterator implements Iterator<StrBuffer2> {

      private int pointer = offset;

      @Override
      public StrBuffer2 next() {
        if (pointer < buffer.length()) {
          StrBuffer2 result = new StrBuffer2(buffer, pointer);
          pointer = StrBuffer2.this.next(pointer);
          return result;
        } else {
          throw new NoSuchElementException();
        }
      }

      @Override
      public boolean hasNext() {
        return pointer < buffer.length();
      }
  }

 }