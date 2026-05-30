package scheme;

import java.io.PrintStream;

public class compoundprocedure extends schemeobject {

  schemepair        exp;
  schemeenvironment env;

  public compoundprocedure( schemepair exp, schemeenvironment env ) {
    this.exp = exp;
    this.env = env;
  }

  protected schemeobject getParameters() {
    return exp.getCadr();
  }

  public schemeobject getBody() {
    schemeobject body = exp.getCddr();
    if ( body.isNull() ) {
      body = new schemepair( schemenull.nullObject() );
    };
    return body;
  }

  public schemeenvironment getEnvironment() {
    return env;
  }

  public schemeenvironment makeBindings( schemelist args )
    throws schemeexception 
  {
    schemeenvironment newenv;

    schemeobject param = getParameters();
    if ( param.isList() ) {
      newenv = env.extend( (schemelist)param, args );
    } else if ( param.isPair() ) {
      newenv = env.extend( (schemepair)param, args );
    } else {
      newenv = env.extend();
      newenv.defineVariable( (schemesymbol)param, args.copy() );
    };
    return newenv;
  }

  public void print( PrintStream out ) {
    out.println( "<PROCEDURE" );
    exp.print( out );
    out.println();
    env.print( out );
    out.println();
    out.println( ">" );
  }

  public boolean isAtom() {
    return false;
  }

  public boolean isProcedure() {
    return true;
  }

  public boolean isCompoundProcedure() {
    return true;
  }


}
