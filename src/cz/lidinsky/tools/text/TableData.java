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

import java.util.Arrays;

/**
 * Contains table data. Each column has a unique identifier.
 */
class TableData {

  /**
   * A buffer which contains the table data. First elements, it means from
   * 0 to the number of columns contains names of the columns.
   */
  private String[] buffer;

  /**
   * Number of columns.
   */
  private int cols;

  /**
   * Number of rows.
   */
  private int rows;

  private static final String EMPTY = "";

  public TableData() {
    buffer = new String[25];
    Arrays.fill(buffer, EMPTY);
    rows = 0;
    cols = 0;
  }

  /**
   * Adds column with given key at the end of the table.
   */
  public void addColumn(String key) {
    // if necessary, enlarge the buffer
    if (buffer.length < (cols + 1) * (rows + 1)) {
      String[] temp = new String[(cols + 1) * (rows + 2)];
      Arrays.fill(temp, EMPTY);
      System.arraycopy(buffer, 0, temp, 0, buffer.length);
      buffer = temp;
    }
    // shift all of the rows
    for (int row = rows - 1; row >= 0; row--) {
      int before = row * cols + cols;
      int after = row * (cols + 1) + cols + 1;
      System.arraycopy(buffer, before, buffer, after, cols);
      // clean elements of the new column
      buffer[after + 1] = EMPTY;
    }
    // append column key
    buffer[cols] = key;
    // increase number of columns
    cols++;
  }

  public void addRow() {
    // if necessary, enlarge the buffer
    if (buffer.length < cols * (rows + 2)) {
      String[] temp = new String[cols * (rows + 3)];
      Arrays.fill(temp, EMPTY);
      System.arraycopy(buffer, 0, temp, 0, buffer.length);
      buffer = temp;
    }
    rows++;
  }

  public void putValue(String key, String value) {
    int column = getColumnIndex(key);
    if (column < 0) {
      addColumn(key);
      column = cols - 1;
    }
    if (rows == 0) {
      addRow();
    }
    buffer[rows * cols + column] = value == null ? EMPTY : value;
  }

  public int getColumnIndex(String key) {
    for (int i = 0; i < cols; i++) {
      if (key.equals(buffer[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns true iff this object doewn't contain any data.
   *
   * @return ture iff this object doesn't contain any data
   */
  public boolean isEmpty() {
    return rows == 0;
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public void getHeads(String[] heads) {
    System.arraycopy(buffer, 0, heads, 0, Math.min(heads.length, cols));
  }

  public void getRow(int index, String[] row) {
    System.arraycopy(buffer, (index + 1) * cols, row, 0, Math.min(row.length, cols));
  }

  public void getColumnWidths(int[] widths) {
    Arrays.fill(widths, 0);
    for (int r = 0; r <= rows; r++) {
      for (int c = 0; c < Math.min(cols, widths.length); c++) {
        widths[c] = Math.max(widths[c], buffer[r * cols + c].length());
      }
    }
  }
}
