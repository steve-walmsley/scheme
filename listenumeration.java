package scheme;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class listenumeration implements Enumeration<schemeobject> {

  protected schemelist l;

  public listenumeration( schemelist l ) {
    this.l = l;
  }

  public boolean hasMoreElements() {
    return !l.isNull();
  }

  public schemeobject nextElement() {
    schemelist next;
    next = l;
    try {
      l = (schemelist)l.getCdr();
      return next.getCar();
    } catch ( schemelistexception sle ) {
      throw new NoSuchElementException( sle.getMessage() );
    }
  }

}
