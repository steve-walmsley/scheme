package scheme;

import java.io.PrintStream;

public class primitivetan extends primitiveprocedure {

  protected primitivetan() {
    name = "tan";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg = argl.getCar(); 
    if ( arg.isNumber() ) {
      val = new schemereal(
        Math.tan( ((schemenumber)arg).realValue() )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
