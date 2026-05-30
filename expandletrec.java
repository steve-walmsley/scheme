package scheme;

import java.io.PrintStream;

public class expandletrec extends schemeexpander {

  protected schemepair expanded;

  public expandletrec() {
    super( "letrec" );
  }

  public schemeobject expand( schemelist exp ) throws schemeexpandexception {
    try {
      schemelist bindings    = (schemepair)exp.getCar();

      schemelist assignments = bindings.copy();

      for( 
        schemelist a = assignments; 
        !a.isNull(); 
        a = (schemelist)a.getCdr()
      ) {
        schemepair setExpression = new schemepair(
          schemepair.setSymbol,
          ((schemepair)a.getCar()).copy() 
        );
        ((schemepair)a).setCar( setExpression);
      };

      for( 
        schemelist b = bindings;
        !b.isNull(); 
        b = (schemelist)b.getCdr()
      ) {
        schemepair e = (schemepair)b.getCdar();
        e.setCar( schemefalse.falseObject() );
      };

      schemelist l;
      for( 
        l = assignments; 
        !l.getCdr().isNull(); 
        l = (schemelist)l.getCdr()
      ) {
      };

      schemelist body = (schemepair)exp.getCdr();

      ((schemepair)l).setCdr( body );
      ((schemepair)exp).setCdr( assignments );

      expanded = new schemepair( schemesymbol.makeSymbol( "let"), exp );      
      return expanded;
    } catch ( schemelistexception sle ) {
      throw new schemeexpandexception( 
        "scheme.expandletrec.expand() : ill-formed letrec expression"
      );
    }
  }

}
