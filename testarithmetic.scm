( write "addition" )
( + 2   3   )           ; Integer + Integer -> Integer
( + 2.1 3   )           ; Real    + Integer -> Real
( + 2   3.2 )           ; Integer + Real    -> Real
( + 2.3 3.2 )           ; Real    + Real    -> Real
( + 3 )                 ; Single argument   -> Identity
( + )                   ; No argument       -> 0
( + 1 2.1 4 5.2 7 8.3 ) ; Sum

( write "multiplication" )
( * 2   3   )           ; Integer * Integer -> Integer
( * 2.5 3   )           ; Real    * Integer -> Real
( * 2   3.2 )           ; Integer * Real    -> Real
( * 2.5 3.2 )           ; Real    * Real    -> Real
( * 2.5 )               ; Single argument   -> argument
( * )                   ; No argument       -> 1
( * 1 2.5 4 5.2 7 8.1 ) ; Product

( write "subtraction" )
( - 2   3   )           ; Integer - Integer -> Integer
( - 2.5 3   )           ; Real    - Integer -> Real
( - 2   3.5 )           ; Integer - Real    -> Real
( - 2.2 4.7 )           ; Real    - Real    -> Real
( - 3 )                 ; Single argument   -> Negate
( - 1 2.5 4 5.2 7 8.2 ) ; Difference

( write "division" )
( / 2   8   )           ; Integer / Integer -> Real
( / 2.5 2   )           ; Real    / Integer -> Real
( / 2   0.5 )           ; Integer / Real    -> Real
( / 3.2 0.4 )           ; Real    / Real    -> Real
( / 4 )                 ; Single argument   -> Reciprocal
( / 1.1 2 4.4 5 )       ; Quotient

( write "vector addition" )
( + '( 1 2 3 ) 4 )
( + 4 '( 1 2 3 ) )
( + '( 1 2 3 ) '( 4 5 6 ) )
( + '( 1 2 3 ) '( 4 5 6 ) '( 7 8 9 ) )
( + '( 1.1 2.2 3.3 ) 4.4 )
( + 4.4 '( 1.1 2.2 3.3 ) )
( + '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) )
( + '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) '( 7.7 8.8 9.9 ) )

( write "vector multiplication" )
( * '( 1 2 3 ) 4 )
( * 4 '( 1 2 3 ) )
( * '( 1 2 3 ) '( 4 5 6 ) )
( * '( 1 2 3 ) '( 4 5 6 ) '( 7 8 9 ) )
( * '( 1.1 2.2 3.3 ) 4.4 )
( * 4.4 '( 1.1 2.2 3.3 ) )
( * '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) )
( * '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) '( 7.7 8.8 9.9 ) )

( write "vector subtraction" )
( - '( 1 2 3 ) 4 )
( - 4 '( 1 2 3 ) )
( - '( 1 2 3 ) '( 4 5 6 ) )
( - '( 1 2 3 ) '( 4 5 6 ) '( 7 8 9 ) )
( - '( 1.1 2.2 3.3 ) 4.4 )
( - 4.4 '( 1.1 2.2 3.3 ) )
( - '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) )
( - '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) '( 7.7 8.8 9.9 ) )
( - '( 1.1 2.2 3.3 ) )

( write "vector division" )
( / '( 1 2 3 ) 4 )
( / 4 '( 1 2 3 ) )
( / '( 1 2 3 ) '( 4 5 6 ) )
( / '( 1 2 3 ) '( 4 5 6 ) '( 7 8 9 ) )
( / '( 1.1 2.2 3.3 ) 4.4 )
( / 4.4 '( 1.1 2.2 3.3 ) )
( / '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) )
( / '( 1.1 2.2 3.3 ) '( 4.4 5.5 6.6 ) '( 7.7 8.8 9.9 ) )
( / '( 1.1 2.2 3.3 ) )

( write "division by zero" )
( / 2   0   )           ; Integer / Integer -> Real
( / 2.5 0   )           ; Real    / Integer -> Real
( / 2   0.0 )           ; Integer / Real    -> Real
( / 3.2 0.0 )           ; Real    / Real    -> Real
( / 0 )                 ; Single argument   -> Reciprocal
( / 1.1 0 4.4 5 )       ; Quotient

( write "integer division" )
( quotient  4 2 )
( quotient  2 4 )
( quotient  8 3 )
( quotient -8 3 )
( quotient  0 4 )
( quotient  4 0 )

( write "remainder and modulo" )
( modulo     13  4 )
( remainder  13  4 )
( modulo    -13  4 )
( remainder -13  4 )
( modulo     13 -4 )
( remainder  13 -4 )
( modulo    -13 -4 )
( remainder -13 -4 )
