package scheme;

import java.io.PrintStream;

public class schemestring extends schemeobject {

  protected String stringValue;

  public static schemeobject read( schemetokenizer tokenizer ) 
    throws schemeexception 
  {
    return new schemestring( tokenizer.getString() );
  }

  public schemestring( String value ) {
    stringValue = value;
  }

  public String stringValue() {
    return this.stringValue;
  }

  public String toString() {
    return "\"" + stringValue + "\"";
  }

  public void print( PrintStream out ) {
    out.print( this.toString() );
  }

  public void display( PrintStream out ) {
    out.print( stringValue );
  }

  public boolean isString() {
    return true;
  };

  public boolean isSelfEvaluating() {
    return true;
  };

  public boolean isEqual( schemeobject o ) {
    if ( o instanceof schemestring ) {
      schemestring s = (schemestring)o;
      return stringValue.equals( s.stringValue() );
    } else {
      return super.isEqv( o );
    }
  }

  public schemestring append( schemestring s ) {
    return new schemestring( stringValue + s.stringValue() );
  }

}
