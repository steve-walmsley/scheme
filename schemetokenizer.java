package scheme;

import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.IOException;

public class schemetokenizer {

  public static final int nullToken          =  0;
  public static final int unrecognizedToken  =  1;
  public static final int endOfFileToken     =  2;
  public static final int tokenIdentifier    =  3;
  public static final int tokenInteger       =  4;
  public static final int tokenReal          =  5;
  public static final int tokenString        =  6;
  public static final int tokenQuote         =  7;
  public static final int tokenDoubleQuote   =  8;
  public static final int tokenLeftBracket   =  9;
  public static final int tokenRightBracket  = 10;
  public static final int tokenDot           = 11;
  public static final int tokenHashBracket   = 12;
  public static final int tokenHashU8Bracket = 13;
  public static final int tokenTrue          = 14;
  public static final int tokenFalse         = 15;

  protected static final int stringDelimiter  = '"';
  protected static final int leftParenthesis  = '(';
  protected static final int rightParenthesis = ')';
  protected static final int singleQuote      = '\'';
  protected static final int semiColon        = ';';
  protected static final int dot              = '.';
  protected static final int minus            = '-';
  protected static final int hash             = '#';

  protected StreamTokenizer tokenizer;

  protected int tokenType = nullToken;

  protected String identifierValue;
  protected String stringValue;
  protected int    integerValue;
  protected double realValue;

  protected boolean tokenPushedBack = false;

  public schemetokenizer( Reader r ) {
    tokenizer = new StreamTokenizer( r );
    tokenizer.resetSyntax();
    tokenizer.whitespaceChars( '\u0000', '\u0020' );
    tokenizer.parseNumbers();
    tokenizer.wordChars( '\u0021', '\u007E' );
    tokenizer.quoteChar( stringDelimiter );

    tokenizer.ordinaryChar( leftParenthesis );
    tokenizer.ordinaryChar( rightParenthesis );
    tokenizer.ordinaryChar( singleQuote );
    tokenizer.ordinaryChar( dot );
    tokenizer.ordinaryChar( hash );

    tokenizer.commentChar( semiColon );
  };

  public int nextToken() {
    if ( tokenPushedBack ) {
      tokenPushedBack = false;
    } else {
      try {
        switch ( tokenizer.nextToken() ) {
          case StreamTokenizer.TT_EOF    : tokenType = endOfFileToken;    break;

          case StreamTokenizer.TT_WORD   : 
            tokenType = tokenIdentifier;   
            identifierValue = tokenizer.sval;
            break;

          case StreamTokenizer.TT_NUMBER : 
            if ( tokenizer.nval > Math.floor( tokenizer.nval ) ) {
              tokenType = tokenReal;
              realValue = tokenizer.nval;
            } else {
              tokenType = tokenInteger;
              integerValue = (int)Math.floor( tokenizer.nval );
            };
            break;

          case stringDelimiter           : 
            tokenType = tokenString;       
            stringValue = tokenizer.sval;
            break;

          case leftParenthesis           : tokenType = tokenLeftBracket;  break;
          case rightParenthesis          : tokenType = tokenRightBracket; break;
          case singleQuote               : tokenType = tokenQuote;        break;
          case dot                       : tokenType = tokenDot;          break;
          case minus                     : 
            tokenType = tokenIdentifier;   
            identifierValue = "-";
            break;

          case hash                      :
            switch ( tokenizer.nextToken() ) {
              case leftParenthesis       : tokenType = tokenHashBracket; break;

              case StreamTokenizer.TT_WORD :         
                if ( tokenizer.sval.equals( "u8" ) && ( tokenizer.nextToken() == leftParenthesis ) ) {
                  tokenType = tokenHashU8Bracket;   
                } else if ( tokenizer.sval.equals( "t" ) ) {
                  tokenType = tokenTrue;   
                } else if ( tokenizer.sval.equals( "f" ) ) {
                  tokenType = tokenFalse;   
                } else {
                  tokenType = tokenIdentifier;   
                  identifierValue = String.valueOf( (char)hash ) + tokenizer.sval;
                }
                break;
              default : 
                tokenType = unrecognizedToken; 
                break;
            }
            break;

          default                        : 
            tokenType = unrecognizedToken; 
            break;
        };
      } catch ( IOException ioe ) {
        tokenType = unrecognizedToken;
      }
    }
    return tokenType;
  }

  public void pushBack() {
    tokenPushedBack = true;
  }

  public int getTokenType() {
    return tokenType;
  }

  public String getIdentifier() {
    return identifierValue;
  }

  public String getString() {
    return stringValue;
  }

  public double getReal() {
    return realValue;
  }

  public int getInteger() {
    return integerValue;
  }


}
