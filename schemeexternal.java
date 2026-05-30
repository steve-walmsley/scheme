package scheme;

import java.lang.reflect.InvocationTargetException;

public abstract class schemeexternal {

  public static schemeexternal newInstance() {
    schemeexternal externalInstance = new dummyexternal();

    String externalClassName = System.getProperty( "scheme.external" );
    if ( externalClassName != null ) {
      try {
        Class<?> externalClass = Class.forName( externalClassName );
        externalInstance = (schemeexternal)externalClass.getDeclaredConstructor().newInstance();
      } catch ( ClassNotFoundException cnf ) {
        System.out.printf( "< schemeexternal.newInstance() failed to load %s\n", externalClassName ); 
        System.out.printf( "%s\n", cnf );       
      } catch ( InstantiationException ie ) {
        System.out.printf( "< schemeexternal.newInstance() failed to instantiate %s\n", externalClassName );
        System.out.printf( "%s\n", ie );    
      } catch ( IllegalAccessException iae ) {
        System.out.printf( "< schemeexternal.newInstance() cannot access %s\n", externalClassName );
        System.out.printf( "%s\n", iae );              
      } catch ( NoSuchMethodException nsm ) {
        System.out.printf( "< schemeexternal.newInstance() noarg constructor not found %s\n", externalClassName );
        System.out.printf( "%s\n", nsm );
      } catch ( InvocationTargetException ite ) {
        System.out.printf( "< schemeexternal.newInstance() exception in constructor %s\n", externalClassName );
        System.out.printf( "%s\n", ite );
      }
    }

    return externalInstance;
  }

  protected schemeexternal() {}

  public abstract void externalinitialise();

  public void initialise() {
    externalinitialise();
  }

  public static void installExternal( int code, String name ) {
    schemeinterpreter.installExternal( new externalprocedure( name, code ) );
  }

  public abstract Object[] schemeExternalDespatch( int code, Object[] parameters );

  public Object[] externalapply( int code, Object[] args ) {

    Object[] parameters = schemeToJava( args );

    Object[] result = schemeExternalDespatch( 
      code, 
      parameters
    ); 

    Object[] value = javaToScheme( result );

    return value;
  }

  public schemeobject apply( 
    externalprocedure ext, schemelist arguments 
  ) throws schemeexception {

    Object[] value = new schemeobject[0];

    int code = ext.getCode();

    value = externalapply( code, arguments.toArray() );

    if ( value.length <= 0 ) {
      return schemenull.nullObject();
    } else if ( value.length == 1 ) {
      return schemepair.fromArray( value ).getCar();
    } else {
      return schemepair.fromArray( value );
    }
  }

  public abstract void externalfinalize();

  protected void close() {
    try {

      externalfinalize();

    } catch ( Throwable t ) {
    };
  }

/*----------------------------------------------------------------------------*/

  private Object[] schemeToJava( Object[] args ) {

    Object[] parameters = new Object[args.length];

    for( int index = 0; index < args.length; index++ ) {
      Object element = args[ index ];   

      if ( element instanceof schemeobject ) {
        
        if ( ((schemeobject)element).isInteger() ) {

          parameters[index] = ((schemeinteger)element).integerValue();
  
        } else if ( ((schemeobject)element).isReal() ) {

          parameters[index] = ((schemereal)element).realValue();

        } else if ( ((schemeobject)element).isString() ) {

          parameters[index] = ((schemestring)element).stringValue();
 
        } else if ( ((schemeobject)element).isNull() ) {

          parameters[index] = new Object[0];

        };
      } else if ( element instanceof Object[] ) {
        parameters[index] = makeExternalVector( ((Object[])element) );
      } else {
        System.out.printf( "< SCHEMEEXTERNALIMP/schemeToC unexpected class\n" );
      };
    };
    return parameters;
  }


/*----------------------------------------------------------------------------*/


  private Object[] makeExternalVector( Object[] schemeParameter ) {
    schemeobject element = (schemeobject)schemeParameter[0];
    
    if ( element.isInteger() ) {
      return makeIntegerVector( schemeParameter );
    } else if ( element.isReal() ) {
      return makeRealVector( schemeParameter );
    } else {
      return new Object[0];
    }
  }

/*----------------------------------------------------------------------------*/

  private Integer[] makeIntegerVector( Object[] schemeParameter ) {
    int len = schemeParameter.length;

    Integer[] vectorArray = new Integer[ len ];

    for( int index = 0; index < len; index++ ) {
      schemeobject element = (schemeobject)schemeParameter[index];    

      if ( element.isInteger() ) {
        vectorArray[index] =  ((schemeinteger)element).integerValue();
      };
    };

    return vectorArray;
  }

/*----------------------------------------------------------------------------*/

  private Double[] makeRealVector( Object[] schemeParameter ) {
    int len = schemeParameter.length;

    Double[] vectorArray = new Double[ len ];

    for( int index = 0; index < len; index++ ) {
      schemeobject element = (schemeobject)schemeParameter[index];    

      if ( element.isReal() ) {
        vectorArray[index] =  ((schemereal)element).realValue();
      };
    };

    return vectorArray;
  }

/*----------------------------------------------------------------------------*/

  private Object[] javaToScheme( Object[] parameters ) {

    Object[] value = new Object[parameters.length];

    for( int index = 0; index < value.length; index++ ) {

      if ( parameters[index] instanceof Integer ) {

         value[index] = new schemeinteger( ((Integer)parameters[index]) );

      } else if ( parameters[index] instanceof Double ) {

         value[index] = new schemereal( ((Double)parameters[index]) );

      } else if ( parameters[index] instanceof String ) {

         value[index] = new schemestring( ((String)parameters[index]) );

      } else if ( parameters[index] instanceof double[] ) {

         value[index] = makeSchemeRealArray( ((double[])parameters[index]) );

      } else if ( parameters[index] instanceof int[] ) {

         value[index] = makeSchemeIntegerArray( ((int[])parameters[index]) );

      } else {
      };
    };
    return value;
  }
/*----------------------------------------------------------------------------*/

  private schemeobject[] makeSchemeRealArray( double[] externalParameter ) {

    int vSize = externalParameter.length;
    schemeobject[] vector = new schemereal[vSize];

    for ( int vIndex = 0; vIndex < vSize; vIndex++ ) {
      vector[ vIndex ] = new schemereal( externalParameter[vIndex] ); 
    };

    return vector;
  }

/*----------------------------------------------------------------------------*/

  private schemeobject[] makeSchemeIntegerArray( int[] externalParameter ) {

    int vSize = externalParameter.length;
    schemeobject[] vector = new schemeinteger[vSize];

    for ( int vIndex = 0; vIndex < vSize; vIndex++ ) {
      vector[ vIndex ] = new schemeinteger( externalParameter[vIndex] ); 
    };

    return vector;
  }

/*----------------------------------------------------------------------------*/

}
