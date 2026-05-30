package scheme;

import java.io.PrintStream;

public class schemeundefined extends schemeobject {

  private static schemeundefined undefinedObject = null;

  public static schemeundefined undefinedObject() {
    if ( undefinedObject == null ) {
      undefinedObject = new schemeundefined();
    };
    return undefinedObject;
  }

  public void print( PrintStream out ) {
  }

}
