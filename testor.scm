;
; R5RS 4.21 p 11
;
( or ( = 2 2 ) ( > 2 1 ) ) ; => #t
( or ( = 2 2 ) ( < 2 1 ) ) ; => #t
( or #f #f #f )            ; => #f
;
; Single argument cases
;
( or ( > 2 1 ) ) ; => #t
( or ( < 2 1 ) ) ; => #f
;
; No arguments 
;
( or ) ; => #f
