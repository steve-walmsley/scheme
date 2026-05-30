package scheme;

import java.io.PrintStream;

public class primitiveexpt extends primitiveprocedure {

  protected primitiveexpt() {
    name = "expt";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg1 = argl.getCar(); 
    schemeobject arg2 = argl.getCadr(); 
    if ( arg1.isNumber() && arg2.isNumber() ) {
      val = new schemereal(
        Math.pow( 
          ((schemenumber)arg1).realValue(), 
          ((schemenumber)arg2).realValue()  
        )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
