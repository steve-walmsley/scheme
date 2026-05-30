package scheme;

import java.util.Iterator;

public class listiterable implements Iterable<schemeobject> {

  protected Iterator<schemeobject> i;

  public listiterable( schemelist l ) {
    this.i = new listenumeration( l ).asIterator();
  }

  public Iterator<schemeobject> iterator() { return i; }

}
