package scheme;

import java.io.PrintStream;

public class schemeexpander extends schemeobject {

  protected String keyword;

  public schemeexpander( String keyword ) {
    this.keyword = keyword;
  }

  public void print( PrintStream out ) {
    out.println( "< EXPANDER " + keyword + " >" );
  }

  public boolean isAtom() {
    return false;
  }

  public boolean isExpander() {
    return true;
  }

  public schemeobject expand() throws schemeexpandexception {
    return schemenull.nullObject();
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    throw new schemeexpandexception( "scheme.schemeexpander.expand()" );
  }

}
