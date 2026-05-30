package scheme;

import java.io.PrintStream;

public class primitiveacos extends primitiveprocedure {

  protected primitiveacos() {
    name = "acos";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg = argl.getCar(); 
    if ( arg.isNumber() ) {
      val = new schemereal(
        Math.acos( ((schemenumber)arg).realValue() )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
