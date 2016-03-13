/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author jilm
 */
public class Object2String {

  public Object2String() {
    discovered = new ArrayList<>();
    open = new ArrayDeque<>();
    buffer = new StrBuffer();
    references = new HashMap<>();
  }

  /**
   * Returns the string representation of the given object.
   *
   * @param object
   * @return
   */
  public String toStrBuffer(Object object) {
    if (object == null) {
      return buffer.append("<null>")
        .toString();
    } else if (isPrimitive(object)) {
      primitive2StrBuffer(object);
      return buffer.toString();
    } else {
      discovered.clear();
      open.clear();
      dfs(object);
      return buffer.toString();
    }
  }

  private final List<Object> discovered;
  private final Map<Object, Integer> references;
  private final Deque<Object> open;
  private final StrBuffer buffer;

  protected void dfs(Object object) {
    if (object != null) {
      buffer.appendHead(0, "An Object Summary");
      appendObjectPreface(object);
      discovered.add(object);
      appendFields(object);
      if (!open.isEmpty()) {
        buffer.appendHead(0, "Referenced objects");
        buffer.startOrderedList();
        while (!open.isEmpty()) {
          Object toDiscover = open.pop();
          if (!isDiscovered(toDiscover)) {
            discovered.add(toDiscover);
            int mark = references.get(toDiscover);
            buffer.startItem(mark);
            appendObjectPreface(toDiscover);
            appendFields(toDiscover);
          }
        }
        buffer.closeList();
      }
    }
  }

  protected boolean isDiscovered(Object object) {
    return discovered.stream()
            .filter(o -> o == object)
            .findAny()
            .isPresent();
  }

  protected void appendObjectPreface(Object object) {
    buffer.append("This is summary of the object which is an instance of the class with name:")
      .append(object.getClass().getSimpleName())
      //.append("Package name:")
      //.append(object.getClass().getPackage().getName())
      .append("Hash code:")
      .append(Integer.toHexString(object.hashCode()));
  }

  private boolean isPrimitive(Object object) {
    return object instanceof String
      || object instanceof Number;
  }

  private void primitive2StrBuffer(Object object) {
    buffer.append(object.toString());
  }

  /**
   * Creates and returns overall info about the given object.
   *
   * @param object
   * @return
   */
  private void appendFields(Object object) {
    // create a new string buffer and add preface info about the object
    buffer.startUnorderedList();
    // go through all of its fields and add them into the string buffer
    Field[] fields = object.getClass().getDeclaredFields();
    for (Field field : fields) {
      buffer.startItem(field.getName());
      try {
        field.setAccessible(true);
        Object value = field.get(object);
        if (value == null) {
          buffer.append("<null>");
        } else if (isPrimitive(value)) {
          buffer.append(value.toString());
        } else {
          int mark;
          if (!references.containsKey(value)) {
            mark = buffer.reserveMark();
            references.put(value, mark);
          } else {
            mark = references.get(value);
          }
          buffer.reference(mark);
          open.push(value);
        }
      } catch (IllegalArgumentException | IllegalAccessException e) {
        buffer.append(e.toString());
      }
    }
    buffer.closeList();
  }

}
