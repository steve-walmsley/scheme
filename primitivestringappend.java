package scheme;

public class primitivestringappend extends primitiveprocedure {

  protected primitivestringappend() {
    name = "string-append";
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    return ((schemestring)arg1).append( (schemestring)arg2 );
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = accumulatePrimitive( new schemestring( "" ), argl );

    return val;
  }

}
