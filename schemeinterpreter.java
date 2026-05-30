package scheme;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Stack;

public class schemeinterpreter {

protected static schemeenvironment nle = new schemeenvironment();
protected static schemeenvironment sre = nle.extend();
protected static schemeenvironment tle = sre.extend();

static {
  nle.defineVariable( schemefalse.falseSymbol(), schemefalse.falseObject() );
  nle.defineVariable( schemetrue.trueSymbol(),   schemetrue.trueObject()   );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "let" ), new expandlet()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "let*" ), new expandletstar()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "letrec" ), new expandletrec()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "begin" ), new expandbegin()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "do" ), new expanddo()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "and" ), new expandand()
  );
  nle.defineVariable( 
    schemesymbol.makeSymbol( "or" ), new expandor()
  );

  primitiveprocedure.installPrimitives( sre );
}

public static void installExternal( externalprocedure external ) {
  tle.defineVariable( 
    schemesymbol.makeSymbol( external.getName() ), external
  );
}

protected boolean printResults = false;

protected schemeobject exp;
protected schemeobject unev;
protected schemeobject val;
protected schemeobject fun;
protected schemeobject argl;

protected schemeenvironment  env;
protected schemecontinuation cont;

protected Stack<schemeobject> stack = new Stack<>();

public void setPrintResults( boolean printResults ) {
  this.printResults = printResults;
}

public void eval( schemeenvironment initialEnv ) throws schemeexception {

  int pc = schemecontinuation.evalDespatch;

  cont = new schemecontinuation( schemecontinuation.printResult );
  env  = initialEnv;

  while ( pc != schemecontinuation.printResult ) {
    switch ( pc ) {
      case schemecontinuation.evalDespatch :
        if ( exp.isSelfEvaluating() ) {
          pc = schemecontinuation.evSelfEval;
        } else if ( exp.isQuoted() ) {
          pc = schemecontinuation.evQuote;
        } else if ( exp.isVariable() ) {
          pc = schemecontinuation.evVariable;
        } else if ( exp.isDefinition() ) {
          pc = schemecontinuation.evDefinition;
        } else if ( exp.isAssignment() ) {
          pc = schemecontinuation.evAssignment;
        } else if ( exp.isLambda() ) {
          pc = schemecontinuation.evLambda;
        } else if ( exp.isConditional() ) {
          pc = schemecontinuation.evCond;
        } else if ( exp.isIf() ) {
          pc = schemecontinuation.evIf;
        } else if ( exp.isNoArgs() ) {
          pc = schemecontinuation.evNoArgs;
        } else if ( exp.isApplication() ) {
          pc = schemecontinuation.evApplication;
        } else {
          throw new schemeexception( 
            "schemeinterpreter.eval unknown expression type " + exp
          );
        };
      break;
      case schemecontinuation.evSelfEval :
        val = exp;
        pc = cont.getpc();
      break;
      case schemecontinuation.evQuote :
        val = ((schemepair)((schemepair)exp).getCdr()).getCar();
        pc = cont.getpc();
      break;
      case schemecontinuation.evVariable :
        val = env.lookupVariable( (schemesymbol)exp );
        pc = cont.getpc();
      break;
      case schemecontinuation.evLambda :
        val = new compoundprocedure( (schemepair)exp, env );
        pc = cont.getpc();
      break;
      case schemecontinuation.evDefinition :
        unev = ((schemepair)exp).definitionVariable();
        exp  = ((schemepair)exp).definitionValue();
        stack.push( unev );
        stack.push( env );
        stack.push( cont );
        cont = new schemecontinuation( schemecontinuation.evDefinition1 );
        pc = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evDefinition1 :
        cont = (schemecontinuation)stack.pop();
        env  = (schemeenvironment)stack.pop();
        unev = (schemeobject)stack.pop();
        env.defineVariable( (schemesymbol)unev, val );
        val = unev;
        pc = cont.getpc();
      break;
      case schemecontinuation.evAssignment :
        unev = ((schemepair)exp).assignmentVariable();
        exp  = ((schemepair)exp).assignmentValue();
        stack.push( unev );
        stack.push( env );
        stack.push( cont );
        cont = new schemecontinuation( schemecontinuation.evAssignment1 );
        pc = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evAssignment1 :
        cont = (schemecontinuation)stack.pop();
        env  = (schemeenvironment)stack.pop();
        unev = (schemeobject)stack.pop();
        env.setVariableValue( (schemesymbol)unev, val );
        val = unev;
        pc = cont.getpc();
      break;
      case schemecontinuation.evNoArgs :
        exp  = ((schemepair)exp).operator();
        stack.push( cont );
        cont = new schemecontinuation( schemecontinuation.setupNoArgApply );
        pc = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.setupNoArgApply :
        fun  = val;
        if ( fun.isExpander() ) {
          exp = ((schemeexpander)fun).expand();
          cont = (schemecontinuation)stack.pop();
          pc = schemecontinuation.evalDespatch;
        } else {
          argl = schemenull.nullObject();
          pc = schemecontinuation.applyDespatch;
        }
      break;
      case schemecontinuation.evApplication :
        unev = ((schemepair)exp).operands();
        exp  = ((schemepair)exp).operator();
        stack.push( cont );
        stack.push( env );
        stack.push( unev );
        cont = new schemecontinuation( schemecontinuation.evalArgs );
        pc = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evalArgs :
        unev = (schemeobject)stack.pop();
        env  = (schemeenvironment)stack.pop();
        fun  = val;
        if ( fun.isExpander() ) {
          exp = ((schemeexpander)fun).expand( (schemelist)unev );
          cont = (schemecontinuation)stack.pop();
          pc = schemecontinuation.evalDespatch;
        } else {
          stack.push( fun );
          argl = schemenull.nullObject();
          pc = schemecontinuation.evalArgLoop;
        };
      break;
      case schemecontinuation.evalArgLoop :
        stack.push( argl );
        exp = ((schemepair)unev).firstOperand();
        if ( ((schemepair)unev).isLastOperand() ) {
          pc = schemecontinuation.evalLastArg;
        } else {
          stack.push( env );
          stack.push( unev );
          cont = new schemecontinuation( schemecontinuation.accumulateArgs );
          pc = schemecontinuation.evalDespatch;
        };
      break;
      case schemecontinuation.accumulateArgs :
        unev = (schemeobject)stack.pop();
        env  = (schemeenvironment)stack.pop();
        argl = (schemeobject)stack.pop();
        argl = new schemepair( val, argl );
        unev = ((schemepair)unev).restOperands();
        pc   = schemecontinuation.evalArgLoop;
      break;
      case schemecontinuation.evalLastArg :
        cont = new schemecontinuation( schemecontinuation.accumulateLastArg );
        pc   = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.accumulateLastArg :
        argl = (schemeobject)stack.pop();
        argl = new schemepair( val, argl );
        fun  = (schemeobject)stack.pop();
        pc   = schemecontinuation.applyDespatch;
      break;
      case schemecontinuation.applyDespatch :
        if ( fun.isPrimitiveProcedure() ) {
          pc   = schemecontinuation.primitiveApply;
        } else if ( fun.isCompoundProcedure() ) {
          pc   = schemecontinuation.compoundApply;
        } else if ( fun.isExternalProcedure() ) {
          pc   = schemecontinuation.externalApply;
        } else {
          pc   = schemecontinuation.unknownProcedureTypeError;
        };
      break;
      case schemecontinuation.primitiveApply :
        val  = ((primitiveprocedure)fun).apply( 
          ((schemelist)argl).reverse(), this 
        );
        cont = (schemecontinuation)stack.pop();
        pc   = cont.getpc();
      break;
      case schemecontinuation.compoundApply :
        env  = ((compoundprocedure)fun).makeBindings( ((schemelist)argl).reverse() );
        unev = ((compoundprocedure)fun).getBody();
        pc   = schemecontinuation.evalSequence;
      break;
      case schemecontinuation.externalApply :
        val  = ((externalprocedure)fun).apply( ((schemelist)argl).reverse() );
        cont = (schemecontinuation)stack.pop();
        pc   = cont.getpc();
      break;
      case schemecontinuation.evalSequence :
        exp = ((schemepair)unev).firstExp();
        if ( ((schemepair)unev).isLastExp() ) {
          pc   = schemecontinuation.lastExp;
        } else {
          stack.push( unev );
          stack.push( env );
          cont = new schemecontinuation( schemecontinuation.evalSequenceContinue );
          pc   = schemecontinuation.evalDespatch;
        };
      break;
      case schemecontinuation.evalSequenceContinue :
        env  = (schemeenvironment)stack.pop();
        unev = (schemeobject)stack.pop();
        unev = ((schemepair)unev).restExps();
        pc   = schemecontinuation.evalSequence;
      break;
      case schemecontinuation.lastExp :
        cont = (schemecontinuation)stack.pop();
        pc   = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evCond :
        stack.push( cont );
        cont = new schemecontinuation( schemecontinuation.evCondDecide );
        unev = ((schemepair)exp).clauses();
      // no break here : cf. SICP 5.2.3 p432
      case schemecontinuation.evCondPred :
        if ( unev.noClauses() ) {
          pc   = schemecontinuation.evCondReturnNil;
        } else {
          exp = ((schemepair)unev).firstClause();
          if ( ((schemepair)exp).elseClause() ) {
            pc   = schemecontinuation.evCondElseClause;
          } else {
            stack.push( env );
            stack.push( unev );
            exp = ((schemepair)exp).predicate();
            pc  = schemecontinuation.evalDespatch;
          };
        };
      break;
      case schemecontinuation.evCondReturnNil :
        cont = (schemecontinuation)stack.pop();
        val  = schemeundefined.undefinedObject();
        pc   = cont.getpc();
      break;
      case schemecontinuation.evCondDecide :
        unev = (schemeobject)stack.pop();
        env  = (schemeenvironment)stack.pop();
        if ( val.isTrue() ) {
          pc   = schemecontinuation.evCondTruePred;
        } else {
          unev = ((schemepair)unev).restClauses();
          pc   = schemecontinuation.evCondPred;
        };        
      break;
      case schemecontinuation.evCondTruePred :
        exp = ((schemepair)unev).firstClause();
      // no break here : cf. SICP 5.2.3 p433
      case schemecontinuation.evCondElseClause :
        unev = ((schemepair)exp).actions();
        pc   = schemecontinuation.evalSequence;
      break;
      case schemecontinuation.evIf :
        stack.push( exp );
        stack.push( env );
        stack.push( cont );
        cont = new schemecontinuation( schemecontinuation.evIfDecide );
        exp = ((schemepair)exp).ifPredicate();
        pc  = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evIfDecide :
        cont = (schemecontinuation)stack.pop();
        env  = (schemeenvironment)stack.pop();
        exp  = (schemeobject)stack.pop();
        if ( val.isTrue() ) {
          pc = schemecontinuation.evIfConsequent;
        } else {
          pc = schemecontinuation.evIfAlternative;
        };
      break;
      case schemecontinuation.evIfAlternative :
        exp = ((schemepair)exp).ifAlternative();
        pc  = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.evIfConsequent :
        exp = ((schemepair)exp).ifConsequent();
        pc  = schemecontinuation.evalDespatch;
      break;
      case schemecontinuation.unknownProcedureTypeError :
        throw new schemeexception( 
          "schemeinterpreter.eval unknown procedure type " + 
          fun.getClass().getName()
        );
      default :
        throw new schemeexception( 
          "schemeinterpreter.eval unexpected pc " + pc
        );
    };
  };
}

public void interpret( Reader input ) {
  schemetokenizer tokenizer = new schemetokenizer( input );

  boolean endOfFile = false;
  while( !endOfFile ) {
    try {
      exp = schemeobject.read( tokenizer );
      if ( exp instanceof schemeeof ) {
        endOfFile = true;
      } else {
        schemeobject tlexp = exp;
        try {
          eval( tle );
          if ( printResults ) {
            val.print( System.out );
            System.out.println();
          };
        } catch ( schemeexception se ) {
          System.out.println();
          System.out.println( se );
          System.out.print( "> " );
          tlexp.print( System.out );
          System.out.println();
          System.out.println();
        };
      };
    } catch ( schemeexception se ) {
      System.out.println( se );
    };
  }
}

public void interpret( InputStream input ) {
  BufferedReader r = new BufferedReader( new InputStreamReader( input )  );
  interpret( r );
  try {
    r.close();
  } catch ( IOException ioe ) {};
}

public void loadLibrary( String libraryName ) {
  InputStream library = ClassLoader.getSystemResourceAsStream( libraryName );

  if ( library != null ) {
    interpret( library );
    try {
      library.close();
    } catch ( IOException ioe ) {};
  } else {
    System.out.println( 
      "< schemeinterpreter : can't find " + libraryName + " >" 
    );
  };
}

public static void main( String[] args ) {

  schemeexternal externalModule = schemeexternal.newInstance();
  externalModule.initialise();
  externalprocedure.setModule( externalModule );

  schemeinterpreter scheme = new schemeinterpreter();

  scheme.loadLibrary( "scheme/schemelibrary.scm" );

  String applicationLibrary = System.getProperty( "scheme.applicationLibrary" );
  if ( applicationLibrary != null ) {
    scheme.loadLibrary( applicationLibrary );
  };

  scheme.setPrintResults( true );

  try {
    tle.defineVariable( 
      schemesymbol.makeSymbol( "*argv*" ), schemepair.fromArray( args )
    );
  } catch( schemelistexception sle ) {
    tle.defineVariable( 
      schemesymbol.makeSymbol( "*argv*" ), schemenull.nullObject()
    );
  };

  if ( args.length < 1 ) {

    System.out.println( "< schemeinterpreter >" );
    scheme.interpret( System.in );

  } else {
    String inputName = args[0];
    try {
      InputStream input = new FileInputStream( inputName );
      System.out.println( "< schemeinterpreter " + inputName + " >" );
      scheme.interpret( input );
      try {
        input.close();
      } catch ( IOException ioe ) {};

    } catch( FileNotFoundException fnf ) {
      System.out.println( "< schemeinterpreter failed to open " + inputName );
      System.out.println( fnf );
    };
  };

  externalModule.close();

  System.out.println( "< leaving schemeinterpreter >" );
}

}
