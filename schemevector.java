package scheme;

import java.io.PrintStream;

import java.util.Arrays;
import java.util.Vector;

public class schemevector extends schemeobject {

  protected schemeobject[] value;

  public schemevector( schemeobject[] value ) {
    this.value = value;
  }

  public schemevector( int k ) {
    value = new schemeobject[k];
  }

  public schemevector( int k, schemeobject fill ) {
    value = new schemeobject[k];
    Arrays.fill( value, fill );
  }

  public schemeobject[] value() {
    return value;
  }

/*----------------------------------------------------------------------------*/
/* Primitive Operations                                                       */
/*----------------------------------------------------------------------------*/

  public schemeobject get( int k ) {
    return value[k];
  }

  public void set( int k, schemeobject obj ) {
    value[k] = obj;
  }

  public int length() { 
    return value.length; 
  }

/*----------------------------------------------------------------------------*/
/* Eqivalence Predicates                                                      */
/*----------------------------------------------------------------------------*/

  public boolean isEqual( schemeobject o ) {
    if ( o instanceof schemevector ) {
      schemevector v = (schemevector)o;
      return Arrays.equals( value, v.value() );
    } else {
      return super.isEqv( o );
    }
  }

/*----------------------------------------------------------------------------*/
/* Interpreter routines                                                       */
/*----------------------------------------------------------------------------*/

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    Vector<schemeobject> v = new Vector<schemeobject>();

    schemeobject expression = schemenull.nullObject();
  
    boolean endOfList = false;

    int token = tokenizer.nextToken();
    switch ( token ) {
      case schemetokenizer.endOfFileToken :
        expression = new schemeeof();
        endOfList = true;
        break;
      case schemetokenizer.tokenRightBracket :
        expression = new schemevector( 0 );
        endOfList = true;
        break;
      default :
        tokenizer.pushBack();
        v.add( schemeobject.read( tokenizer ) );
        break;
    };
    while( !endOfList ) {
      token = tokenizer.nextToken();
      switch ( token ) {
        case schemetokenizer.endOfFileToken :
          expression = new schemevector( v.toArray( new schemeobject[ v.size() ] ) );
          endOfList = true;
          break;
        case schemetokenizer.tokenRightBracket :
          expression = new schemevector( v.toArray( new schemeobject[ v.size() ] ) );
          endOfList = true;
          break;
        default :
          tokenizer.pushBack();
          v.add( schemeobject.read( tokenizer ) );
          break;
      };
    };
    return expression;
  }

public void print( PrintStream out ) {
  out.print( "#(" );
  if ( value.length > 0 ) {
    value[0].print( out );
    if ( value.length > 1 ) {
      for ( int v = 1; v < value.length; v++ ) {
        out.print( " " );
        value[v].print( out );
      }
    }
  }
  out.print( ")" );
}

/*----------------------------------------------------------------------------*/
/* Scheme Type Predicates                                                     */
/*----------------------------------------------------------------------------*/

  public boolean isSelfEvaluating() {
    return true;
  };

  public boolean isVector() {
    return true;
  };

}
