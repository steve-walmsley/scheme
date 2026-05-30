package scheme;

import java.io.PrintStream;
/*
 * R5RS 4.2.1 p11 and 7.3 p 43
 */
public class expandor extends schemeexpander {

  static schemesymbol orSymbol = schemesymbol.makeSymbol( "or" ); 

  public expandor() {
    super( "or" );
  }

  public schemeobject expand() throws schemeexpandexception {
    //
    // (or) => #f
    //
    return schemefalse.falseObject();
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {
      if ( exp.length() == 1 ) {
        //
        // ( or test ) => test
        //
        return exp.getCar();
      } else {
        //
        // ( or test1 test2 ... ) => ( let ((x test1)) ( if x x ( or test2 ... ) ) )
        //
        schemesymbol x = schemesymbol.makeSymbol( "x" );

        schemepair initialisation = new schemepair( x );
        initialisation.append( new schemepair( exp.getCar() ) );

        schemepair ifStatement = new schemepair( schemepair.ifSymbol );
        ifStatement.append( new schemepair( x ) );
        ifStatement.append( new schemepair( x ) );
        ifStatement.append( new schemepair( new schemepair( orSymbol, exp.getCdr() ) ) );

        schemepair expanded = new schemepair( schemesymbol.makeSymbol( "let" ) );
        expanded.append( new schemepair( new schemepair( initialisation ) ) );
        expanded.append( new schemepair( ifStatement ) );
        return expanded;
      }
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandlet.expand() : ill-formed let expression"
      );
    }
  }

}
