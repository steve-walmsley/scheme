( pair? '( a . b ) )
( pair? '( a b c ) )
( pair? () )
( pair? 123.456 )
( pair? "abc" )

( null? '( a . b ) )
( null? '( a b c ) )
( null? () )
( null? 123.456 )
( null? "abc" )

( list? '( a . b ) )
( list? '( a b c ) )
( list? () )
( list? 123.456 )
( list? "abc" )

( length '( a b c ) )
( length '( a (b) (c d e) ) )
( length '())
( length "not a list" )                      ; fail - argument not a list

( reverse '( a b c ) )
( reverse '( a (b) (c d e) ) )
( reverse '())
( reverse "not a list" )                     ; fail - argument not a list
;
; list-tail simple lists
;
( list-tail '( a b c ) 0 )
( list-tail '( a b c ) 1 )
( list-tail '( a b c ) 2 )
( list-tail '( a b c ) 3 )                    
( list-tail '( a b c ) 4 )                    ; fail - index out of range
;
; list-tail nested lists
;
( list-tail '( a (b) (c d e) ) 0 )
( list-tail '( a (b) (c d e) ) 1 )
( list-tail '( a (b) (c d e) ) 2 )
( list-tail '( a (b) (c d e) ) 3 )            
( list-tail '( a (b) (c d e) ) 4 )            ; fail - index out of range
;
; list-tail improper lists
;
( list-tail '( a b . c ) 0 )
( list-tail '( a b . c ) 1 )
( list-tail '( a b . c ) 2 )
( list-tail '( a b . c ) 3 )                  ; fail - index out of range
;
; list-tail empty list
;
( list-tail '() 0 )                           
( list-tail '() 1 )                           ; fail - index out of range
;
; list-tail wrong argument types
;
( list-tail "not a list" "not an integer" )   ; fail - argument 1 not a list
( list-tail '( a b c ) "not an integer" )     ; fail - argument 2 not an integer
;
; list-ref simple lists
;
( list-ref '( a b c ) 0 )
( list-ref '( a b c ) 1 )
( list-ref '( a b c ) 2 )
( list-ref '( a b c ) 3 )                    ; fail - index out of range
;
; list-ref nested lists
;
( list-ref '( a (b) (c d e) ) 0 )
( list-ref '( a (b) (c d e) ) 1 )
( list-ref '( a (b) (c d e) ) 2 )
( list-ref '( a (b) (c d e) ) 3 )            ; fail - index out of range
;
; list-ref improper lists
;
( list-ref '( a b . c ) 0 )
( list-ref '( a b . c ) 1 )
( list-ref '( a b . c ) 2 )
( list-ref '( a b . c ) 3 )                  ; fail - index out of range
;
; list-ref wrong argument types
;
( list-ref "not a list" "not an integer" )   ; fail - argument 1 not a list
( list-ref '( a b c ) "not an integer" )     ; fail - argument 2 not an integer
( list-ref '() 0 )                           ; fail - () is not a list
;
; append
;
( append '(x) '(y) )
( append '(a) '(b c d) )
( append '(a (b)) '((c)) )
( append '(a b) '( c . d ))
( append '() 'a )
( append '() '(a b c) )
( append '(a b c) '() )
( append '( a b c ) '( d e f ) '( g h i ) )

( let (                                      ; Demonstrate that append
   ( x '( a b ) )                            ; copies it's arguments.
  )                                          ;
  ( append x '( c d ) )                      ; Append '( c d ) to a copy of x
  x                                          ; x is unchanged - return '( a b )
)                                            ;

( append '( a b c ) )                        ; trivial append - return arg
( append "abc" )                             ; fail - argument not a list 
;
; set-car!
;
( let (
    ( x '(a b c) )
  )
  ( set-car! x 1 )
  x
)
;
; set-cdr!
;
( let (
    ( x '(a b c) )
  )
  ( set-cdr! x 1 )
  x
)
