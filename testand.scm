;
; R5RS 4.21 p 11
;
( and ( = 2 2 ) ( > 2 1 ) ) ; => #t
( and ( = 2 2 ) ( < 2 1 ) ) ; => #f
( and 1 2 'c '(f g ) )      ; => ( f g )
;
; Single argument cases
;
( and ( > 2 1 ) ) ; => #t
( and ( < 2 1 ) ) ; => #f
;
; No arguments 
;
( and ) ; => #t
