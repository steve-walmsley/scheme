package scheme;

public class expanddo extends schemeexpander {

  static schemesymbol beginSymbol = schemesymbol.makeSymbol( "begin" ); 
  static schemesymbol loopSymbol = schemesymbol.makeSymbol( "loop" );
  static schemesymbol letrecSymbol = schemesymbol.makeSymbol( "letrec" );

  public expanddo() {
    super( "do" );
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {

      /*
       *     ( do
       *       (
       *         ( <variable> <init> <step> )
       *         ...
       *       )
       *       ( <test> <result> ... )
       *       <command>
       *       ...
       *     )
       */
      schemelist variables = schemenull.nullObject();
      schemelist inits     = schemenull.nullObject();
      schemelist steps     = schemenull.nullObject();

      if ( exp.getCar().isPair() ) {
        schemelist iterations  = (schemepair)exp.getCar();

        variables = new schemepair( iterationVariable( iterations.getCar() ) );
        inits     = new schemepair( iterationInit( iterations.getCar() ) );
        steps     = new schemepair( iterationStep( iterations.getCar() ) );

        schemelist next = (schemelist)iterations.getCdr();

        while( next.isPair() ) {
          variables.append( new schemepair( iterationVariable( next.getCar() ) ) );
          inits.append( new schemepair( iterationInit( next.getCar() ) ) );
          steps.append( new schemepair( iterationStep( next.getCar() ) ) );
          next = (schemelist)next.getCdr();
        };

      }

      schemeobject test = schemenull.nullObject();
      schemeobject results = schemenull.nullObject();

      if ( exp.getCadr().isPair() ) {
        schemelist termination = (schemepair)exp.getCadr();

        test = terminationTest( termination );
        results = terminationResults( termination );
      }

      schemelist commands = schemenull.nullObject();

      if ( exp.getCddr().isPair() ) {
        commands    = (schemepair)exp.getCddr();
      }

      /*
       * ( letrec
       *   (
       *     loop 
       *     ( lambda ( <variable> ... )
       *       ( if <test>
       *         ( begin <result> ... )
       *         ( begin <command> ... ( loop <step> ... ))
       *       )
       *     )
       *   )
       *   ( loop <init> ... ) 
       * )
       */
      schemepair expanded = new schemepair( letrecSymbol );
      expanded.append( new schemepair( new schemepair( 
        loopDeclaration( 
          variables, 
          ifExpression( 
            test,
            terminationBlock( results ),
            loopBlock( commands, steps )
          )
        )
      ) ) );
      expanded.append( new schemepair( 
        loopInitialisation( inits )
      ) );

      return expanded;

    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandlet.expand() : ill-formed let expression"
      );
    }
  }

/*----------------------------------------------------------------------------*/

  schemeobject iterationVariable( schemeobject iteration ) throws schemelistexception {
    return ((schemelist)iteration).getCar();
  }

  schemeobject iterationInit( schemeobject iteration ) throws schemelistexception {
    return ((schemelist)iteration).getCadr();
  }

  schemeobject iterationStep( schemeobject iteration ) throws schemelistexception {
    if ( ((schemelist)iteration).length() >= 3 ) {
      return ((schemelist)iteration).getCaddr();
    } else {
      return ((schemelist)iteration).getCar();
    }
  }

  schemeobject terminationTest( schemelist termination ) throws schemelistexception {
    return termination.getCar();
  }

  schemeobject terminationResults( schemelist termination ) throws schemelistexception {
    return termination.getCdr();
  }

/*----------------------------------------------------------------------------*/

  schemepair terminationBlock( schemeobject results ) throws schemelistexception {
    schemepair tb = new schemepair( beginSymbol );
    tb.append( results );
    return tb;
  }

  schemepair loopBlock( schemeobject commands, schemeobject increments ) throws schemelistexception {
    schemepair lb = new schemepair( beginSymbol );
    lb.append( commands );
    schemepair lc = new schemepair( loopSymbol );
    lc.append( increments );
    lb.append( new schemepair( lc ) );
    return lb;
  }

  schemepair ifExpression( schemeobject test, schemeobject tb, schemeobject lb ) throws schemelistexception {
    schemepair ie = new schemepair( schemepair.ifSymbol );
    ie.append( new schemepair( test ) );
    ie.append( new schemepair( tb ) );
    ie.append( new schemepair( lb ) );
    return ie;
  }

  schemepair loopDeclaration( schemeobject variables, schemeobject ifExpression ) throws schemelistexception {
    schemepair loopProcedure = new schemepair( schemepair.lambdaSymbol );      
    loopProcedure.append( new schemepair( variables ) );
    loopProcedure.append( new schemepair( ifExpression ) );

    schemepair loop = new schemepair( loopSymbol );
    loop.append( new schemepair( loopProcedure ) );

    return loop;
  }

  schemepair loopInitialisation( schemeobject initialisations ) throws schemelistexception {
    schemepair init = new schemepair( loopSymbol );
    init.append( initialisations );
    return init;
  }

}
