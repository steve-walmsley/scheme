;
; Procedure with two arguments
;
( define procTwoArg 
  ( lambda ( p1 p2 )
    ( list p2 p1 ) 
  )
)

procTwoArg

( procTwoArg "aardvark" "badger" )         ; Correct call with two arguments
( procTwoArg "aardvark" "badger" "camel" ) ; Erroneous call with three arguments
( procTwoArg "aardvark" )                  ; Erroneous call with one argument
( procTwoArg )                             ; Erroneous call with no arguments

(newline)

;
; Procedure with list argument
;
( define procSingle
  ( lambda plist
    plist
  )
)

procSingle

( procSingle "aardvark" "badger" "camel" "dingo" ) ; several arguments
( procSingle )                                     ; no arguments

(newline)

;
; Procedure with optional arguments
;
( define procVariable
  ( lambda ( p1 p2 . opt )
    ( list
      ( list "p1" p1 )
      ( list "p2" p2 )
      ( list "opt" opt )
    )
  )
)

procVariable

( procVariable "aardvark" "badger" "camel" "dingo" ) ; 4 arguments, 2 options
( procVariable "aardvark" "badger" "camel" )         ; 3 arguments, 1 option
( procVariable "aardvark" "badger" )                 ; 2 arguments exactly
( procVariable "aardvark" )                          ; 1 argument - error

(newline)
;
;-------------------------------------------------------------------------------
;
; define proc alternative syntax
;
;-------------------------------------------------------------------------------
;
; Procedure with two arguments
;
( define ( procTwoArgAlt p1 p2 )
  ( list p2 p1 ) 
)

procTwoArgAlt

( procTwoArgAlt "aardvark" "badger" )         ; Correct call with two arguments
( procTwoArgAlt "aardvark" "badger" "camel" ) ; Erroneous call with three arguments
( procTwoArgAlt "aardvark" )                  ; Erroneous call with one argument
( procTwoArgAlt )                             ; Erroneous call with no arguments

(newline)

;
; Procedure with list argument
;
( define ( procSingleAlt . plist )
  plist
)

procSingleAlt

( procSingleAlt "aardvark" "badger" "camel" "dingo" ) ; several arguments
( procSingleAlt )                                     ; no arguments

(newline)

;
; Procedure with optional arguments
;
( define ( procVariableAlt p1 p2 . opt )
  ( list
    ( list "p1" p1 )
    ( list "p2" p2 )
    ( list "opt" opt )
  )
)

procVariableAlt

( procVariableAlt "aardvark" "badger" "camel" "dingo" ) ; 4 arguments, 2 options
( procVariableAlt "aardvark" "badger" "camel" )         ; 3 arguments, 1 option
( procVariableAlt "aardvark" "badger" )                 ; 2 arguments exactly
( procVariableAlt "aardvark" )                          ; 1 argument - error

(newline)
;
; Procedure? 
;
( procedure? car )
( procedure? 'car )
( procedure? ( lambda(x) (* x x) ) )
( procedure? '( lambda(x) (* x x) ) )
