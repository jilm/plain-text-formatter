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

  /**
   * Fills word from the word iterator into the given buffer up to the length.
   * The word iterator will return the first word that was not moved into the
   * buffer. Number of character that were filled-in is returned.
   */
  public static int fillLine(
      WordIterator words, char[] buffer, int offset, int length) {
    offset = validateArrayOffset(buffer.length, offset);
    length = validateArrayLength(buffer.length, offset, length);
    int counter = words.getChars(buffer, offset, length);
    words.next();
    while (words.getLength() > 0 && words.getLength() + counter + 1 <= length) {
      counter += 1;
      counter += words.getChars(buffer, offset + counter, length - counter);
      words.next();
    }
    return counter;
  }


  static int validateArrayOffset(int bufferLength, int offset) {
    return Math.min(offset < 0 ? 0 : offset, bufferLength);
  }

  static int validateArrayLength(int bufferLength, int offset, int length) {
    return Math.min(length < 0 ? 0 : length, bufferLength - offset);
  }

  public static void main(String[] args) throws Exception {
    java.io.BufferedReader reader
      = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    String buffer
      = reader.lines().collect(java.util.stream.Collectors.joining(" "));
    char[] line = new char[21];
    WordIterator words = new WordIterator(buffer, 0, buffer.length());
    while (!words.isAtTheEnd()) {
      java.util.Arrays.fill(line, ' ');
      fillLine(words, line, 4, 16);
      System.out.println(new String(line));
    }
  }

}
