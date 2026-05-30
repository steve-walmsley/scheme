package scheme;

import java.io.PrintStream;

public class schemeinteger extends schemenumber {

  protected int integerValue;

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    return new schemeinteger( tokenizer.getInteger() );
  }

  protected schemeinteger( int value ) {
    integerValue = value;
  }

  public void print( PrintStream out ) {
    out.print( integerValue );
  }

  public String toString() {
    return Integer.toString( integerValue );
  }

  /*--------------------------------------------------------------------------*/
  /* Methods of schemenumber                                                  */
  /*--------------------------------------------------------------------------*/

  public int integerValue() {
    return integerValue;
  }

  public double realValue() {
    return integerValue;
  }

  public boolean isLessThan( schemenumber n ) {
    if ( n.isInteger() ) {
      return integerValue < ((schemeinteger)n).integerValue();
    } else {
      return integerValue < ((schemereal)n).realValue();
    }
  }

  public boolean isEqualTo( schemenumber n ) {
    if ( n.isInteger() ) {
      return integerValue == ((schemeinteger)n).integerValue();
    } else {
      return integerValue == ((schemereal)n).realValue();
    }
  }

  public boolean isGreaterThan( schemenumber n ) {
    if ( n.isInteger() ) {
      return integerValue > ((schemeinteger)n).integerValue();
    } else {
      return integerValue > ((schemereal)n).realValue();
    }
  }

  public boolean isLessEqual( schemenumber n ) {
    if ( n.isInteger() ) {
      return integerValue <= ((schemeinteger)n).integerValue();
    } else {
      return integerValue <= ((schemereal)n).realValue();
    }
  }

  public boolean isGreaterEqual( schemenumber n ) {
    if ( n.isInteger() ) {
      return integerValue >= ((schemeinteger)n).integerValue();
    } else {
      return integerValue >= ((schemereal)n).realValue();
    }
  }

  public schemeobject plus( schemeobject n ) throws schemeexception {
    if ( n.isInteger() ) {
      return new schemeinteger( 
        integerValue + ((schemenumber)n).integerValue() 
      );
    } else {
      return n.plus( this );
    }
  }

  public schemeobject times( schemeobject n ) throws schemeexception {
    if ( n.isInteger() ) {
      return new schemeinteger( 
        integerValue * ((schemenumber)n).integerValue() 
      );
    } else {
      return n.times( this );
    }
  }

  public schemeobject minus( schemeobject n ) throws schemeexception {
    if ( n.isInteger() ) {
      return new schemeinteger( 
        integerValue - ((schemenumber)n).integerValue() 
      );
    } else {
      return n.negative().plus( this );
    }
  }

  public schemeobject divide( schemeobject n ) throws schemeexception {
    if ( n.isInteger() ) {
      return new schemereal( 
        (double)integerValue / (double)((schemenumber)n).integerValue() 
      );
    } else {
      return n.reciprocal().times( this );
    }
  }

  public schemeobject negative() throws schemeexception {
    return new schemeinteger( -integerValue );
  }

  public schemeobject reciprocal() throws schemeexception {
    return new schemereal( 1.0/(double)integerValue );
  }

  public schemeinteger quotient( schemeinteger n ) throws schemeexception {
    if ( ( n.integerValue() > 0 ) || ( n.integerValue() < 0 ) ) {
      return new schemeinteger( 
        integerValue / n.integerValue() 
      );
    } else {
      throw new schemenumberexception(
        "scheme.schemeinteger.quotient() : attempted divide by zero"
      );
    }
  }

  public schemeinteger remainder( schemeinteger n ) throws schemeexception {
    if ( ( n.integerValue() > 0 ) || ( n.integerValue() < 0 ) ) {
      return new schemeinteger( 
        integerValue % n.integerValue() 
      );
    } else {
      throw new schemenumberexception(
        "scheme.schemeinteger.remainder() : attempted divide by zero"
      );
    }
  }

  public schemeinteger modulo( schemeinteger n ) throws schemeexception {
    if ( ( n.integerValue() > 0 ) || ( n.integerValue() < 0 ) ) {
      int r = integerValue % n.integerValue();
      if ( n.integerValue < 0 ) {
        if ( r > 0 ) {
          return new schemeinteger( r + n.integerValue );
        } else {
          return new schemeinteger( r );
        }
      } else {
        if ( r < 0 ) {
          return new schemeinteger( r + n.integerValue );
        } else {
          return new schemeinteger( r );
        }
      }
    } else {
      throw new schemenumberexception(
        "scheme.schemeinteger.modulo() : attempted divide by zero"
      );
    }
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

  public boolean isRational() {
    return true;
  }

  public boolean isInteger() {
    return true;
  }

  /*--------------------------------------------------------------------------*/
  /* Syntax Predicates                                                        */
  /*--------------------------------------------------------------------------*/

  public boolean isSelfEvaluating() {
    return true;
  };

}
