package scheme;

import java.io.PrintStream;

import java.util.Arrays;
import java.util.Vector;

public class schemebytevector extends schemeobject {

  protected byte[] value;

  public schemebytevector( byte[] value ) {
    this.value = value;
  }

  public schemebytevector( int k ) {
    value = new byte[k];
  }

  public schemebytevector( int k, byte fill ) {
    value = new byte[k];
    Arrays.fill( value, fill );
  }

  public byte[] value() {
    return value;
  }

/*----------------------------------------------------------------------------*/
/* Primitive Operations                                                       */
/*----------------------------------------------------------------------------*/

  public byte get( int k ) {
    return value[k];
  }

  public void set( int k, byte obj ) {
    value[k] = obj;
  }

  public int length() { 
    return value.length; 
  }

/*----------------------------------------------------------------------------*/
/* Eqivalence Predicates                                                      */
/*----------------------------------------------------------------------------*/

  public boolean isEqual( schemeobject o ) {
    if ( o instanceof schemebytevector ) {
      schemebytevector v = (schemebytevector)o;
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
        expression = new schemebytevector( 0 );
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
          expression = new schemebytevector( toByteArray( v ) );
          endOfList = true;
          break;
        case schemetokenizer.tokenRightBracket :
          expression = new schemebytevector( toByteArray( v ) );
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

  private static byte[] toByteArray( Vector<schemeobject> v ) {
    byte[] b = new byte[ v.size() ];
    int i = 0;
    for( schemeobject o : v ) {
      b[i] = (byte)((schemeinteger)o).integerValue();
      i = i + 1;
    }
    return b;
  }

public void print( PrintStream out ) {
  out.print( "#u8(" );
  if ( value.length > 0 ) {
    out.print( value[0] & 0xFF );
    if ( value.length > 1 ) {
      for ( int v = 1; v < value.length; v++ ) {
        out.print( " " );
        out.print( value[v] & 0xFF  );
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

  public boolean isByteVector() {
    return true;
  };

}
