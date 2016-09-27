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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Common predecessor for all of the text formatters whose children are blocks
 * of text.
 */
class TableTextFormatter extends AbstractTextFormatter {

  /**
   *
   * @param depth
   * @param order
   */
  TableTextFormatter(AbstractTextFormatter parent) {
    super(parent);
    stage = 0;
  }

  private int stage;

  /**
   * This implementation iterates over the children and calls format line.
   *
   * @param buffer
   * @param offset
   * @param length
   *
   * @return number of characters
   */
  @Override
  boolean formatLine(Line line) {

    if (isEmpty()) return false;
    analyze(line.getLength());
    switch (stage) {
      case 0: // line above the head
      case 2:
      case 4:
        line.fill(line.getLength(), '-');
        stage++;
        return true;
      case 1: // head
        formatRow(line, columnNames, widths);
        stage++;
        return true;
      case 3: // body of the table
        formatRow(line, getRowValues(), widths);
        rows.remove(0);
        if (rows.isEmpty()) {
          stage++;
        }
        return true;
      default:
        return false;
    }
  }

  /**
   * Format one row of the table.
   */
  protected int formatRow(Line line,
      Collection<String> values, int[] widths) {

    int size = 0;
    int delimit = 0;
    int i = 0;
    for (String value : values) {
      size += delimit;
      line.appendWord(value, widths[i]);
      size += widths[i];
      delimit = 2;
      i++;
    }
    return size;
  }

  protected Collection<String> getRowValues() {
    List<String> values = new ArrayList(columns);
    for (int i = 0; i < columns; i++) {
      values.add(rows.get(0).get(columnNames.get(i)));
    }
    return values;
  }

  /**
   * Returns true if there is not any text content inside this block.
   *
   * @return
   */
  @Override
  protected boolean isEmpty() {
    return rows.isEmpty() && stage != 4;
  }

  List<Map<String, String>> rows = new ArrayList<>();

  void add(Map<String, String> row) {
    rows.add(row);
  }

  /** Number of columns. */
  private int columns;

  private List<String> columnNames;

  private int[] widths;

  private boolean analyzed = false;

  private void analyze(int lineWidth) {
    if (analyzed) return;
    columnNames = rows.stream()
      .flatMap(row -> row.keySet().stream())
      .distinct()
      .collect(Collectors.toList());
    columns = columnNames.size();
    widths = new int[columns];
    for (int i = 0; i < columns; i++) widths[i] = columnNames.get(i).length();
    for (Map<String, String> row : rows) {
      for (int i = 0; i < columns; i++) {
        widths[i] = Math.max(widths[i], row.get(columnNames.get(i)).length());
      }
    }
    analyzed = true;
  }

  void putValue(String key, String value) {
    if (rows.isEmpty()) {
      rows.add(new HashMap());
    }
    rows.get(rows.size() - 1).put(key, value);
  }

  void newRow() {
    rows.add(new HashMap());
  }

}
