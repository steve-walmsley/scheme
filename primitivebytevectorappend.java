package scheme;

import java.util.Arrays;

public class primitivebytevectorappend extends primitiveprocedure {

  protected primitivebytevectorappend() {
    name = "bytevector-append";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.length() > 0 ) {
      schemebytevector dst = new schemebytevector( dstlength( argl ) );

      int at = 0;
      for ( schemeobject v : ((schemepair)argl).elements() ) {
        schemebytevector src = (schemebytevector)v;
        System.arraycopy( src.value(), 0, dst.value(), at, src.length() );
        at = at + src.length();
      }

      return dst;
    } else {
      return schemenull.nullObject();
    }
  }

  private int dstlength( schemelist argl ) {
    int l = 0;
    for ( schemeobject v : ((schemepair)argl).elements() ) {
      l = l + ((schemebytevector)v).length();
    }
    return l;
  }

}
