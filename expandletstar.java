package scheme;

import java.io.PrintStream;

public class expandletstar extends schemeexpander {

  static schemesymbol letSymbol = schemesymbol.makeSymbol( "let" ); 
  static schemesymbol letstarSymbol = schemesymbol.makeSymbol( "let*" ); 

  protected schemepair expanded;

  public expandletstar() {
    super( "let*" );
  }

  protected schemeobject firstBinding( schemeobject bindings ) 
    throws schemelistexception 
  {
    if ( bindings.isList() ) { 
      return new schemepair( ((schemepair)bindings).getCar() );
    } else {
      throw new schemelistexception( "expandletstar.firstBinding()" );
    }
  }

  protected schemeobject restBindings( schemeobject bindings ) 
    throws schemelistexception 
  {
    if ( bindings.isList() ) { 
      return ((schemelist)bindings).getCdr();
    } else {
      throw new schemelistexception( "expandletstar.restBindings()" );
    }
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {
      schemeobject bindings = exp.getCar();
      schemeobject body     = exp.getCdr();
      if ( bindings.isPair() ) {

        schemepair letstarExpression = new schemepair( 
          new schemepair(
            letstarSymbol, 
            new schemepair( restBindings( bindings ), body )
          )
        );

        expanded = new schemepair( 
          letSymbol, 
          new schemepair( firstBinding( bindings ), letstarExpression ) 
        );

        return expanded;
      } else if ( bindings.isNull() ) {

        expanded = new schemepair( 
          letSymbol, 
          new schemepair( bindings, body )
        );

        return expanded;

      } else {
        throw new schemelistexception( "expandletstar.expand()" );
      }
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandletstar.expand() : ill-formed letstar expression"
      );
    }
  }

}
