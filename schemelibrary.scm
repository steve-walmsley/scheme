;
; R5RS Section 6.2.5 Numerical Operations
;
( define zero?     ( lambda( x ) ( = x 0.0 ) ) )
( define positive? ( lambda( x ) ( > x 0.0 ) ) )
( define negative? ( lambda( x ) ( < x 0.0 ) ) )
( define even?     ( lambda( x ) ( zero? ( remainder x 2 ) ) ) )
( define odd?      ( lambda( x ) ( not ( even? x ) ) ) )

( define abs ( lambda ( x ) 
    ( cond 
      ( ( negative? x ) ( - x ) )
      ( else x )
    ) 
  ) 
)
;
; R5RS Section 6.3.2. Pairs and lists
;
( define ( caar pair ) ( car ( car pair ) ) ) 
( define ( cadr pair ) ( car ( cdr pair ) ) ) 
( define ( cdar pair ) ( cdr ( car pair ) ) ) 
( define ( cddr pair ) ( cdr ( cdr pair ) ) ) 

( define ( caaar pair ) ( car ( car ( car pair ) ) ) )
( define ( caadr pair ) ( car ( car ( cdr pair ) ) ) )
( define ( cadar pair ) ( car ( cdr ( car pair ) ) ) )
( define ( caddr pair ) ( car ( cdr ( cdr pair ) ) ) )

( define ( cdaar pair ) ( cdr ( car ( car pair ) ) ) )
( define ( cdadr pair ) ( cdr ( car ( cdr pair ) ) ) )
( define ( cddar pair ) ( cdr ( cdr ( car pair ) ) ) )
( define ( cdddr pair ) ( cdr ( cdr ( cdr pair ) ) ) )

( define ( caaaar pair ) ( car ( car ( car ( car pair ) ) ) ) )
( define ( caaadr pair ) ( car ( car ( car ( cdr pair ) ) ) ) )
( define ( caadar pair ) ( car ( car ( cdr ( car pair ) ) ) ) )
( define ( caaddr pair ) ( car ( car ( cdr ( cdr pair ) ) ) ) )

( define ( cadaar pair ) ( car ( cdr ( car ( car pair ) ) ) ) )
( define ( cadadr pair ) ( car ( cdr ( car ( cdr pair ) ) ) ) )
( define ( caddar pair ) ( car ( cdr ( cdr ( car pair ) ) ) ) )
( define ( cadddr pair ) ( car ( cdr ( cdr ( cdr pair ) ) ) ) )

( define ( cdaaar pair ) ( cdr ( car ( car ( car pair ) ) ) ) )
( define ( cdaadr pair ) ( cdr ( car ( car ( cdr pair ) ) ) ) )
( define ( cdadar pair ) ( cdr ( car ( cdr ( car pair ) ) ) ) )
( define ( cdaddr pair ) ( cdr ( car ( cdr ( cdr pair ) ) ) ) )

( define ( cddaar pair ) ( cdr ( cdr ( car ( car pair ) ) ) ) )
( define ( cddadr pair ) ( cdr ( cdr ( car ( cdr pair ) ) ) ) )
( define ( cdddar pair ) ( cdr ( cdr ( cdr ( car pair ) ) ) ) )
( define ( cddddr pair ) ( cdr ( cdr ( cdr ( cdr pair ) ) ) ) )

;
; R5RS Section 6.3.6 Vectors
;

( define ( vector->list v )
  ( do
    (
      ( k 0 ( + k 1 ) )
      ( l () ( cons ( vector-ref v k ) l ) )
    )    
    ( ( >= k ( vector-length v ) ) ( reverse l ) )
  )
) 

( define ( list->vector l )
  ( let 
    ( 
      ( v ( make-vector ( length l ) ) ) 
    )
    ( do
      (
        ( k 0 ( + k 1 ) )
        ( a l ( cdr a ) )
      )
      ( ( >= k ( vector-length v ) ) v )
      ( vector-set! v k ( car a ) )
    )
  )
)

( define ( vector-fill! v fill )
  ( do
    (
      ( k 0 ( + k 1 ) )
    )
    ( ( >= k ( vector-length v ) ) v )
    ( vector-set! v k fill )
  )
)

( define ( vector . args ) ( list->vector args ) ) 

;
; R5RS Section 6.4 Control Features
;

;
; map - based on Dybvig87 pp74-5
;
( define ( map f ls . more )
  ( if ( null? more )
    ( letrec 
      (
        ( map1 
          ( lambda ( ls )
            ( if ( null? ls )
              '()
              ( cons
                ( f ( car ls ) )
                ( map1 ( cdr ls ) )
              )
            )
          )
        )
      )
      ( map1 ls )
    )
    ( letrec 
      (
        ( map-more
          ( lambda ( ls more )
            ( if ( null? ls )
              '()
              ( cons
                ( apply  f ( car ls ) ( map car more ) )
                ( map-more ( cdr ls ) ( map cdr more ) )
              )
            )
          )
        )
      )
      ( map-more ls more )
    )
  )
)

( define ( for-each f ls . more )
  ( if ( null? more )
    ( map f ls )
    ( map f ls more )
  )
  #f
)

;
; R7RS Section 6.9 Bytevectors
;

( define ( list->bytevector l )
  ( let 
    ( 
      ( v ( make-bytevector ( length l ) ) ) 
    )
    ( do
      (
        ( k 0 ( + k 1 ) )
        ( a l ( cdr a ) )
      )
      ( ( >= k ( bytevector-length v ) ) v )
      ( bytevector-u8-set! v k ( car a ) )
    )
  )
)

( define ( bytevector . args ) ( list->bytevector args ) ) 

;
; Maths Constants
;
( define pi 3.141592653589793 )
( define ( degrees->radians d ) ( * ( / d 180 ) pi ) )
( define ( radians->degrees r ) ( * ( / r pi ) 180 ) )
;
; References
;
; R5RS 
;   Kelsey et al : The Revised 5 Report on the Algorithmic Language Scheme
;
; Dybvig87 
;   R Kent Dybvig : The Scheme Programming Language
;
