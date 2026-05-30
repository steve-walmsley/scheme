set -x
PATH=.:$PATH
scm test.scm            | diff test.out -
scm testquote.scm       | diff testquote.out -
scm testio.scm          | diff testio.out -
scm testproc.scm        | diff testproc.out -
scm testlist.scm        | diff testlist.out -
scm testsymbol.scm      | diff testsymbol.out -
scm testnumbers.scm     | diff testnumbers.out -
scm testarithmetic.scm  | diff testarithmetic.out -
scm testdot.scm         | diff testdot.out -
scm testif.scm          | diff testif.out -
scm testand.scm         | diff testand.out -
scm testor.scm          | diff testor.out -
scm testcond.scm        | diff testcond.out -
scm testlet.scm         | diff testlet.out -
scm testletstar.scm     | diff testletstar.out -
scm testletrec.scm      | diff testletrec.out -
scm testbegin.scm       | diff testbegin.out -
scm testdo.scm          | diff testdo.out -
#scm testexternal.scm    | diff testexternal.out -
scm testargv.scm aardvaark badger camel dingo | diff testargv.out -
scm testnumeric.scm     | diff testnumeric.out -
scm testmap.scm         | diff testmap.out -
scm testeval.scm        | diff testeval.out -
scm testequivalence.scm | diff testequivalence.out -
scm testminmax.scm      | diff testminmax.out -
scm testvector.scm      | diff testvector.out -
scm testbytevector.scm  | diff testbytevector.out -
scm teststring.scm      | diff teststring.out -
