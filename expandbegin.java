package scheme;

import java.io.PrintStream;

public class expandbegin extends schemeexpander {

  protected schemepair   expanded;

  public expandbegin() {
    super( "begin" );
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
/*
    try {
*/
      schemepair p1 = new schemepair( schemepair.lambdaSymbol );      
      schemepair p2 = new schemepair( schemenull.nullObject() );
      p1.setCdr( p2 );
      p2.setCdr( exp );
      expanded = new schemepair( p1 );
      return expanded;
/*
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandbegin.expand() : ill-formed begin expression"
      );
    }
*/
  }

}
