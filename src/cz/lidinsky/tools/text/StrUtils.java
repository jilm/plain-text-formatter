/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author jilm
 */
public class StrUtils {

  public static String center(String text, int width) {
    if (isBlank(text)) {
      return "";
    } else {
      text = text.trim();
      if (text.length() > width) {
        return text;
      } else if (text.length() == width) {
        return text;
      } else {
        char[] result = new char[width];
        Arrays.fill(result, ' ');
        int offset = (width - text.length()) / 2;
        text.getChars(0, text.length(), result, offset);
        return new String(result);
      }
    }
  }

  public static boolean isEmpty(String text) {
    return text == null || text.isEmpty();
  }

  public static boolean isBlank(String text) {
    return text == null || text.isEmpty() || text.trim().isEmpty();
  }

  /**
   * Return number of whitespace character from the offset to the first non
   * whitespace character.
   *
   * @param text
   *            the scanned text
   *
   * @param offset
   *            first character to inspect
   *
   * @return number of whitespaces or zero
   */
  public static int leftTrim(char[] text, int offset) {
    int counter = 0;
    if (text != null && offset < text.length) {
      while (Character.isWhitespace(text[offset + counter])) {
        counter++;
      }
    }
    return counter;
  }

  public static String getNextWord(char[] text, int offset) {
    if (text != null && offset < text.length) {
      boolean word = false;
      for (int i = offset; i < text.length; i++) {
        if (Character.isWhitespace(text[i])) {
          if (word) {
            return new String(text, offset, i - offset);
          }
        } else {
          word = true;
        }
      }
      if (word) {
        return new String(text, offset, text.length - offset);
      }
    }
    return "";
  }

  public static List<String> splitWords(String text) {
    List<String> result = new ArrayList<>();
    char[] chars = text.toCharArray();
    int begin = 0;
    boolean word = false;
    for (int i = 0; i < chars.length; i++) {
      if (Character.isWhitespace(chars[i])) {
        if (word) {
          result.add(new String(chars, begin, i - begin));
          word = false;
        }
      } else {
        if (!word) {
          begin = i;
          word = true;
        }
      }
    }
    if (word) {
      result.add(new String(chars, begin, chars.length - begin));
    }
    return result;
  }

  public static void splitWords(String text, Deque deque) {
    if (text != null && text.length() > 0) {
      char[] chars = text.toCharArray();
      int index = 0;
      while (index < chars.length) {
        index += leftTrim(chars, index);
        String word = getNextWord(chars, index);
        if (word.length() > 0) {
          deque.addLast(word);
          index += word.length();
        } else {
          return;
        }
      }
    }
  }

  /**
   * Copy char from the source string into the destination char array.
   *
   * @param source
   *            characters source
   *
   * @param destination
   *            an array to place characters from the source
   *
   * @param offset
   *            the index into the destination array where the first character
   *            will be placed
   *
   * @return number of characters placed into the destination buffer
   */
  public static int getChars(String source, char[] destination, int offset) {
    if (source != null && source.length() > 0) {
      int size = Math.min(source.length(), destination.length - offset);
      source.getChars(0, size, destination, offset);
      return size;
    } else {
      return 0;
    }
  }

}
