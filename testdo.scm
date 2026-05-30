;
; R5RS 4.2.4 p 12
;
( let 
  (
    ( x '( 1 3 5 7 9 ) )
  )
  ( do
    (
      ( x x ( cdr x ) )
      ( sum 0 ( + sum ( car x ) ) )
    )
    ( ( null? x ) sum )
  )
)
;
; Based on Dybvig87 4-5 p77
;
( define ( factorial n )
  ( do
    (
      ( i n ( - i 1 ) )
      ( a 1 ( * a i) )
    )
    ( ( zero? i ) a )
  )
)

( factorial 10 )

;
; Based on Dybvig87 4-5 p77
;
( define ( fibonacci n ) 
  ( if ( = n 0 )
       0
       ( do
         (
           ( i n ( - i 1 ) )
           ( a1 1 ( + a1 a2 ) )
           ( a2 0 a1 )
         )
         ( ( = i 1 ) a1 )
       )
  )
)

( fibonacci 6 )

;
; Print numbers 0..10
;
; Unspecified return value
;
( do
  (
    ( i 0 ( + i 1 ) )
  )
  ( ( > i 10 ) )
  ( write i )
  ( write ( newline ) )
)

;
; Scale numbers by constant
;
; Constant has no step
;
( let 
  (
    ( x '( 1 3 5 7 9 ) )
  )
  ( do
    (
      ( x x ( cdr x ) )
      ( c 5 )
      ( y () ( append y ( list ( * c ( car x ) ) ) ) )
    )
    ( ( null? x ) y )
  )
)
