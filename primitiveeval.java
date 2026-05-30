package scheme;

import java.io.PrintStream;

public class primitiveeval extends primitiveprocedure {

  protected primitiveeval() {
    name = "eval";
  }

  public schemeobject apply( 
    schemelist argl, schemeinterpreter interpreter
  ) throws schemeexception 
  {
    schemeobject expression = argl.getCar();
    interpreter.exp = expression;
    schemeobject environment = argl.getCadr();
    if ( environment.isEnvironment() ) {
      interpreter.eval( (schemeenvironment)environment );
    } else {
      interpreter.eval( interpreter.tle );
    };
    return interpreter.val;
  }

}
