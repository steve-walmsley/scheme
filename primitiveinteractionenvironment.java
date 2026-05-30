package scheme;

import java.io.PrintStream;

public class primitiveinteractionenvironment extends primitiveprocedure {

  protected primitiveinteractionenvironment() {
    name = "interaction-environment";
  }

  public schemeobject apply( 
    schemelist argl, schemeinterpreter interpreter
  ) throws schemeexception 
  {
    return interpreter.env;
  }

}
