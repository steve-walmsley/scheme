package scheme;

public class primitivevectorref extends primitiveprocedure {

  protected primitivevectorref() {
    name = "vector-ref";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject o = argl.getCar();
    if ( o.isVector() ) {
      schemevector v = (schemevector)o;
      int k = ((schemeinteger)argl.getCadr()).integerValue();
      if ( ( k >= 0 ) && ( k < v.length() ) ) {
        return ((schemevector)v).get( k );
      } else {
        throw new schemebytevectorexception(
          name + " : index " + k + " out of range"
        );
      }
    } else {
      throw new schemebytevectorexception(
        name + " : argument 1 is not a vector"
      );
    }
  }

}
