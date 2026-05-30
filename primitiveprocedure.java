package scheme;

import java.io.PrintStream;

import java.util.Hashtable;

public class primitiveprocedure extends schemeobject {

  /*--------------------------------------------------------------------------*/
  /* primitiveprocedure static methods                                        */
  /*--------------------------------------------------------------------------*/

  public static void installPrimitive( 
    primitiveprocedure primitive, schemeenvironment env 
  ) {
    env.defineVariable( 
      schemesymbol.makeSymbol( primitive.getName() ), primitive 
    );
  }

  public static void installPrimitives( schemeenvironment env ) {
    installPrimitive( new primitiveispair(),         env );
    installPrimitive( new primitivecons(),           env );
    installPrimitive( new primitivecar(),            env );
    installPrimitive( new primitivecdr(),            env );
    installPrimitive( new primitivesetcar(),         env );
    installPrimitive( new primitivesetcdr(),         env );
    installPrimitive( new primitiveisnull(),         env );
    installPrimitive( new primitiveislist(),         env );
    installPrimitive( new primitivelist(),           env );
    installPrimitive( new primitivelength(),         env );
    installPrimitive( new primitiveappend(),         env );
    installPrimitive( new primitivereverse(),        env );
    installPrimitive( new primitivelistref(),        env );
    installPrimitive( new primitivelisttail(),       env );

    installPrimitive( new primitiveissymbol(),       env );
    installPrimitive( new primitivestringtosymbol(), env );

    installPrimitive( new primitiveplus(),           env );
    installPrimitive( new primitivetimes(),          env );
    installPrimitive( new primitiveminus(),          env );
    installPrimitive( new primitivedivide(),         env );
    installPrimitive( new primitivequotient(),       env );
    installPrimitive( new primitiveremainder(),      env );
    installPrimitive( new primitivemodulo(),         env );
    installPrimitive( new primitivestringtonumber(), env );
    installPrimitive( new primitivecos(),            env );
    installPrimitive( new primitivesin(),            env );
    installPrimitive( new primitivetan(),            env );
    installPrimitive( new primitiveasin(),           env );
    installPrimitive( new primitiveacos(),           env );
    installPrimitive( new primitiveatan(),           env );

    installPrimitive( new primitivemin(),            env );
    installPrimitive( new primitivemax(),            env );

    installPrimitive( new primitivesqrt(),           env );
    installPrimitive( new primitiveexpt(),           env );

    installPrimitive( new primitiveless(),           env );
    installPrimitive( new primitivelessequal(),      env );
    installPrimitive( new primitiveequal(),          env );
    installPrimitive( new primitivegreaterequal(),   env );
    installPrimitive( new primitivegreater(),        env );

    installPrimitive( new primitivewrite(),          env );
    installPrimitive( new primitivenewline(),        env );

    installPrimitive( new primitiveisnumber(),       env );
    installPrimitive( new primitiveiscomplex(),      env );
    installPrimitive( new primitiveisreal(),         env );
    installPrimitive( new primitiveisrational(),     env );
    installPrimitive( new primitiveisinteger(),      env );

    installPrimitive( new primitiveiseq(),           env );
    installPrimitive( new primitiveiseqv(),          env );
    installPrimitive( new primitiveisequal(),        env );

    installPrimitive( new primitivenot(),            env );

    installPrimitive( new primitiveisvector(),       env );
    installPrimitive( new primitivemakevector(),     env );
    installPrimitive( new primitivevectorlength(),   env );
    installPrimitive( new primitivevectorref(),      env );
    installPrimitive( new primitivevectorset(),      env );

    installPrimitive( new primitiveisbytevector(),     env );
    installPrimitive( new primitivemakebytevector(),   env );
    installPrimitive( new primitivebytevectorlength(), env );
    installPrimitive( new primitivebytevectorref(),    env );
    installPrimitive( new primitivebytevectorset(),    env );
    installPrimitive( new primitivebytevectorcopy(),   env );
    installPrimitive( new primitivebytevectorcopyto(), env );
    installPrimitive( new primitivebytevectorappend(), env );
    installPrimitive( new primitiveutf8tostring(),     env );
    installPrimitive( new primitivestringtoutf8(),     env );

    installPrimitive( new primitivestringappend(),     env );

    installPrimitive( new primitiveload(),           env );

    installPrimitive( new primitiveruntime(),        env );
    installPrimitive( new primitivedisplay(),        env );

    installPrimitive( new primitiveisprocedure(),    env );
    installPrimitive( new primitiveapply(),          env );

    installPrimitive( new primitiveeval(),           env );
    installPrimitive( new primitiveinteractionenvironment(), env );
    installPrimitive( new primitivesrenvironment(),          env );
    installPrimitive( new primitivenlenvironment(),          env );
  }

  /*--------------------------------------------------------------------------*/
  /* primitiveprocedure methods                                               */
  /*--------------------------------------------------------------------------*/

  protected String name;

  public primitiveprocedure() {
    name = "undefined primitive";
  }

  public void print( PrintStream out ) {
    out.print( "< PRIMITIVE " + getName() + " >" 
    );
  }

  public String getName() {
    return name;
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

  public boolean isPrimitiveProcedure() {
    return true;
  }

  /*--------------------------------------------------------------------------*/
  /* Primitive Application                                                    */
  /*--------------------------------------------------------------------------*/

  protected boolean booleanOperation( schemeobject arg1, schemeobject arg2 ) 
    throws schemeexception 
  {
    throw new schemeexception( 
      "primitiveprocedure.booleanOperation not implemented " + this.name
    );
  }

  protected schemeobject arithmeticOperation( 
    schemeobject arg1, schemeobject arg2 
  ) 
    throws schemeexception 
  {
    throw new schemeexception( 
      "primitiveprocedure.arithmeticOperation not implemented " + this.name
    );
  }

  protected schemeobject orderPrimitive( schemelist argl ) 
    throws schemeexception 
  {
    schemeobject val = schemefalse.falseObject();
    if ( !argl.isNull() ) {
      schemeobject first = argl.getCar();
      if ( !first.isNumber() ) {
        throw new schemeexception( 
          "primitiveprocedure.orderPrimitive non-numeric argument" + 
          first
        );
      };
      argl = (schemelist)argl.getCdr();

      boolean testPassed = true;
      schemeobject next;

      while ( !argl.isNull() && testPassed ) {
        next = argl.getCar();
        if ( !next.isNumber() ) {
          throw new schemeexception( 
            "primitiveprocedure.orderPrimitive non-numeric argument" + 
            next
          );
        };
        testPassed = booleanOperation( first, next );
        if ( testPassed ) {
          first = next;
          argl = (schemelist)argl.getCdr();
        }
      };
      if ( testPassed ) {
        val = schemetrue.trueObject();
      };    
    };
    return val;
  }

  protected schemeobject accumulatePrimitive( 
    schemeobject initialValue, schemelist argl 
  ) throws schemeexception {
    schemeobject val = initialValue;

    while ( !argl.isNull() ) {
      schemeobject first = argl.getCar();
      val = arithmeticOperation( val, first );
      argl = (schemelist)argl.getCdr();
    };
    return val;
  }

  public schemeobject apply( schemelist arguments ) throws schemeexception {
    throw new schemeexception( 
      "primitiveprocedure.apply unrecognized primitive" 
    );
  }

  public schemeobject apply( 
    schemelist arguments, schemeinterpreter interpreter
  ) throws schemeexception 
  {
    return apply( arguments );
  }

}
