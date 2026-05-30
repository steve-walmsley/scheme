;
; begin with return value ( R5RS 4.2.3 p12 )
;
( define x 0 )

( begin ( set! x 5 ) ( + x 1 ) )

;
; begin with side effects ( R5RS 4.2.3 p12 )
;
( begin ( display "4 plus 1 equals " ) ( display ( + 4 1 ) ) )

;
; begin with no args : should not generate error
;
( begin )
