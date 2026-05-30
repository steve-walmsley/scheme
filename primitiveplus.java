package scheme;

import java.io.PrintStream;

public class primitiveplus extends primitiveprocedure {

  protected primitiveplus() {
    name = "+";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return arg1.plus( arg2 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = accumulatePrimitive( new schemeinteger( 0 ), argl );

    return val;
  }

}
