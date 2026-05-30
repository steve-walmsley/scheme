( define ( letrec-bug ) 
  ( letrec
    (
      ( a "aaardvaark" )
      ( c a )
    )
    ( display "a " ) ( display a ) ( newline )
    ( display "c " ) ( display c ) ( newline )
  )
)

( letrec-bug )
( letrec-bug )
