;
; Apply
;
( apply car '( ( a b c ) ) )
( apply list 'a 'b 'c '( d e f ) )
( apply + ( list 2 3 4 5 ) )
( apply ( lambda(x) (* x x) ) 2 )
( apply car () )
( apply runtime )
( apply 'car '( a b c ) )
