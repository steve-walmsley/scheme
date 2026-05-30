set -x
javac schemeexternal.java
javah scheme.schemeexternal

gcc -c -Wall -fPIC -D_REENTRANT   \
    -I$JAVA_HOME/include          \
    -I$JAVA_HOME/include/linux    \
       schemeexternalimp.c        \
    -o schemeexternalimp.o

gpc -c schemeexternal.pas --unit-path$SOFTOOLS -fPIC -D_REENTRANT \
    --ignore-function-results

gpc -c testdespatcher.pas --unit-path$SOFTOOLS -fPIC -D_REENTRANT

ld -shared                        \
   schemeexternalimp.o            \
   schemeexternal.o               \
   testdespatcher.o               \
   $SOFTOOLS/softools.o           \
  -L/usr/local/lib/gcc-lib/i686-pc-linux-gnu/2.95.3 \
  -lgpc                           \
  -o libschemeexternal.so

