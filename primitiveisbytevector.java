package scheme;

public class primitiveisbytevector extends primitiveprocedure {

  protected primitiveisbytevector() {
    name = "bytevector?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isByteVector() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
