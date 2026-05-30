package scheme;

import java.io.PrintStream;

public abstract class schemeobject {

protected final static schemesymbol quoteSymbol = 
  schemesymbol.makeSymbol( "quote" );

protected static schemeobject makeAtom( schemetokenizer tokenizer ) 
  throws schemeexception 
{
  schemeobject expression = null;

  int token = tokenizer.getTokenType();
  switch ( token ) {
    case schemetokenizer.tokenIdentifier :
      expression = schemesymbol.read( tokenizer );
      break;
    case schemetokenizer.tokenString :
      expression = schemestring.read( tokenizer );
      break;
    case schemetokenizer.tokenReal :
      expression = schemereal.read( tokenizer );
      break;
    case schemetokenizer.tokenInteger :
      expression = schemeinteger.read( tokenizer );
      break;
    case schemetokenizer.unrecognizedToken :
      throw new schemeexception( 
        "schemeobject.makeAtom unrecognized token " 
      );
    default :
      throw new schemeexception( 
        "schemeobject.makeAtom unexpected token " + token 
      );
  };
  return expression;
}

public static schemeobject read( schemetokenizer tokenizer ) 
  throws schemeexception 
{
  schemeobject expression = null;

  int token = tokenizer.nextToken();
  switch ( token ) {
    case schemetokenizer.endOfFileToken :
      expression = new schemeeof();
      break;
    case schemetokenizer.tokenLeftBracket :
      expression = schemepair.read( tokenizer );
      break;
    case schemetokenizer.tokenHashBracket :
      expression = schemevector.read( tokenizer );
      break;
    case schemetokenizer.tokenHashU8Bracket :
      expression = schemebytevector.read( tokenizer );
      break;
    case schemetokenizer.tokenQuote :
      expression = new schemepair( quoteSymbol );
      ((schemepair)expression).setCdr( 
        new schemepair( schemeobject.read( tokenizer ) ) 
      );
      break;
    default :
      expression = schemeobject.makeAtom( tokenizer );
      break;
  };
  return expression;
}

public abstract void print( PrintStream out );

public void display( PrintStream out ) {
  print( out );
}

/*----------------------------------------------------------------------------*/
/* Scheme Type Predicates                                                     */
/*----------------------------------------------------------------------------*/

public boolean isNull() {
  return false;
}

public boolean isAtom() {
  return true;
}

public boolean isPair() {
  return false;
}

public boolean isList() {
  return false;
}

public boolean isBoolean() {
  return false;
}

public boolean isTrue() {
  return true;
}

public boolean isNumber() {
  return false;
}

public boolean isComplex() {
  return false;
}

public boolean isReal() {
  return false;
}

public boolean isRational() {
  return false;
}

public boolean isInteger() {
  return false;
}

public boolean isExpander() {
  return false;
}

public boolean isString() {
  return false;
}

public boolean isProcedure() {
  return false;
}

public boolean isSymbol() {
  return false;
}

public boolean isEnvironment() {
  return false;
}

public boolean isVector() {
  return false;
}

public boolean isByteVector() {
  return false;
}

/*----------------------------------------------------------------------------*/
/* Syntax Predicates                                                          */
/*----------------------------------------------------------------------------*/

public boolean isSelfEvaluating() {
  return false;
};

public boolean isQuoted() {
  return false;
}

public boolean isVariable() {
  return false;
}

public boolean isDefinition() {
  return false;
}

public boolean isAssignment() {
  return false;
}

public boolean isLambda() {
  return false;
}

public boolean isConditional() {
  return false;
}

public boolean isNoArgs() {
  return false;
}

public boolean isApplication() {
  return false;
}

public boolean isPrimitiveProcedure() {
  return false;
}

public boolean isCompoundProcedure() {
  return false;
}

public boolean isExternalProcedure() {
  return false;
}

public boolean noClauses() {
  return true;
}

public boolean isIf() {
  return false;
}

/*----------------------------------------------------------------------------*/
/* Eqivalence Predicates                                                      */
/*----------------------------------------------------------------------------*/

public boolean isEq( schemeobject o ) {
  return equals( o );
}

public boolean isEqv( schemeobject o ) {
  return equals( o );
}

public boolean isEqual( schemeobject o ) {
  return isEqv( o );
}

/*----------------------------------------------------------------------------*/
/* Arithmetic Operations                                                      */
/*----------------------------------------------------------------------------*/

  public schemeobject plus( schemeobject n ) throws schemeexception {
    throw new schemeexception( "schemeobject.plus : invalid operand type" );
  }

  public schemeobject times( schemeobject n ) throws schemeexception {
    throw new schemeexception( "schemeobject.times : invalid operand type" );
  }

  public schemeobject minus( schemeobject n ) throws schemeexception {
    throw new schemeexception( "schemeobject.minus : invalid operand type" );
  }

  public schemeobject divide( schemeobject n ) throws schemeexception {
    throw new schemeexception( "schemeobject.divide : invalid operand type" );
  }

  public schemeobject negative() throws schemeexception {
    throw new schemeexception( "schemeobject.negative : invalid operand type" );
  }

  public schemeobject reciprocal() throws schemeexception {
    throw new schemeexception( "schemeobject.reciprocal : invalid operand type" );
  }

}
