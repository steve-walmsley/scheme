;
; Recursive procedure definition. See R5RS 4.2.2, p12
;
( letrec
  (
    ( even? 
      ( lambda (n) 
        ( cond 
          ( ( zero? n )                 #t )
          ( else        ( odd? ( - n 1 ) ) )
        ) 
      ) 
    )
    ( odd?  
      ( lambda (n) 
        ( cond 
          ( ( zero? n )                 #f )
          ( else        ( even? ( - n 1 ) ) )
        ) 
      ) 
    )
  )
  (even? 88)
)

