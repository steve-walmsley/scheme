;
; Test symbol? cf R5RS 6.3.3
;
( symbol? 'foo )
( symbol? ( car '( a b ) ) )
( symbol? "bar" )
( symbol? 'nil )
( symbol? '() )
( symbol? #f )
;
; Test string->symbol
;
( symbol? ( string->symbol "aardvaark" ) )
( list 
  ( string->symbol "aardvaark" )
  ( string->symbol "badger" )
  ( string->symbol "camel" )
)
