package scheme;

import java.nio.charset.StandardCharsets;

public class primitiveutf8tostring extends primitivebytevectorcopy {

  protected primitiveutf8tostring() {
    name = "utf8->string";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    setupsrc( argl );
    return new schemestring( new String( src.value(), start, ( end - start + 1 ), StandardCharsets.UTF_8 ) );
  }

}
