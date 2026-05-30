package scheme;

import java.io.PrintStream;

public class primitivesqrt extends primitiveprocedure {

  protected primitivesqrt() {
    name = "sqrt";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg = argl.getCar(); 
    if ( arg.isNumber() ) {
      val = new schemereal(
        Math.sqrt( ((schemenumber)arg).realValue() )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
