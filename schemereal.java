package scheme;

import java.io.PrintStream;

public class schemereal extends schemenumber {

  public static final double floatZero = 1.0E-13;

  public static final schemereal minValue = new schemereal( Double.MIN_VALUE );

  public static final schemereal maxValue = new schemereal( Double.MAX_VALUE );

  protected double realValue;

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    return new schemereal( tokenizer.getReal() );
  }

  protected schemereal( double value ) {
    realValue = value;
  }

  public void print( PrintStream out ) {
    if ( Math.abs( realValue ) > floatZero ) {
      out.print( realValue );
    } else {
      out.print( 0.0 );
    }
  }

  public String toString() {
    return Double.toString( realValue );
  }

  /*--------------------------------------------------------------------------*/
  /* Methods of schemenumber                                                  */
  /*--------------------------------------------------------------------------*/

  public double realValue() {
    return realValue;
  }

  public boolean isLessThan( schemenumber n ) {
    if ( n.isInteger() ) {
      return realValue < ((schemeinteger)n).integerValue();
    } else {
      return realValue < ((schemereal)n).realValue();
    }
  }

  public boolean isEqualTo( schemenumber n ) {
    if ( n.isInteger() ) {
      return realValue == ((schemeinteger)n).integerValue();
    } else {
      return realValue == ((schemereal)n).realValue();
    }
  }

  public boolean isGreaterThan( schemenumber n ) {
    if ( n.isInteger() ) {
      return realValue > ((schemeinteger)n).integerValue();
    } else {
      return realValue > ((schemereal)n).realValue();
    }
  }

  public boolean isLessEqual( schemenumber n ) {
    if ( n.isInteger() ) {
      return realValue <= ((schemeinteger)n).integerValue();
    } else {
      return realValue <= ((schemereal)n).realValue();
    }
  }

  public boolean isGreaterEqual( schemenumber n ) {
    if ( n.isInteger() ) {
      return realValue >= ((schemeinteger)n).integerValue();
    } else {
      return realValue >= ((schemereal)n).realValue();
    }
  }

  public schemeobject plus( schemeobject n ) throws schemeexception {
    if ( n.isReal() ) {
      return new schemereal( realValue + ((schemenumber)n).realValue() );
    } else {
      return n.plus( this );
    }
  }

  public schemeobject times( schemeobject n ) throws schemeexception {
    if ( n.isReal() ) {
      return new schemereal( realValue * ((schemenumber)n).realValue() );
    } else {
      return n.times( this );
    }
  }

  public schemeobject minus( schemeobject n ) throws schemeexception {
    if ( n.isReal() ) {
      return new schemereal( realValue - ((schemenumber)n).realValue() );
    } else {
      return n.negative().plus( this );
    }
  }

  public schemeobject divide( schemeobject n ) throws schemeexception {
    if ( n.isReal() ) {
      return new schemereal( realValue / ((schemenumber)n).realValue() );
    } else {
      return n.reciprocal().times( this );
    }
  }

  public schemeobject negative() throws schemeexception {
    return new schemereal( -realValue );
  }

  public schemeobject reciprocal() throws schemeexception {
    return new schemereal( 1.0/realValue );
  }

  /*--------------------------------------------------------------------------*/
  /* Scheme Type Predicates                                                   */
  /*--------------------------------------------------------------------------*/

  public boolean isAtom() {
    return true;
  }

  public boolean isNumber() {
    return true;
  }

  public boolean isComplex() {
    return true;
  }

  public boolean isReal() {
    return true;
  }

  /*--------------------------------------------------------------------------*/
  /* Syntax Predicates                                                        */
  /*--------------------------------------------------------------------------*/

  public boolean isSelfEvaluating() {
    return true;
  };

}
