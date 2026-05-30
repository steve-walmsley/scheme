package scheme;

import java.util.Arrays;

public class primitivebytevectorcopyto extends primitivebytevectorcopy {

  schemebytevector to;
  int at;

  protected primitivebytevectorcopyto() {
    name = "bytevector-copy!";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject u = argl.getCar();
    if ( u.isByteVector() ) {
      to = (schemebytevector)u;
    } else {
      throw new schemebytevectorexception( name + " : from is not a bytevector" );
    }

    at = 0;
    if ( argl.length() > 1 ) {
      at = ((schemeinteger)argl.getCadr()).integerValue();

      if ( at < 0 ) throw new schemebytevectorexception( name + " at " + at + " out of range" );
    }
    
    setupsrc( (schemelist)argl.getCddr() );

    if ( ( to.length() - at ) < ( end - start ) ) {
      throw new schemebytevectorexception( name + " src size " + (end - start) + " is > dst size " +  ( to.length() - at ) );
    }

    System.arraycopy( src.value(), start, to.value(), at, end - start );

    return schemenull.nullObject();
  }

}
