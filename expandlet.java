package scheme;

import java.io.PrintStream;

public class expandlet extends schemeexpander {

  protected schemeobject bindings;
  protected schemeobject body;
  protected schemelist   variables;
  protected schemelist   expressions;
  protected schemepair   expanded;

  public expandlet() {
    super( "let" );
  }

  protected schemeobject bindingVariable( schemeobject binding ) 
    throws schemelistexception 
  {
    if ( binding.isList() ) { 
      return ((schemelist)binding).getCar();
    } else {
      throw new schemelistexception( "expandlet.bindingVariable()" );
    }
  }

  protected schemeobject bindingExpression( schemeobject binding ) 
    throws schemelistexception 
  {
    if ( binding.isList() ) { 
      return ((schemelist)binding).getCadr();
    } else {
      throw new schemelistexception( "expandlet.bindingExpression()" );
    }
  }

  protected schemelist separateVariables( schemelist bindings ) 
    throws schemelistexception 
  {
    schemepair newp = new schemepair( bindingVariable( bindings.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)bindings.getCdr();

    while( next.isPair() ) {
      newp.setCdr( new schemepair( bindingVariable( next.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  protected schemelist separateExpressions( schemelist bindings ) 
    throws schemelistexception 
  {
    schemepair newp = new schemepair( bindingExpression( bindings.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)bindings.getCdr();

    while( next.isPair() ) {
      newp.setCdr( new schemepair( bindingExpression( next.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {
      bindings = exp.getCar();
      body     = exp.getCdr();
      if ( bindings.isPair() ) {
        variables = separateVariables( (schemelist)bindings );
        expressions = separateExpressions( (schemelist)bindings );
      } else if ( bindings.isNull() ) {
        variables   = schemenull.nullObject();
        expressions = schemenull.nullObject();
      } else {
        throw new schemelistexception( "expandlet.expand()" );
      };
      schemepair p1 = new schemepair( schemepair.lambdaSymbol );      
      schemepair p2 = new schemepair( variables );
      p1.setCdr( p2 );
      p2.setCdr( body );
      expanded = new schemepair( p1 );
      expanded.setCdr( expressions );
      return expanded;
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandlet.expand() : ill-formed let expression"
      );
    }
  }

}
