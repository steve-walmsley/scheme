package scheme;

import java.io.PrintStream;

public class primitivesin extends primitiveprocedure {

  protected primitivesin() {
    name = "sin";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg = argl.getCar(); 
    if ( arg.isNumber() ) {
      val = new schemereal(
        Math.sin( ((schemenumber)arg).realValue() )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
