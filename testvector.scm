;
; Vector primitive procedures
;
( make-vector 0 )
( make-vector 1 )
( make-vector 2 )
( make-vector 3 )

( make-vector 1 "aardvaark" )
( make-vector 2 "badger" )
( make-vector 3 "camel" )

( vector? ( make-vector 3 ) )

( vector-length ( make-vector 0 ) )
( vector-length ( make-vector 1 ) )
( vector-length ( make-vector 2 ) )
( vector-length ( make-vector 3 ) )

( let ( ( v ( make-vector 3 ) ) )
  ( vector-set! v 0 "dingo" )
  ( vector-set! v 1 "elephant" )
  ( vector-set! v 2 "fox" )
  v
)

( let ( ( v ( make-vector 3 ) ) )
  ( vector-set! v 0 "giraffe" )
  ( vector-set! v 1 "hyena" )
  ( vector-set! v 2 "iguana" )
  ( vector-ref v 1 )
)

;
; Vector library procedures
;
( vector 'alpher 'bethe 'chadwick )

( vector->list ( vector 'dirac 'einstein 'fermi ) )

( list->vector ( list 'gamow 'heisenberg 'itzykson ) )

( let ( ( v ( make-vector 3 ) ) )
  ( vector-fill! v "jahn" )
)

;
; Vector constants
;
#( 1 2 3 )                                                        ; vector of integers

#( "khariton" "landau" "minkowski" )                              ; vector of strings

#( "noether" #( "ornstein" "pauli" ) "rutherford" )               ; vector within vector

#( "schrödinger" tsiolkovsky ( "uhlenbeck" "voigt" ) 1 2.3 #t )   ; heterogeneous vector

;
; R5RS 6.3.6
;

#(0 (2 2 2 2) "Anna")

(vector-ref '#(1 1 2 3 5 8 13 21) 5) ; => 8

(let ((vec (vector 0 '(2 2 2 2) "Anna"))) (vector-set! vec 1 '("Sue" "Sue")) vec) ; => #(0 ("Sue" "Sue") "Anna")

;(vector-set! '#(0 1 2) 1 "doe") ; => error?

;(vector-ref 
;  ’#(1 1 2 3 5 8 13 21)
;  (let ((i (round (* 2 (acos -1))))) (if (inexact? i)(inexact->exact i)i))
;)                                                                                 ; => 13

(vector->list '#(dah dah didah)) ; => (dah dah didah)

(list->vector '(dididit dah))    ; => #(dididit dah)

