package scheme;

public class primitivebytevectorref extends primitiveprocedure {

  protected primitivebytevectorref() {
    name = "bytevector-u8-ref";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject o = argl.getCar();
    if ( o.isByteVector() ) {
      schemebytevector v = (schemebytevector)o;
      int k = ((schemeinteger)argl.getCadr()).integerValue();
      if ( ( k >= 0 ) && ( k < v.length() ) ) {
        return new schemeinteger( v.get( k ) & 0xFF );
      } else {
        throw new schemelistexception(
          name + " : index " + k + " out of range"
        );
      }
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a bytevector"
      );
    }
  }

}
