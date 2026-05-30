package scheme;

import java.io.PrintStream;
/*
 * R5RS 4.2.1 p11 and 7.3 p 43
 */
public class expandand extends schemeexpander {

  static schemesymbol andSymbol = schemesymbol.makeSymbol( "and" ); 

  public expandand() {
    super( "and" );
  }

  public schemeobject expand() throws schemeexpandexception {
    //
    // (and) => #t
    //
    return schemetrue.trueObject();
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {
      if ( exp.length() == 1 ) {
        //
        // ( and test ) => test
        //
        return exp.getCar();
      } else {
        //
        // ( and test1 test2 ... ) => ( if test1 ( and test2 ... ) #f )
        //
        schemepair expanded = new schemepair( schemepair.ifSymbol );
        expanded.append( new schemepair( exp.getCar() ) );
        expanded.append( new schemepair( new schemepair( andSymbol, exp.getCdr() ) ) );
        expanded.append( new schemepair( schemefalse.falseObject() ) );
        return expanded;
      }
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandlet.expand() : ill-formed let expression"
      );
    }
  }

}
