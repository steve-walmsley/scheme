package scheme;

import java.io.PrintStream;

public class primitivenlenvironment extends primitiveprocedure {

  protected primitivenlenvironment() {
    name = "null-environment";
  }

  public schemeobject apply( 
    schemelist argl, schemeinterpreter interpreter
  ) throws schemeexception 
  {
    if ( argl.isPair() ) {
      schemeobject version = argl.getCar();
      if ( version.isInteger() ) {
        if ( ((schemeinteger)version).integerValue() == 5 ) {
          return interpreter.nle;
        }
      }
    };
    throw new schemeexception( name + " version is not 5" );
  }

}
