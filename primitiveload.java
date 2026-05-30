package scheme;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class primitiveload extends primitiveprocedure {

  protected primitiveload() {
    name = "load";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.getCar().isString() ) {

      String fileName = ((schemestring)argl.getCar()).stringValue();
      try {

        Reader input = new BufferedReader( new FileReader( fileName ) );
        new schemeinterpreter().interpret( input );
        input.close();

        return schemenull.nullObject();

      } catch( FileNotFoundException fnf ) {
        throw new schemeexception( 
          "< scheme.primitiveload failed to open " + fileName 
        );
      } catch( IOException ioe ) {
        throw new schemeexception( 
          "< scheme.primitiveload error closing " + fileName 
        );
      }

    } else {
      throw new schemeexception( 
        "scheme.primitiveload.apply : invalid argument type"
      );
    }
  }

}
