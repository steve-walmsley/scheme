package scheme;

import java.io.PrintStream;

import java.util.Hashtable;

public class externalprocedure extends schemeobject {

  protected static schemeexternal externalmodule;

  /*--------------------------------------------------------------------------*/
  /* externalprocedure static methods                                         */
  /*--------------------------------------------------------------------------*/

  public static void setModule( schemeexternal m ) {
    externalmodule = m;
  }

  /*--------------------------------------------------------------------------*/
  /* externalprocedure methods                                                */
  /*--------------------------------------------------------------------------*/

  protected String name;
  protected int    code;

  public externalprocedure( String name, int code ) {
    this.name = name;
    this.code = code;
  }

  public String toString() {
    return getClass().getName() + " " + name + " " + code;
  }
    
  public void print( PrintStream out ) {
    out.print( "< EXTERNAL " + getName() + " " + getCode() + " >" 
    );
  }

  public String getName() {
    return name;
  }

  public int getCode() {
    return code;
  }

  /*--------------------------------------------------------------------------*/
  /* Scheme Type Predicates                                                   */
  /*--------------------------------------------------------------------------*/

  public boolean isAtom() {
    return false;
  }

  public boolean isProcedure() {
    return true;
  }

  /*--------------------------------------------------------------------------*/
  /* Syntax Predicates                                                        */
  /*--------------------------------------------------------------------------*/

  public boolean isExternalProcedure() {
    return true;
  }

  /*--------------------------------------------------------------------------*/
  /* External Application                                                     */
  /*--------------------------------------------------------------------------*/

  public schemeobject apply( schemelist arguments ) throws schemeexception {
    return externalmodule.apply( this, arguments );
  }

}
