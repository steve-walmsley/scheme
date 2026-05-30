package scheme;

import java.io.PrintStream;

public class primitivemin extends primitiveprocedure {

  protected primitivemin() {
    name = "min";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return ( ((schemenumber)arg2).isLessThan( (schemenumber)arg1 ) ? arg2 : arg1 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = accumulatePrimitive( schemereal.maxValue, argl );

    return val;
  }

}
