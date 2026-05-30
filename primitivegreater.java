package scheme;

import java.io.PrintStream;

public class primitivegreater extends primitiveprocedure {

  protected primitivegreater() {
    name = ">";
  }

  protected boolean booleanOperation( schemeobject arg1, schemeobject arg2 ) 
    throws schemeexception 
  {
    boolean val = false;

    if ( ((schemenumber)arg1).isGreaterThan( (schemenumber)arg2 ) ) {
      val = true;
    };    
    return val;
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {

    return orderPrimitive( argl );
  }

}
