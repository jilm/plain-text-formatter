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
 */
public class Object2String {


  private final List<Object> discovered;
  private final Map<Object, Integer> references;
  private final Deque<Object> open;
  //private final StrBuffer buffer;
  private final ArticleBuilder builder;

  public Object2String() {
    discovered = new ArrayList<>();
    open = new ArrayDeque<>();
    //buffer = new StrBuffer();
    references = new HashMap<>();
    builder = new ArticleBuilder("Object statement");
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
      builder.appendParagraph().add("<null>");
      return builder.serialize();

    } else if (isPrimitive(object)) {
      // given object is a primitive object
      builder.appendParagraph().add(getPrimitiveRepresentation(object));
      return builder.serialize();

    } else {
      discovered.clear();
      open.clear();
      open.add(object);
      dfs();
      return builder.serialize();
    }
  }

  /**
   * Depth-first search algorithm to print all of the objects.
   */
  protected void dfs() {
    while (!open.isEmpty()) {
      Object toDiscover = open.pop();
      if (!isDiscovered(toDiscover)) {
        discovered.add(toDiscover);
        appendObject(toDiscover);
      }
    }
  }

  protected boolean isDiscovered(Object object) {
    return discovered.stream()
            .filter(o -> o == object)
            .findAny()
            .isPresent();
  }


  /**
   * Apeends content of the given object into the builder. It is expected that
   * the given object is neater null nor primitive. New chapter is created for
   * each object.
   *
   * @param object
   */
  private void appendObject(Object object) {

    ChapterBuilder chapter = builder.appendChapter(getBasicStatement(object));
    // go through all of its fields and add them into the string buffer
    TableBuilder table = chapter.appendTable();
    Class _class = object.getClass();
    while (_class != null) {
      Field[] fields = _class.getDeclaredFields();
      for (Field field : fields) {
        try {
          field.setAccessible(true);
          Object value = field.get(object);
          table.appendValue(field.getName(), getShortStatement(value));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          // TODO:
        }
      }
      _class = _class.getSuperclass();
    }
  }

  private boolean isCollection(Object object) {
    return object instanceof Collection;
  }

  public static String getBasicStatement(Object object) {
    return object.getClass().getName()
            + '@'
            + Integer.toHexString(object.hashCode());
  }

  public static boolean isPrimitive(Object object) {
    return object instanceof String
      || object instanceof Number
      || object instanceof Boolean;
  }

  public static String getPrimitiveRepresentation(Object object) {
    return object.toString();
  }

  public String getShortStatement(Object object) {
    if (object == null) {
      return "<null>";
    } else if (isPrimitive(object)) {
      return object.toString();
    } else if (object instanceof Collection
            && ((Collection)object).isEmpty()) {
      return "<an empty collection>";
    } else if (object instanceof Collection
            && ((Collection)object).size() == 1) {
      return "Collection which contains one element: "; // TODO:
    } else if (object instanceof Collection) {
      open.add(object);
      return "A collection";
    } else {
      open.add(object);
      return getBasicStatement(object);
    }
  }

}
