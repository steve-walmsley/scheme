package scheme;

import java.io.PrintStream;

public class primitiveapply extends primitiveprocedure {

  protected primitiveapply() {
    name = "apply";
  }

  public schemeobject apply( 
    schemelist argl, schemeinterpreter interpreter
  ) throws schemeexception 
  {
    if ( argl.isPair() ) {
      argl = ((schemepair)argl).appendLast();
      interpreter.fun = argl.getCar();
      interpreter.argl = argl.getCdr();
      if ( interpreter.argl.isPair() ) {
        interpreter.argl = ((schemepair)interpreter.argl).reverse();
      };
      interpreter.stack.push( new schemecontinuation(
          schemecontinuation.applyDespatch
        )
      );
    };
    return schemeundefined.undefinedObject();
  }

}
