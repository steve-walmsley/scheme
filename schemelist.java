package scheme;

public abstract class schemelist extends schemeobject {

public abstract schemeobject getCar() throws schemelistexception;

public abstract schemeobject getCdr() throws schemelistexception;

public abstract schemeobject getCaar() throws schemelistexception;

public abstract schemeobject getCadr() throws schemelistexception;

public abstract schemeobject getCdar() throws schemelistexception;

public abstract schemeobject getCddr() throws schemelistexception;

public abstract schemeobject getCaddr() throws schemelistexception;

public abstract schemeobject getCadddr() throws schemelistexception;

public abstract schemelist copy() throws schemelistexception;

public abstract schemeobject append( schemeobject o ) throws schemelistexception;

public abstract schemelist reverse() throws schemelistexception;

public abstract int length() throws schemelistexception;

public abstract schemeobject listtail( int k ) throws schemelistexception;

public abstract Object[] toArray() throws schemelistexception;

}
