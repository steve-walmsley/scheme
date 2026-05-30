
package scheme;

import java.io.PrintStream;

public class primitiveatan extends primitiveprocedure {

  protected primitiveatan() {
    name = "atan";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.length() > 1 ) {
      schemeobject arg1 = argl.getCar(); 
      schemeobject arg2 = argl.getCadr();
      if ( arg1.isNumber() && arg2.isNumber() ) {
        return new schemereal(
          Math.atan2( 
            ((schemenumber)arg1).realValue(),
            ((schemenumber)arg2).realValue() 
          )
        );
      } else {
        throw new schemenumberexception(
          name + " : non-numeric argument"
        );
      }
    } else {
      schemeobject arg = argl.getCar(); 
      if ( arg.isNumber() ) {
        return new schemereal(
          Math.atan( ((schemenumber)arg).realValue() )
        );
      } else {
        throw new schemenumberexception(
          name + " : non-numeric argument"
        );
      }
    }
  }

}
