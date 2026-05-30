package scheme;

public class primitivestringtonumber extends primitiveprocedure {

  protected primitivestringtonumber() {
    name = "string->number";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject s = argl.getCar();
    if ( s.isString() ) {
      schemeobject r = argl.getCadr();
      if ( r.isNull() ) {
        try {
          double dVal = Double.parseDouble( ((schemestring)s).stringValue() );
          if ( dVal > Math.floor( dVal ) ) {
            return new schemereal( dVal );
          } else {
            return new schemeinteger( (int)Math.floor( dVal ) );
          }
        } catch ( NumberFormatException nfe ) {
          return schemefalse.falseObject();
        }
      } else if ( r.isInteger() ) {
        try {
          int iVal = Integer.parseInt( 
            ((schemestring)s).stringValue(), ((schemeinteger)r).integerValue 
          );
          return new schemeinteger( iVal );
        } catch ( NumberFormatException nfe ) {
          return schemefalse.falseObject();
        }
      } else {
        throw new schemeexception(
          name + " : radix is not an integer"
        );
      }
    } else {
      throw new schemeexception(
        name + " : argument is not a string"
      );
    }
  }

}
