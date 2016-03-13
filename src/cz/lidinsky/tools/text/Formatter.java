/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lidinsky.tools.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main formatter class.
 */
public class Formatter {

  public Formatter() { }

  public String format(StrIterator iterator) {
    return new PageFormatter(iterator).format();
  }


}
