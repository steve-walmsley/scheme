( write "cos" )
( cos 0 )
( cos ( / pi 2 ) )
( cos pi )
( cos ( * ( / pi 2 ) 3 ) )
( cos ( * pi 2 ) )

( write "sin" )
( sin 0 )
( sin ( / pi 2 ) )
( sin pi )
( sin ( * ( / pi 2 ) 3 ) )
( sin ( * pi 2 ) )

( write "tan" )
( tan 0 )
( tan ( / pi 2 ) )
( tan pi )
( tan ( * ( / pi 2 ) 3 ) )
( tan ( * pi 2 ) )

( write "asin" )
( asin 0 )                    ;  0 degrees
( asin ( / 1 2 ) )            ; 30 degrees
( asin ( / 1 ( sqrt 2 ) ) )   ; 45 degrees
( asin ( / ( sqrt 3 ) 2 ) )   ; 60 degrees
( asin 1 )                    ; 90 degrees

( write "acos" )
( acos 0 )                    ; 90 degrees
( acos ( / 1 2 ) )            ; 60 degrees
( acos ( / 1 ( sqrt 2 ) ) )   ; 45 degrees
( acos ( / ( sqrt 3 ) 2 ) )   ; 30 degrees
( acos 1 )                    ;  0 degrees

( write "atan" )
( atan 0 )                    ;  0 degrees
( atan ( / 1 ( sqrt 3 ) ) )   ; 30 degrees
( atan 1 )                    ; 45 degrees
( atan ( sqrt 3 ) )           ; 60 degrees
( atan ( / 1 0 ) )            ; 90 degrees

( write "atan2" )
( atan -1 0 )                 ; -90 degrees
( atan ( - ( sqrt 3 ) ) 1 )   ; -60 degrees
( atan -1 1 )                 ; -45 degrees
( atan -1 ( sqrt 3 ) )        ; -30 degrees
( atan 0 1 )                  ;  0 degrees
( atan 1 ( sqrt 3 ) )         ; 30 degrees
( atan 1 1 )                  ; 45 degrees
( atan ( sqrt 3 ) 1 )         ; 60 degrees
( atan 1 0 )                  ; 90 degrees

