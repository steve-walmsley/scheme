package scheme;

import java.io.PrintStream;

public class schemepair extends schemelist {

protected final static schemesymbol defineSymbol = 
  schemesymbol.makeSymbol( "define" );

protected final static schemesymbol setSymbol = 
  schemesymbol.makeSymbol( "set!" );

protected final static schemesymbol lambdaSymbol = 
  schemesymbol.makeSymbol( "lambda" );

protected final static schemesymbol condSymbol = 
  schemesymbol.makeSymbol( "cond" );

protected final static schemesymbol elseSymbol = 
  schemesymbol.makeSymbol( "else" );

protected final static schemesymbol ifSymbol = 
  schemesymbol.makeSymbol( "if" );

protected schemeobject car = schemenull.nullObject();
protected schemeobject cdr = schemenull.nullObject();

protected schemepair( schemeobject car, schemeobject cdr ) {
  this.car = car;
  this.cdr = cdr;
}

protected schemepair( schemeobject car ) {
  this.car = car;
}

protected boolean endOfList() {
  boolean endOfList = true;
  if ( cdr.isPair() ) {
    endOfList = false;
  };
  return endOfList;
}

public Iterable<schemeobject> elements() {
  return new listiterable( this );
}

/*----------------------------------------------------------------------------*/
/* Primitive Operations                                                       */
/*----------------------------------------------------------------------------*/

public void setCar( schemeobject car ) {
  this.car = car;
}

public void setCdr( schemeobject cdr ) {
  this.cdr = cdr;
}

public schemeobject getCar() {
  return car;
}

public schemeobject getCdr() {
  return cdr;
}

public schemeobject getCaar() {
  if ( car.isPair() ) {
    return ((schemepair)car).getCar();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCadr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCar();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCdar() {
  if ( car.isPair() ) {
    return ((schemepair)car).getCdr();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCddr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCdr();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCaadr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCaar();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCaddr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCadr();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCdadr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCdar();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCdddr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCddr();
  } else {
    return schemenull.nullObject();
  }
}

public schemeobject getCadddr() {
  if ( cdr.isPair() ) {
    return ((schemepair)cdr).getCaddr();
  } else {
    return schemenull.nullObject();
  }
}

public schemelist copy() throws schemelistexception {
  if ( !cdr.isPair() ) {
    return new schemepair( car );
  } else {
    schemepair newp = new schemepair( car );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar() ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }
}

public schemeobject append( schemeobject o ) throws schemelistexception {
  if ( !cdr.isPair() ) {
    cdr = o;
  } else {
    schemelist next = (schemelist)cdr;

    while( next.getCdr().isPair() ) {
      next = (schemelist)next.getCdr();
    };
    ((schemepair)next).setCdr( o );
  };
  return this;
}

public schemepair appendLast() throws schemelistexception {
  if ( cdr.isPair() ) {
    schemelist prev = this;
    schemelist next = (schemelist)cdr;

    while( next.getCdr().isPair() ) {
      prev = next;
      next = (schemelist)next.getCdr();
    };
    if ( next.getCar().isPair() ) {
      ((schemepair)prev).setCdr( next.getCar() );
    };
  };
  return this;
}

public schemelist reverse() throws schemelistexception {
  if ( !cdr.isPair() ) {
    return this;
  } else {
    schemelist revl = new schemepair( car );
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      revl = new schemepair( next.getCar(), revl );
      next = (schemelist)next.getCdr();
    };
    return revl;
  }
}

public int length() throws schemelistexception {
  int l = 1;    
  if ( cdr.isPair() ) {
    for( 
      schemelist next = (schemelist)cdr;
      next.isPair();
      next = (schemelist)next.getCdr()
    ) {
      l = l + 1;
    };
  };
  return l;
}

public schemeobject listref( int k ) throws schemelistexception {
  if ( k < 0 ) {
    throw new schemelistexception( 
      "list-ref : index " + k + " is out of range"
    );
  } else {
    if ( k == 0 ) {
      return car;
    } else {
      schemeobject next = cdr;
      int l = 1;
      while ( next.isPair() && ( l < k ) ) {
        next = ((schemelist)next).getCdr();
        l = l + 1;
      };
      if ( ( l < k ) || next.isNull() ){
        throw new schemelistexception( 
          "list-ref : index " + k + " is out of range"
        );
      } else {
        if ( next.isPair() ) {
          return ((schemelist)next).getCar();
        } else {
          return next;
        }
      }
    }
  }
}

public schemeobject listtail( int k ) throws schemelistexception {
  if ( k < 0 ) {
    throw new schemelistexception( 
      "list-tail : index " + k + " is out of range"
    );
  } else {
    if ( k == 0 ) {
      return this;
    } else {
      schemeobject next = cdr;
      int l = 1;
      while ( next.isPair() && ( l < k ) ) {
        next = ((schemelist)next).getCdr();
        l = l + 1;
      };
      if ( l < k ) {
        throw new schemelistexception( 
          "list-tail : index " + k + " is out of range"
        );
      } else {
        return next;
      }
    }
  }
}

/*----------------------------------------------------------------------------*/
/* Eqivalence Predicates                                                      */
/*----------------------------------------------------------------------------*/

public boolean isEqual( schemeobject o ) {
  if ( o.isPair() ) {
    schemepair p = (schemepair)o;
    try {

      if ( length() == p.length() ) {
        int l = length();

        schemepair q = this;
        schemepair r = p;

        boolean equal = true;        
        for ( int i = 0; i < l; i++ ) { 
          equal = equal && ( q.getCar().isEqual( r.getCar() ) );
          if ( ( i + 1 ) < l ) { 
            q = (schemepair)q.getCdr();
            r = (schemepair)r.getCdr();
          }
        }
        return equal;

      } else {
        return false;
      }

    } catch ( schemelistexception sle ) {
      return false;
    }
  } else {
    return isEqv( o );
  }
}


/*----------------------------------------------------------------------------*/
/* External Procedure Support                                                 */
/*----------------------------------------------------------------------------*/

public Object[] toArray() throws schemelistexception {
  int l = length();
  Object[] a = new Object[l];

  if ( car.isPair() ) {
    a[0] = ((schemepair)car).toArray();
  } else {
    a[0] = car;
  };

  schemeobject nextCar;
  int i = 1;
  if ( cdr.isPair() ) {
    for( 
      schemelist next = (schemelist)cdr;
      next.isPair();
      next = (schemelist)next.getCdr()
    ) {
      nextCar = next.getCar();
      if ( nextCar.isPair() ) {
        a[i] = ((schemepair)nextCar).toArray();
      } else {
        a[i] = nextCar;
      };
      i = i + 1;
    };
  };
  return a;
}

protected static schemelist fromArray( Object[] a ) throws schemelistexception {
  int l = a.length;

  if ( a.length <= 0 ) {
    return schemenull.nullObject();
  } else {
    schemepair newp;
    if ( a[0] instanceof schemeobject ) {
      newp = new schemepair( ((schemeobject)a[0]) );
    } else if ( a[0] instanceof String ) {
      newp = new schemepair( new schemestring((String)a[0]) );
    } else if ( a[0] instanceof schemeobject[] ) {
      newp = new schemepair( fromArray( ((Object[])a[0]) ) );
    } else {
      newp = new schemepair( schemenull.nullObject() );
    };
    schemepair newl = newp;
    schemepair next;
    for( int i = 1; i < l; i++ ) {
      if ( a[i] instanceof schemeobject ) {
        next = new schemepair( ((schemeobject)a[i]) );
      } else if ( a[i] instanceof String ) {
        next = new schemepair( new schemestring((String)a[i]) );
      } else if ( a[i] instanceof schemeobject[] ) {
        next = new schemepair( fromArray( ((Object[])a[i]) ) );
      } else {
        next = new schemepair( schemenull.nullObject() );
      };
      newp.setCdr( next );
      newp = next;
    };
    return newl;
  }
}

/*----------------------------------------------------------------------------*/
/* Interpreter routines                                                       */
/*----------------------------------------------------------------------------*/

public static schemeobject read( schemetokenizer tokenizer ) 
  throws schemeexception 
{
  schemeobject expression = schemenull.nullObject();
  schemepair   listTail = null;
  schemepair   newPair  = null; 
  
  boolean endOfList = false;

  int token = tokenizer.nextToken();
  switch ( token ) {
    case schemetokenizer.endOfFileToken :
      expression = new schemeeof();
      endOfList = true;
      break;
    case schemetokenizer.tokenRightBracket :
      expression = schemenull.nullObject();
      endOfList = true;
      break;
    case schemetokenizer.tokenLeftBracket :
      expression = new schemepair( schemepair.read( tokenizer ) );
      listTail = (schemepair)expression;
      break;
    default :
      tokenizer.pushBack();
      expression = new schemepair( schemeobject.read( tokenizer ) );
      listTail = (schemepair)expression;
      break;
  };
  while( !endOfList ) {
    token = tokenizer.nextToken();
    switch ( token ) {
      case schemetokenizer.endOfFileToken :
        endOfList = true;
        break;
      case schemetokenizer.tokenRightBracket :
        endOfList = true;
        break;
      case schemetokenizer.tokenLeftBracket :
        newPair = new schemepair( schemepair.read( tokenizer ) );
        listTail.setCdr( newPair );
        listTail = newPair;
        break;
      case schemetokenizer.tokenDot :
        listTail.setCdr( schemeobject.read( tokenizer )  );
        break;
      default :
        tokenizer.pushBack();
        newPair = new schemepair( schemeobject.read( tokenizer ) );
        listTail.setCdr( newPair );
        listTail = newPair;
        break;
    };
  };
  return expression;
}

protected void printElement( PrintStream out ) {
  out.print( " " );
  car.print( out );
  if ( !cdr.isNull() ) {
    if ( cdr.isAtom() ) {
      out.print( " . " );
      cdr.print( out );
    } else {
      ((schemepair)cdr).printElement( out );
    };
  };
};

public void print( PrintStream out ) {
  out.print( "(" );
  printElement( out );
  out.print( " )" );
};

/*----------------------------------------------------------------------------*/
/* Scheme Type Predicates                                                     */
/*----------------------------------------------------------------------------*/

public boolean isAtom() {
  return false;
}

public boolean isPair() {
  return true;
}

public boolean isList() {
  if ( cdr.isNull() ) {
    return true;
  } else if ( cdr.isPair() ) {
    return cdr.isList();
  } else {
    return false;
  }
}

/*----------------------------------------------------------------------------*/
/* Evaluator Predicates                                                       */
/*----------------------------------------------------------------------------*/

public boolean isSelfEvaluating() {
  return false;
};

public boolean isQuoted() {
  boolean quoted = false;
  if ( car == quoteSymbol ) {
    if ( !endOfList() ) {
      quoted = true;
    }
  };
  return quoted;
}

public boolean isDefinition() {
  boolean definition = false;
  if ( car == defineSymbol ) {
    if ( !getCaddr().isNull() ) {
      definition = true;
    }
  };
  return definition;
}

public schemesymbol definitionVariable() {
  if ( getCadr().isVariable() ) {
    return (schemesymbol)getCadr();
  } else {
    return (schemesymbol)getCaadr();
  }
}

public schemeobject definitionValue() {
  if ( getCadr().isVariable() ) {
    return getCaddr();
  } else {
    return new schemepair(
      lambdaSymbol,
      new schemepair( getCdadr(), getCddr() )
    );
  }
}

public boolean isAssignment() {
  boolean assignment = false;
  if ( car == setSymbol ) {
    assignment = true;
  };
  return assignment;
}

public schemesymbol assignmentVariable() {
  return (schemesymbol)getCadr();
}

public schemeobject assignmentValue() {
  return getCaddr();
}

public boolean isLambda() {
  boolean lambda = false;
  if ( car == lambdaSymbol ) {
    if ( !endOfList() ) {
      lambda = true;
    }
  };
  return lambda;
}

public boolean isConditional() {
  boolean conditional = false;
  if ( car == condSymbol ) {
    conditional = true;
  };
  return conditional;
}

public schemeobject clauses() {
  return getCdr();
}

public boolean noClauses() {
  return false;
}

public schemeobject firstClause() {
  return getCar();
}

public schemeobject restClauses() {
  return getCdr();
}

public schemeobject predicate() {
  return getCar();
}

public schemeobject actions() {
  return getCdr();
}

public boolean elseClause() {
  boolean elseClause = false;
  if ( predicate() == elseSymbol ) {
    elseClause = true;
  };
  return elseClause;
}

public boolean isNoArgs() {
  return endOfList();
}

public boolean isApplication() {
  return !endOfList();
}

public schemeobject operator() {
  return getCar();
}

public schemeobject operands() {
  return getCdr();
}

public schemeobject firstOperand() {
  return getCar();
}

public schemeobject restOperands() {
  return getCdr();
}

public boolean isLastOperand() {
  return cdr.isNull();
}

public schemeobject firstExp() {
  return getCar();
}

public schemeobject restExps() {
  return getCdr();
}

public boolean isLastExp() {
  return cdr.isNull();
}

public boolean isIf() {
  boolean ifexpression = false;
  if ( car == ifSymbol ) {
    ifexpression = true;
  };
  return ifexpression;
}

public schemeobject ifPredicate() {
  return getCadr();
}

public schemeobject ifConsequent() {
  return getCaddr();
}

public schemeobject ifAlternative() {
  if ( !getCdddr().isNull() ) {
    return getCadddr();
  } else {
    return schemenull.nullObject();
  }
}

/*----------------------------------------------------------------------------*/
/* Vector Arithmetic                                                          */
/*----------------------------------------------------------------------------*/

  protected schemeobject plus( schemenumber n ) throws schemeexception {
    schemepair newp = new schemepair( car.plus( n ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().plus( n ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  protected schemeobject plus( schemelist n ) throws schemeexception {
    schemepair newp = new schemepair( car.plus( n.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;
    n = (schemelist)n.getCdr();

    while( next.isPair() && n.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().plus( n.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
      n = (schemelist)n.getCdr();
    };
    return newl;
  }

  public schemeobject plus( schemeobject n ) throws schemeexception {
    if ( n.isNumber() ) {
      return plus( (schemenumber)n );
    } else if ( n.isPair() ) {
      return plus( (schemepair)n );
    } else {
      throw new schemeexception( "schemepair.plus : invalid argument type" );
    }
  }

  protected schemeobject times( schemenumber n ) throws schemeexception {
    schemepair newp = new schemepair( car.times( n ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().times( n ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  protected schemeobject times( schemelist n ) throws schemeexception {
    schemepair newp = new schemepair( car.times( n.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;
    n = (schemelist)n.getCdr();

    while( next.isPair() && n.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().times( n.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
      n = (schemelist)n.getCdr();
    };
    return newl;
  }

  public schemeobject times( schemeobject n ) throws schemeexception {
    if ( n.isNumber() ) {
      return times( (schemenumber)n );
    } else if ( n.isPair() ) {
      return times( (schemepair)n );
    } else {
      throw new schemeexception( "schemepair.times : invalid argument type" );
    }
  }

  protected schemeobject minus( schemenumber n ) throws schemeexception {
    schemepair newp = new schemepair( car.minus( n ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().minus( n ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  protected schemeobject minus( schemelist n ) throws schemeexception {
    schemepair newp = new schemepair( car.minus( n.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;
    n = (schemelist)n.getCdr();

    while( next.isPair() && n.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().minus( n.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
      n = (schemelist)n.getCdr();
    };
    return newl;
  }

  public schemeobject minus( schemeobject n ) throws schemeexception {
    if ( n.isNumber() ) {
      return minus( (schemenumber)n );
    } else if ( n.isPair() ) {
      return minus( (schemepair)n );
    } else {
      throw new schemeexception( "schemepair.minus : invalid argument type" );
    }
  }


  protected schemeobject divide( schemenumber n ) throws schemeexception {
    schemepair newp = new schemepair( car.divide( n ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().divide( n ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  protected schemeobject divide( schemelist n ) throws schemeexception {
    schemepair newp = new schemepair( car.divide( n.getCar() ) );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;
    n = (schemelist)n.getCdr();

    while( next.isPair() && n.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().divide( n.getCar() ) ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
      n = (schemelist)n.getCdr();
    };
    return newl;
  }

  public schemeobject divide( schemeobject n ) throws schemeexception {
    if ( n.isNumber() ) {
      return divide( (schemenumber)n );
    } else if ( n.isPair() ) {
      return divide( (schemepair)n );
    } else {
      throw new schemeexception( "schemepair.divide : invalid argument type" );
    }
  }


  public schemeobject negative() throws schemeexception {
    schemepair newp = new schemepair( car.negative() );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().negative() ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }

  public schemeobject reciprocal() throws schemeexception {
    schemepair newp = new schemepair( car.reciprocal() );
    schemepair newl = newp;
    schemelist next = (schemelist)cdr;

    while( next.isPair() ) {
      newp.setCdr( new schemepair( next.getCar().reciprocal() ) );
      newp = (schemepair)newp.getCdr();
      next = (schemelist)next.getCdr();
    };
    return newl;
  }


}
