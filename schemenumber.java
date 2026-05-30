package scheme;

public abstract class schemenumber extends schemeobject {

  public int integerValue() throws schemenumberexception {
    throw new schemenumberexception( 
      "schemenumber.integerValue : not an integer"
    );
  };
  
  public double realValue() throws schemenumberexception {
    throw new schemenumberexception( 
      "schemenumber.realValue : not a real"
    );
  };

  public abstract boolean isLessThan( schemenumber n );

  public abstract boolean isEqualTo( schemenumber n );

  public abstract boolean isGreaterThan( schemenumber n );

  public abstract boolean isLessEqual( schemenumber n );

  public abstract boolean isGreaterEqual( schemenumber n );

  public boolean isEqv( schemeobject o ) {
    if ( o instanceof schemenumber ) {
      schemenumber n = (schemenumber)o;
      return isEqualTo( n );
    } else {
      return super.isEqv( o );
    }
  }

}
