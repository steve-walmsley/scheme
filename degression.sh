set -x
PATH=.:$PATH
dcm test.scm           | diff test.out -
dcm testquote.scm      | diff testquote.out -
dcm testio.scm         | diff testio.out -
dcm testproc.scm       | diff testproc.out -
dcm testlist.scm       | diff testlist.out -
dcm testsymbol.scm     | diff testsymbol.out -
dcm testnumbers.scm    | diff testnumbers.out -
dcm testarithmetic.scm | diff testarithmetic.out -
dcm testdot.scm        | diff testdot.out -
dcm testif.scm         | diff testif.out -
dcm testcond.scm       | diff testcond.out -
dcm testand.scm        | diff testand.out -
dcm testor.scm         | diff testor.out -
dcm testlet.scm        | diff testlet.out -
dcm testletstar.scm    | diff testletstar.out -
dcm testletrec.scm     | diff testletrec.out -
dcm testbegin.scm      | diff testbegin.out -
dcm testdo.scm         | diff testdo.out -
#dcm testexternal.scm   | diff testexternal.out -
dcm testargv.scm aardvaark badger camel dingo | diff testargv.out -
dcm testnumeric.scm    | diff testnumeric.out -
dcm testmap.scm        | diff testmap.out -
dcm testeval.scm       | diff testeval.out -
dcm testequivalence.scm | diff testequivalence.out -
dcm testminmax.scm     | diff testminmax.out -
dcm testvector.scm     | diff testvector.out -
dcm testbytevector.scm | diff testbytevector.out -
dcm teststring.scm     | diff teststring.out -
