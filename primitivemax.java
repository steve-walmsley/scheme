package scheme;

import java.io.PrintStream;

public class primitivemax extends primitiveprocedure {

  protected primitivemax() {
    name = "max";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return ( ((schemenumber)arg2).isGreaterThan( (schemenumber)arg1 ) ? arg2 : arg1 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = accumulatePrimitive( schemereal.minValue, argl );

    return val;
  }

}
