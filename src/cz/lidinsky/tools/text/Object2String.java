/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      // given object is null
      return buffer.append("<null>")
        .toString();

    } else if (isPrimitive(object)) {
      // given object is a primitive object
      primitive2StrBuffer(object);
      return buffer.toString();

    } else if (object instanceof Collection) {
      // a collection was given
      discovered.clear();
      open.clear();
      Collection collection = (Collection)object;
      if (collection.isEmpty()) {
        return buffer.append("<An empty collection.>")
                .toString();
      } else {
        buffer.appendHead("A collection content follows")
                .startOrderedList();
        for (Object element : collection) {
          buffer.startItem();
          appendObject(element);
          discovered.add(element);
        }
        buffer.closeList();
        dfs();
        return buffer.toString();
      }

    } else {
      // just object
      discovered.clear();
      open.clear();
      buffer.appendHead(0, "An Object Summary")
              .startUnorderedList()
              .startItem("class")
              .append(object.getClass().getName())
              .startItem("hash")
              .append(Integer.toHexString(object.hashCode()));
      dfs();
      discovered.add(object);
      appendFields(object);
      buffer.closeList();
      return buffer.toString();
    }
  }

  private final List<Object> discovered;
  private final Map<Object, Integer> references;
  private final Deque<Object> open;
  private final StrBuffer buffer;

  protected void dfs() {
      //appendObjectPreface(object);
      if (!open.isEmpty()) {
        buffer.appendHead(0, "Referenced objects");
        buffer.startOrderedList();
        while (!open.isEmpty()) {
          Object toDiscover = open.pop();
          if (!isDiscovered(toDiscover)) {
            discovered.add(toDiscover);
            int mark = references.get(toDiscover);
            buffer.startItem(mark)
                    .startUnorderedList()
                    .startItem("class")
                    .append(toDiscover.getClass().getName())
                    .startItem("hash")
                    .append(Integer.toHexString(toDiscover.hashCode()));
            appendFields(toDiscover);
            buffer.closeList();
          }
        }
        buffer.closeList();
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
      || object instanceof Number
      || object instanceof Boolean;
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
    // go through all of its fields and add them into the string buffer
    Class _class = object.getClass();
    while (_class != null) {
      Field[] fields = _class.getDeclaredFields();
      for (Field field : fields) {
        buffer.startItem(field.getName());
        try {
          field.setAccessible(true);
          Object value = field.get(object);
          appendFieldValue(value);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          buffer.append(e.toString());
        }
      }
      _class = _class.getSuperclass();
    }
  }

  /**
   * Appends contenten of the given object into the buffer. If it is a some
   * primitive object, where the content is short than the content is appended.
   * If it is a complex object, the reference is appended instead and the
   * value is stored into the open collection to be appended later.
   *
   * @param value
   */
  private void appendFieldValue(Object value) {

    if (value == null) {
      buffer.append("<null>");

    } else if (isPrimitive(value)) {
      primitive2StrBuffer(value);

    } else if (isDiscovered(value)) {
      buffer.reference(references.get(value));

    } else if (value instanceof List) {
      List list = (List)value;
      if (list.isEmpty()) {
        buffer.append("<an empty list>");
      } else if (list.size() == 1) {
        Object element = list.get(0);
        buffer.append("[");
        appendFieldValue(element);
        buffer.append("]");
      } else {
        buffer.append(String.format("a list of size %d", list.size()));
      }

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
  }

  /**
   * Apeends content of the given object into the buffer.
   *
   * @param object
   */
  private void appendObject(Object object) {

    if (object == null) {
      // if the given object is null, just say null
      buffer.append("<null>");

    } else if (isPrimitive(object)) {
      // if it is primitive value, just say the value
      primitive2StrBuffer(object);

    } else if (isCollection(object)) {
      // if it is a collection of objects, list all of its items
      appendCollection(object);

    } else if (isDiscovered(object)) {
      // if it is an object whose content has already been listed, say just
      // reference to that object
      buffer.reference(references.get(object));

    } else {
      // if it is an object
      buffer.startUnorderedList()
              .startItem("class")
              .append(object.getClass().getName())
              .startItem("hash")
              .append(Integer.toHexString(object.hashCode()));
      //appendObjectPreface(object);
      discovered.add(object);
      appendFields(object);
      buffer.closeList();
    }
  }

  private boolean isCollection(Object object) {
    return object instanceof Collection;
  }

  private void appendCollection(Object object) {
    if (object instanceof Collection) {
      Collection collection = (Collection)object;
      if (collection.isEmpty()) {
        buffer.append("<An empty collection.>");
      } else if (collection.size() == 1) {
        buffer.append("[");
        appendFieldValue(collection.iterator().next());
        buffer.append("]");
      } else {
        buffer.append("A collection of objects; size: %i")
                .startOrderedList();
        for (Object element : collection) {
          if (references.containsKey(element)) {
            buffer.startItem();
          } else {
            int mark = buffer.reserveMark();
            references.put(element, mark);
            buffer.startItem(mark);
          }
          appendFieldValue(element);
        }
        buffer.closeList();
      }
    }
  }

}
