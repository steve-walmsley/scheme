package scheme;

public class primitivebytevectorset extends primitiveprocedure {

  protected primitivebytevectorset() {
    name = "bytevector-u8-set!";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject o = argl.getCar();
    if ( o.isByteVector() ) {
      schemebytevector v = (schemebytevector)o;
      int k = ((schemeinteger)argl.getCadr()).integerValue();
      byte b = (byte)((schemeinteger)argl.getCaddr()).integerValue();
      if ( ( k >= 0 ) && ( k < v.length() ) ) {
        v.set( k, b );
        return schemeundefined.undefinedObject();
      } else {
        throw new schemebytevectorexception(
          name + " : index " + k + " out of range"
        );
      }
    } else {
      throw new schemebytevectorexception(
        name + " : argument 1 is not a bytevector"
      );
    }
  }

}
