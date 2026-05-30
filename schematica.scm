( define ( table table-function range )
  ( letrec
    (

      ( table-level
        ( lambda ( lf irange ) 
          ( let
            (
              ( imin ( car irange ) )
              ( imax ( cadr irange ) )
            )
            ( do
              (
                ( i imin ( + i 1 ) )
                ( t () ( append t ( list ( lf i ) ) ) )
              )
              ( ( > i imax ) t )
            )
          )
        )
      )

      ( level-function 
        ( lambda ( indices range )
          ( if ( > ( length range ) 0 )
            ( lambda (index) 
              ( table-level 
                ( level-function ( append indices ( list index ) ) ( cdr range ) )
                ( car range )
              ) 
            )
            ( lambda (index) 
              ( apply table-function ( append indices ( list index ) ) ) 
            ) 
          )
        )
      )

    ) 

    ( table-level 
      ( level-function '() ( cdr range ) ) 
      ( car range ) 
    ) 

  )
)

( table 
 ( lambda ( x y z ) ( list x y z ) )
 '( ( 0 2 ) ( 0 3 ) ( 0 4 ) )
)
