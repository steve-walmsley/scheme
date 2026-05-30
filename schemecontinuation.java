package scheme;

import java.io.PrintStream;

public class schemecontinuation extends schemeobject {

  public static final int evalDespatch            =  0;
  public static final int evSelfEval              =  1;
  public static final int evQuote                 =  2;
  public static final int evVariable              =  3;
  public static final int evLet                   =  4;
  public static final int evLambda                =  5;
  public static final int evNoArgs                =  6;
  public static final int evApplication           =  7;
  public static final int setupNoArgApply         =  8;
  public static final int evalArgs                =  9;
  public static final int evalArgLoop             = 10;
  public static final int accumulateArgs          = 11;
  public static final int evalLastArg             = 12;
  public static final int accumulateLastArg       = 13;
  public static final int applyDespatch           = 14;
  public static final int primitiveApply          = 15;
  public static final int applyPrimitiveProcedure = 16;
  public static final int compoundApply           = 17;
  public static final int evalSequence            = 18;
  public static final int evalSequenceContinue    = 19;
  public static final int lastExp                 = 20;
  public static final int evCond                  = 21;
  public static final int evCondPred              = 22;
  public static final int evCondReturnNil         = 23;
  public static final int evCondDecide            = 24;
  public static final int evCondTruePred          = 25;
  public static final int evCondElseClause        = 26;

  public static final int evAssignment            = 27;
  public static final int evAssignment1           = 28;
  public static final int evDefinition            = 29;
  public static final int evDefinition1           = 30;

  public static final int printResult             = 31;

  public static final int unknownProcedureTypeError = 32;

  public static final int externalApply           = 33;

  public static final int evIf                    = 34;
  public static final int evIfDecide              = 35;
  public static final int evIfAlternative         = 36;
  public static final int evIfConsequent          = 37;

  protected int pc;

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    return new schemecontinuation(-1);
  }

  protected schemecontinuation( int pc ) {
    this.pc = pc;
  }

  public int getpc() {
    return pc;
  }

  public void print( PrintStream out ) {
    out.print( "< CONTINUATION PC = " + pc + " >" );
  }

}
