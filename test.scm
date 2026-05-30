"abc"                                             ; a string
123                                               ; an integer
123.456                                           ; a real
-321                                              ; a negative integer
-654.321                                          ; a negative real
( define abc 123 )                                ; define symbol
abc                                               ; symbol value
( set!   abc 789 )                                ; set symbol
abc                                               ; redefined value
def                                               ; undefined symbol
( set!   def 789 )                                ; set undefined symbol
()                                                ; the empty list
( quote abc )                                     ; quoted symbol
( quote "abc" )                                   ; quoted string
( quote 123 )                                     ; quoted integer
( quote 123.456 )                                 ; quoted real
( quote ( abc "abc" 123 123.456 ) )               ; quoted simple list
( quote ( abc ( "abc" 123 ) 123.456 ) )           ; quoted nested list
( define p ( lambda ( p1 p2 ) s1 s2 s3 ) )        ; lambda expression
p                                                 ; procedure value
( define p1 123     )                             ; procedure parameter
( define p2 456.789 )                             ; procedure parameter
( define p3 "abc"   )                             ; procedure parameter
( aardvaark p1 p2 p3 -123 "def" )                 ; undefined procedure 
car                                               ; car primitive
cdr                                               ; cdr primitive
( car ( quote ( "aardvark" "badger" "camel" ) ) ) ; car application
( cdr ( quote ( "aardvark" "badger" "camel" ) ) ) ; cdr application
( cons "dingo" "elephant" )                       ; cons application
( list "fox" "giraffe" "hyena" "iguana" )         ; list application
