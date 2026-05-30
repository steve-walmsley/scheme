package scheme;

public class primitivevectorlength extends primitiveprocedure {

  protected primitivevectorlength() {
    name = "vector-length";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject v = argl.getCar();
    if ( v.isVector() ) {
      return new schemeinteger( ((schemevector)v).length() );
    } else {
      throw new schemebytevectorexception(
        name + " : argument is not a vector"
      );
    }
  }

}
