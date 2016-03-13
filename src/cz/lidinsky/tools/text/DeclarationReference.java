/*
 *  Copyright 2013, 2014, 2016 Jiri Lidinsky
 *
 *  This file is part of control4j.
 *
 *  control4j is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, version 3.
 *
 *  control4j is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with control4j.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.lidinsky.tools.text;

import static cz.lidinsky.tools.text.StrUtils.isBlank;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *  Serves as a reference to the point, where referenced object was declared.
 *  To some line in the configuration file for example. It should return
 *  information in the human readable form. This information is used in a log
 *  message or in the error report.
 *
 *  <p>Reference information may and should be hierarchical.
 *  The example of output:<br>
 *  This is the module object, class: PMAnd,<br>
 *  on line: 25, column: 6,<br>
 *  in the file: heating.xml,<br>
 *  project: project1<br>
 *
 *  <p>To create object which particularize some existing reference, call method
 *  which starts with "specify" word.
 *
 *  <p>This object is immutable.
 */
public class DeclarationReference {

  /**
   *  Point to the reference object which is more general.
   */
  private DeclarationReference parent;

  /**
   *  The text.
   */
  private String reference;

  protected static final DeclarationReference emptyRef = new EmptyReference();

  private DeclarationReference(String text) {
    this(text, null);
  }

  private DeclarationReference(String text, DeclarationReference parent) {
    this.reference = text;
    this.parent = parent;
  }

  /**
   *  Creates and returns a reference object to the project with given name.
   *
   *  @param projectName
   *             the name of the project
   *
   *  @return a reference object
   */
  public static DeclarationReference getProjectRef(String projectName) {
    if (!isBlank(projectName)) {
      return new DeclarationReference("In the project: " + projectName);
    } else {
      return emptyRef;
    }
  }

  public DeclarationReference specifyProject(String projectName) {
    if (!isBlank(projectName)) {
      return new DeclarationReference("in the project: " + projectName, this);
    } else {
      return this;
    }
  }

  public static DeclarationReference getFileRef(String fileName) {
    if (!isBlank(fileName)) {
      return new DeclarationReference("In the file: " + fileName);
    } else {
      return emptyRef;
    }
  }

  public DeclarationReference specifyFile(String fileName) {
    if (!isBlank(fileName)) {
      return new DeclarationReference("in the file: " + fileName, this);
    } else {
      return this;
    }
  }

  public static DeclarationReference getLineColumnRef(int line, int column) {
    return new DeclarationReference(
        String.format("on line: %d; column: %d", line, column));
  }

  public DeclarationReference specifyLineColumn(int line, int column) {
    return new DeclarationReference(
        String.format("on line: %d; column: %d", line, column), this);

  }

  public static DeclarationReference getLineRef(int line) {
    return new DeclarationReference(
        String.format("on line: %d", line));
  }

  public DeclarationReference specifyLine(int line) {
    return new DeclarationReference(
        String.format("on line: %d", line), this);
  }

  public static DeclarationReference get(String text) {
    if (!isBlank(text)) {
      return new DeclarationReference(text);
    } else {
      return emptyRef;
    }
  }

  public DeclarationReference specify(String text) {
    if (!isBlank(text)) {
      return new DeclarationReference(text, this);
    } else {
      return this;
    }
  }

  public static DeclarationReference concat(
      DeclarationReference ... references) {
    if (references != null) {
      int size = references.length;
      if (size == 0) {
        return emptyRef;
      } else if (size == 1) {
        return references[0];
      } else {
        return new ConcatReference(references);
      }
    } else {
      return emptyRef;
    }
  }

  /**
   *  Returns the declaration reference as a string. The returned string
   *  contains not only content of this object but also the whole chain
   *  of objects. Declaration ref objects are separated by the colon and
   *  line end. Content of this object is on the firs line, parent on the
   *  second, and so on.
   *
   *  @return a string representation
   */
  @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder(reference);
      for (DeclarationReference dr=parent; dr != null; dr = dr.parent)
      {
        sb.append(',');
        sb.append('\n');
        sb.append(dr.reference);
      }
      return sb.toString();
    }

  protected static class ConcatReference extends DeclarationReference {

    private DeclarationReference[] references;

    ConcatReference(DeclarationReference[] references) {
      super(null, null);
      references = Arrays.stream(references)
        .flatMap(ref -> ref instanceof ConcatReference
            ? Arrays.stream(((ConcatReference)ref).references)
            : Stream.of(ref))
        .toArray(DeclarationReference[]::new);
    }

  }

  protected static class EmptyReference extends DeclarationReference {

    EmptyReference() {
      super(null, null);
    }

    @Override
      public DeclarationReference specify(String text) {
        return get(text);
      }

    @Override
      public DeclarationReference specifyLine(int line) {
        return getLineRef(line);
      }


    @Override
      public DeclarationReference specifyLineColumn(int line, int column) {
        return getLineColumnRef(line, column);
      }


    @Override
      public DeclarationReference specifyFile(String filename) {
        return getFileRef(filename);
      }

    @Override
      public DeclarationReference specifyProject(String projectName) {
        return getProjectRef(projectName);
      }
  }


  public static void main(String[] args) {
    System.out.println(
        DeclarationReference.getProjectRef("control4j")
        .specifyFile("pokus.xml")
        .specifyLineColumn(20, 5)
        .toString());
  }

}
