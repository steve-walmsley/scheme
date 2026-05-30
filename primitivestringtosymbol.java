package scheme;

public class primitivestringtosymbol extends primitiveprocedure {

  protected primitivestringtosymbol() {
    name = "string->symbol";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject s = argl.getCar();
    if ( s.isString() ) {
      return schemesymbol.makeSymbol( ((schemestring)s).stringValue() );
    } else {
      throw new schemeexception(
        name + " : argument is not a string"
      );
    }
  }

}
