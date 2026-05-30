package scheme;

import java.util.Arrays;

public class primitivebytevectorcopy extends primitiveprocedure {

  schemebytevector src;
  int start;
  int end;

  protected primitivebytevectorcopy() {
    name = "bytevector-copy";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    setupsrc( argl );
    return new schemebytevector( Arrays.copyOfRange( src.value(), start, end ) );
  }

  void setupsrc( schemelist argl ) throws schemeexception {
    schemeobject v = argl.getCar();
    if ( v.isByteVector() ) {
      src = (schemebytevector)v;
    } else {
      throw new schemebytevectorexception( name + " : argument is not a bytevector" );
    }

    start = 0;
    if ( argl.length() > 1 ) {
      start = ((schemeinteger)argl.getCadr()).integerValue();

      if ( start < 0 ) throw new schemebytevectorexception( name + " start " + start + " out of range" );
    }

    end = src.length() - 1;
    if ( argl.length() > 2 ) {
      end = ((schemeinteger)argl.getCaddr()).integerValue();

      if ( end >= src.length() ) throw new schemebytevectorexception( name + " end " + end + " out of range" );
    }

  }

}
