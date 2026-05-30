package scheme;

import java.io.PrintStream;

public class primitiveappend extends primitiveprocedure {

  protected primitiveappend() {
    name = "append";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isList() ) {
      schemeobject next = argl.getCdr();
      if ( next.isPair() ) {
        schemeobject o = ((schemelist)next).getCar();
        if ( o.isList() ) {
          o = apply( ((schemelist)next) );
        };
        return ((schemelist)l).copy().append( o );
      } else {
        return l;
      }
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a list"
      );
    }
  }

}
