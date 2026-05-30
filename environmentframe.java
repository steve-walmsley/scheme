package scheme;

import java.io.PrintStream;

import java.util.Hashtable;

public class environmentframe extends schemeobject {

  protected Hashtable<schemesymbol,schemeobject> frame = new Hashtable<>();

  public environmentframe() {
  }

  public environmentframe( schemepair vars, schemelist vals ) 
    throws schemelistexception 
  {
    schemesymbol var;
    schemeobject val;
    boolean endOfVars = !vars.isPair();
    boolean endOfVals = vals.isNull();
    while( !endOfVars && !endOfVals ) {
      var = (schemesymbol)vars.getCar();
      val = vals.getCar();
      frame.put( var, val );
      if ( vars.getCdr().isPair() ) {
        vars = (schemepair)vars.getCdr();
      } else {
        endOfVars = true;
      };
      if ( vals.getCdr().isPair() ) {
        vals = (schemelist)vals.getCdr();
      } else {
        endOfVals = true;
      };
    };
    if ( !endOfVars && endOfVals ) {
      throw new schemelistexception( 
        "environmentframe.<init> not enough values"
      );
    };
    if ( endOfVars && !endOfVals ) {
      if ( vars.getCdr().isSymbol() ) {
        frame.put( (schemesymbol)vars.getCdr(), vals );
      }
    };
    if ( endOfVars && endOfVals ) {
      if ( vars.getCdr().isSymbol() ) {
        frame.put( (schemesymbol)vars.getCdr(), schemenull.nullObject() );
      }
    };
  }


  public environmentframe( schemelist vars, schemelist vals ) 
    throws schemelistexception 
  {
    schemesymbol var;
    schemeobject val;
    boolean endOfVars = vars.isNull();
    boolean endOfVals = vals.isNull();
    while( !endOfVars && !endOfVals ) {
      var = (schemesymbol)vars.getCar();
      val = vals.getCar();
      frame.put( var, val );
      if ( vars.getCdr().isPair() ) {
        vars = (schemepair)vars.getCdr();
      } else {
        endOfVars = true;
      };
      if ( vals.getCdr().isPair() ) {
        vals = (schemelist)vals.getCdr();
      } else {
        endOfVals = true;
      };
    };
    if ( !endOfVars && endOfVals ) {
      throw new schemelistexception( 
        "environmentframe.<init> not enough values"
      );
    };
    if ( endOfVars && !endOfVals ) {
      throw new schemelistexception( 
        "environmentframe.<init> too many values"
      );
    };
  }


/*
  public environmentframe( schemelist vars, schemelist vals ) 
    throws schemelistexception 
  {
    schemesymbol var;
    schemeobject val;
    boolean endOfVars = vars.isNull();
    boolean endOfVals = vals.isNull();
    try {
      while( !endOfVars && !endOfVals ) {
        var = (schemesymbol)vars.getCar();
        val = vals.getCar();
        frame.put( var, val );
        vars = (schemelist)vars.getCdr();
        vals = (schemelist)vals.getCdr();
        endOfVars = vars.isNull();
        endOfVals = vals.isNull();
      }
    } catch ( schemelistexception sle ) {
      throw new schemelistexception( 
        "environmentframe.<init> vars and vals don't match"
      );
    };
  }
*/

  public void defineVariable( schemesymbol variable, schemeobject value ) {
    frame.put( variable, value );
  }

  public schemeobject lookupVariable( schemesymbol variable ) 
    throws unboundvariableexception 
  {
    schemeobject value;

    value = (schemeobject)frame.get( variable );
    if ( value != null ) {
      return value;
    } else {
      throw new unboundvariableexception( 
        "environmentframe.lookupVariable undefined variable " + variable
      );
    }
  }

  public void setVariableValue( schemesymbol variable, schemeobject value ) 
    throws unboundvariableexception 
  {
    if ( frame.containsKey( variable ) ) {
      frame.put( variable, value );
    } else {
      throw new unboundvariableexception( 
        "environmentframe.setVariableValue undefined variable " + variable
      );
    }
  }

  public void print( PrintStream out ) {
    out.print( "<FRAME> " + frame );
  }

  public boolean isAtom() {
    return false;
  }
}
