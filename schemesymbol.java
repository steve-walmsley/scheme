package scheme;

import java.io.PrintStream;

import java.util.Hashtable;

public class schemesymbol extends schemeobject {

  protected static Hashtable<String,schemesymbol> symbolTable = new Hashtable<>();

  protected String symbolValue;

  public static schemesymbol makeSymbol( String identifier ) {
    String symbolName = identifier.toLowerCase();
    schemesymbol symbol = (schemesymbol)symbolTable.get( symbolName );
    if ( symbol == null ) {
      symbol = new schemesymbol( symbolName );
      symbolTable.put( symbolName, symbol );
    };
    return symbol;
  }

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    String identifier = tokenizer.getIdentifier();
    schemeobject symbol = makeSymbol( identifier );
    return symbol;
  }

  protected schemesymbol( String value ) {
    symbolValue = value;
  }

  public void print( PrintStream out ) {
    out.print( symbolValue );
  }

  public boolean isSymbol() {
    return true;
  }

  public boolean isVariable() {
    return true;
  }

  public String toString() {
    return symbolValue;
  }
}
