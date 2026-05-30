( define aardvaark "the-value-of-aardvaark" )
aardvaark
( let ( 
    ( symbolName ( list-ref *argv* 1 ) )
  )
  ( eval ( string->symbol symbolName ) )
)
