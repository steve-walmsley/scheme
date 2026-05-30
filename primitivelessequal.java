package scheme;

import java.io.PrintStream;

public class primitivelessequal extends primitiveprocedure {

  protected primitivelessequal() {
    name = "<=";
  }

  protected boolean booleanOperation( schemeobject arg1, schemeobject arg2 ) 
    throws schemeexception 
  {
    boolean val = false;

    if ( ((schemenumber)arg1).isLessEqual( (schemenumber)arg2 ) ) {
      val = true;
    };    
    return val;
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {

    return orderPrimitive( argl );
  }

}
