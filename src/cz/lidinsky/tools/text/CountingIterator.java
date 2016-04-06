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

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * An iterator which assigns a number to each block code.
 *
 * @author jilm
 */
class CountingIterator extends AbstractIterator {

  private int actualHeadLevel;
  private final int[] headCounter;

  private final AbstractIterator iterator;

  private final Deque<Integer> listCounter;

  /**
   *
   * @param iterator
   */
  CountingIterator(AbstractIterator iterator) {
    this.headCounter = new int[StrCode.HEAD_LEVELS];
    this.actualHeadLevel = 0;
    this.iterator = iterator;
    this.listCounter = new ArrayDeque<>();
  }

  @Override
  boolean isAtTheEnd() {
    return iterator.isAtTheEnd();
  }

  @Override
  boolean next() {
    if (iterator.next()) {
      StrCode code = getCode();
      switch (code) {
        case HEAD0:
          actualHeadLevel = 0;
          headCounter[actualHeadLevel]++;
          Arrays.fill(headCounter, 1, StrCode.HEAD_LEVELS - 1, 0);
          listCounter.clear();
          break;
        case HEAD1:
          actualHeadLevel = 1;
          headCounter[actualHeadLevel]++;
          Arrays.fill(headCounter, 2, StrCode.HEAD_LEVELS - 1, 0);
          listCounter.clear();
          break;
        case HEAD2:
          actualHeadLevel = 2;
          headCounter[actualHeadLevel]++;
          Arrays.fill(headCounter, 3, StrCode.HEAD_LEVELS - 1, 0);
          listCounter.clear();
          break;
        case HEAD3:
          actualHeadLevel = 3;
          headCounter[actualHeadLevel]++;
          headCounter[4] = 0;
          listCounter.clear();
          break;
        case HEAD4:
          actualHeadLevel = 4;
          headCounter[actualHeadLevel]++;
          listCounter.clear();
          break;
        case LIST_ORDERED:
        case LIST_UNORDERED:
          listCounter.push(0);
          break;
        case LIST_END:
          if (!listCounter.isEmpty()) listCounter.pop();
          break;
        case ITEM:
          if (!listCounter.isEmpty()) {
            listCounter.push(listCounter.pop() + 1);
          }
          break;
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  String getText() {
    return iterator.getText();
  }

  @Override
  StrCode getCode() {
    return iterator.getCode();
  }

  @Override
  WordIterator getWordIterator() {
    return iterator.getWordIterator();
  }

  @Override
  void push() {
    iterator.push();
  }

  @Override
  void pop() {
    iterator.pop();
  }

  @Override
  int getText(char[] buffer, int offset, int length) {
    return iterator.getText(buffer, offset, length);
  }

  @Override
  boolean isEmpty() {
    return iterator.isEmpty();
  }

  int getHeadLevel() {
    return actualHeadLevel;
  }

  int getHeadNumber() {
    return headCounter[actualHeadLevel];
  }

  String getFullHeadNumber() {
    String result = "";
    for (int i = 0; i <= actualHeadLevel; i++) {
      result += Integer.toString(headCounter[i] + 1) + ".";
    }
    return result;
  }

  int getListLevel() {
    return listCounter.size();
  }

  int getListItemCount() {
    return listCounter.getFirst();
  }

  @Override
  String getBuffer() {
    return iterator.getBuffer();
  }

}
