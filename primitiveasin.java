package scheme;

import java.io.PrintStream;

public class primitiveasin extends primitiveprocedure {

  protected primitiveasin() {
    name = "asin";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg = argl.getCar(); 
    if ( arg.isNumber() ) {
      val = new schemereal(
        Math.asin( ((schemenumber)arg).realValue() )
      );
    } else {
      throw new schemenumberexception(
        name + " : non-numeric argument"
      );
    };

    return val;
  }

}
