package scheme;

import java.nio.charset.StandardCharsets;

public class primitivestringtoutf8 extends primitiveprocedure {

  protected primitivestringtoutf8() {
    name = "string->utf8";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    String src;

    schemeobject v = argl.getCar();
    if ( v.isString() ) {
      src = ((schemestring)v).stringValue();
    } else {
      throw new schemebytevectorexception( name + " : argument is not a string" );
    }

    int start = 0;
    if ( argl.length() > 1 ) {
      start = ((schemeinteger)argl.getCadr()).integerValue();

      if ( start < 0 ) throw new schemebytevectorexception( name + " start " + start + " out of range" );
    }

    int end = src.length() - 1;
    if ( argl.length() > 2 ) {
      end = ((schemeinteger)argl.getCaddr()).integerValue();

      if ( end >= src.length() ) throw new schemebytevectorexception( name + " end " + end + " out of range" );
    }

    String range = src.substring( start, end+1 );

    return new schemebytevector( range.getBytes( StandardCharsets.UTF_8 ) );
  }

}
