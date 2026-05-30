package scheme;

import java.io.PrintStream;

public class primitiveminus extends primitiveprocedure {

  protected primitiveminus() {
    name = "-";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return arg1.minus( arg2 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject first = argl.getCar(); 
    if ( argl.getCdr().isNull() ) {
      val = first.negative();
    } else {
      val = accumulatePrimitive( first, (schemelist)argl.getCdr() );
    };

    return val;
  }

}
