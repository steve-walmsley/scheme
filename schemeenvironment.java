package scheme;

import java.io.PrintStream;

import java.util.Hashtable;

public class schemeenvironment extends schemeobject {

  protected schemeenvironment scope = null;

  protected environmentframe  frame = new environmentframe();

  public schemeenvironment() {
  }

  public schemeenvironment( environmentframe frame, schemeenvironment scope ) {
    this.frame = frame;
    this.scope = scope;
  }

  public void defineVariable( schemesymbol variable, schemeobject value ) {
    frame.defineVariable( variable, value );
  }

  public schemeobject lookupVariable( schemesymbol variable ) 
    throws schemeexception 
  {
    try {
      return frame.lookupVariable( variable );
    } catch ( unboundvariableexception uve ) {
      if ( scope != null ) {
        return scope.lookupVariable( variable );
      } else {
        throw new unboundvariableexception( 
          "schemeenvironment.lookupVariable undefined variable " + variable
        );
      }
    }
  }

  public void setVariableValue( schemesymbol variable, schemeobject value ) 
    throws schemeexception 
  {

    try {
      frame.setVariableValue( variable, value );
    } catch ( unboundvariableexception uve ) {
      if ( scope != null ) {
        scope.setVariableValue( variable, value );
      } else {
        throw new unboundvariableexception( 
          "schemeenvironment.setVariableValue undefined variable " + variable
        );
      }
    }

  }

  public schemeenvironment extend() {
    environmentframe newFrame = new environmentframe();
    return new schemeenvironment( newFrame, this );
  }

  public schemeenvironment extend( schemepair vars, schemelist args ) 
    throws schemelistexception 
  {
    environmentframe newFrame = new environmentframe( vars, args );
    return new schemeenvironment( newFrame, this );
  }

  public schemeenvironment extend( schemelist vars, schemelist args ) 
    throws schemelistexception 
  {
    environmentframe newFrame = new environmentframe( vars, args );
    return new schemeenvironment( newFrame, this );
  }

  public void print( PrintStream out ) {
    out.print( "<ENVIRONMENT>" );
  }

  public boolean isAtom() {
    return false;
  }

  public boolean isEnvironment() {
    return true;
  }
}
