( map abs ( list 1 -2 3 -4 5 -6 ) )
( map + ( list 1 2 3  ) ( list 4 5 6 ) )
( map - ( list 1 2 3  ) ( list 4 5 6 ) )
( map car ( list '( a b c ) '( d e f ) ) )
( map cons '( a b c ) '( d e f ) )

( for-each 
  ( lambda ( o ) ( write o ) ( newline ) ) 
  ( list "aardvaark" "badger" "camel" "dingo" ) 
)
