package scheme;

import java.io.PrintStream;

public class primitivetimes extends primitiveprocedure {

  protected primitivetimes() {
    name = "*";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return arg1.times( arg2 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = accumulatePrimitive( new schemeinteger( 1 ), argl );

    return val;
  }

}
