package scheme;

import java.io.PrintStream;

public class schemenull extends schemelist {

  private static schemenull nullObject = null;

  public static schemenull nullObject() {
    if ( nullObject == null ) {
      nullObject = new schemenull();
    };
    return nullObject;
  }

  private schemenull() {
  }

  public void print( PrintStream out ) {
    out.print( "()" );
  }

  public boolean isSelfEvaluating() {
    return true;
  };

  public boolean isNull() {
    return true;
  }

  public boolean isList() {
    return true;
  }

  public schemeobject getCar() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCar" );
  };

  public schemeobject getCdr() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCdr" );
  };

  public schemeobject getCaar() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCaar" );
  };

  public schemeobject getCadr() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCadr" );
  };

  public schemeobject getCdar() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCdar" );
  };

  public schemeobject getCddr() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCddr" );
  };

  public schemeobject getCaddr() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCaddr" );
  };

  public schemeobject getCadddr() throws schemelistexception {
    throw new schemelistexception( "schemenull.getCadddr" );
  };

  public schemelist copy() {
    return this;    
  }

  public schemeobject append( schemeobject o ) throws schemelistexception {
    return o;
  }

  public schemelist reverse() {
    return this;    
  }

  public int length() {
    return 0;
  }

  public schemeobject listtail( int k ) throws schemelistexception {
    if ( k == 0 ) {
      return this;
    } else {
      throw new schemelistexception( 
        "list-tail : index " + k + " is out of range"
      );
    }
  }

  public Object[] toArray() {
    return new schemeobject[0];
  }
}
