;
; Simple let*, same as first let example ( R5RS 4.2.2 p11 )
;
( let* ( ( x 2 ) ( y 3 ) )
  ( * x y )
)
;
; Nested let* gives different result to nested let ( R5RS 4.2.2 p11 )
;
( let ( ( x 2 ) ( y 3 ) )
  ( let* ( 
      ( x 7 )
      ( z ( + x y ) )
    )
    ( * z x )
  )
)
;
; Null variable list ( should not give error )
;
( let* () "aardvaark" )
;
; Null body ( should not give error )
;
( let* () )
;
; let* : reference to previous binding ( should not give error )
; ( where let would give an error )
;
( let*
  (
    ( a "aardvaark" )
    ( b "badger" )
    ( c b )
  ) 
  ( list a b c )
)
;
; Procedure with let* ( multiple calls should not give error )
;
( define ( let-star-proc )
  ( let*
    (
      ( a "aardvaark" )
      ( c a )
    )
    ( display "a " ) ( display a ) ( newline )
    ( display "c " ) ( display c ) ( newline )
  )
)

( let-star-proc )
( let-star-proc )
