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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jilm
 */
public class MapBuilder {

  private final Map<String, StrBuffer2> data;

  public MapBuilder() {
    data = new HashMap<>();
  }

  public void put(String key, String value) {
    put(key, new StrBuffer2(StrCode.TEXT, value));
  }

  public void put(String key, StrBuffer2 value) {
    data.put(key, value);
  }

  public StrBuffer2 getBuffer() {
    StringBuilder sb = new StringBuilder();
    sb.append(StrCode.TABLE.getCode())                // begin table
            .append(StrCode.NESTED.getCode());        // begin nested
    for (String key : data.keySet()) {
      String value = data.get(key).getBuffer();
      sb.append(StrCode.ITEM.getCode())               // begin item
              .append(StrCode.KEY.getCode())
              .append(StrBuffer2.getLengthCode(key.length()))
              .append(key)
              .append(StrCode.VALUE.getCode())        // begin value
              .append(value)
              .append(StrCode.END.getCode())          // end value
              .append(StrCode.END.getCode());         // end item
    }
    sb.append(StrCode.END.getCode())                  // end nested
            .append(StrCode.END.getCode());           // end table
    return new StrBuffer2(sb.toString());
  }


}
