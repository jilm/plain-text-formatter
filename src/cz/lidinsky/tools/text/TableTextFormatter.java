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
 * Format its content in the form of the table
 */
class TableTextFormatter extends AbstractTextFormatter {

  private final TableData data;

  private AbstractTextFormatter formatter;

  /**
   * @param parent
   *            parent object of this node
   */
  TableTextFormatter(AbstractTextFormatter parent) {
    super(parent);
    data = new TableData();
  }

  @Override
  boolean formatLine(Line line) {
    if (formatter == null) {
      switch (data.getRows()) {
        case 0:
          return false;
        case 1:
          this.formatter = new ItemFormatter();
          break;
        default:
          this.formatter = new TableFormatter();
          break;
      }
    }
    return formatter.formatLine(line);
  }

  /**
   * Format one row of the table.
   *
   * @param line
   *            the character buffer for the result
   *
   * @param values
   *            a value for each column
   *
   * @param widths
   *            a width of each column
   */
  protected int formatRow(Line line,
          String[] values, int[] widths) {

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

  @Override
  protected boolean isEmpty() {
    return formatter == null ? data.isEmpty() : formatter.isEmpty();
  }

  /**
   * Put a value into the actual row.
   *
   * @param key
   *            column name
   *
   * @param value
   *            a value for the given column
   */
  public void putValue(String key, String value) {
    data.putValue(key, value);
  }

  /**
   * Starts new table row. Empty rows are ignored.
   */
  public void newRow() {
    data.addRow();
  }

  /**
   *
   */
  private class TableFormatter extends AbstractTextFormatter {

    private int counter;
    private String[] buffer;
    private int[] widths;

    public TableFormatter() {
      super(null);
      this.counter = -3;
    }

    private void initialize() {
      buffer = new String[data.getCols()];
      widths = new int[data.getCols()];
      data.getColumnWidths(widths);
    }

    @Override
    boolean formatLine(Line line) {

      if (isEmpty()) {
        return false;
      } else {
        switch (counter) {
          case -3: // line above the head
            initialize();
            line.fill(line.getLength(), '-');
            counter++;
            return true;
          case -2: // head itself
            data.getHeads(buffer);
            formatRow(line, buffer, widths);
            counter++;
            return true;
          case -1: // line bellow the head
            line.fill(line.getLength(), '-');
            counter++;
            return true;
          default: // body of the table
            if (counter == data.getRows()) {
              // line bellow the table
              line.fill(line.getLength(), '-');
              counter++;
              return true;
            } else if (counter < data.getRows()) {
              data.getRow(counter, buffer);
              formatRow(line, buffer, widths);
              counter++;
              return true;
            } else {
              return false;
            }
        }
      }
    }

    @Override
    protected boolean isEmpty() {
      return this.counter >= data.getRows();
    }

  }

  /**
   *
   */
  private class ItemFormatter extends AbstractTextFormatter {

    private final String[] head;
    private final String[] values;
    private int counter;

    ItemFormatter() {
      super(null);
      head = new String[data.getCols()];
      data.getHeads(head);
      values = new String[data.getCols()];
      data.getRow(0, values);
      counter = 0;
    }

    @Override
    boolean formatLine(Line line) {
      if (isEmpty()) return false;
      line.appendWord(head[counter]);
      line.append(':');
      line.appendWord(values[counter]);
      counter++;
      return true;
    }

    @Override
      protected boolean isEmpty() {
        return counter >= head.length;
      }

  }

}
