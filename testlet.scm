;
; Simple let ( R5RS 4.2.2 p11 )
;
( let ( ( x 2 ) ( y 3 ) )
  ( * x y )
)
;
; Nested let ( R5RS 4.2.2 p11 )
;
( let ( ( x 2 ) ( y 3 ) )
  ( let ( 
      ( x 7 )
      ( z ( + x y ) )
    )
    ( * z x )
  )
)
;
; Null variable list ( should not give error )
;
( let () "aardvaark" )
;
; Null body ( should not give error )
;
( let () )
;
; let error : bindings cannot refer to each other
;
( let
  (
    ( a "aardvaark" )
    ( b "badger" )
    ( c b )
  ) 
  ( list a b c )
)

