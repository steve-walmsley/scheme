package scheme;

import java.io.PrintStream;

public class primitivedivide extends primitiveprocedure {

  protected primitivedivide() {
    name = "/";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return arg1.divide( arg2 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject first = argl.getCar(); 
    if ( argl.getCdr().isNull() ) {
      val = first.reciprocal();
    } else {
      val = accumulatePrimitive( first, (schemelist)argl.getCdr() );
    };

    return val;
  }

}
