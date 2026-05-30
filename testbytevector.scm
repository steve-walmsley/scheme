;
; Byte Vector primitive procedures
;
( make-bytevector 0 )
( make-bytevector 1 )
( make-bytevector 2 )
( make-bytevector 3 )

( make-bytevector 1 64 )
( make-bytevector 2 128 )
( make-bytevector 3 255 )

( bytevector? ( make-bytevector 3 ) )

( bytevector-length ( make-bytevector 0 ) )
( bytevector-length ( make-bytevector 1 ) )
( bytevector-length ( make-bytevector 2 ) )
( bytevector-length ( make-bytevector 3 ) )

( let ( ( v ( make-bytevector 3 ) ) )
  ( bytevector-u8-set! v 0 64 )
  ( bytevector-u8-set! v 1 128 )
  ( bytevector-u8-set! v 2 255 )
  v
)

( let ( ( v ( make-bytevector 3 ) ) )
  ( bytevector-u8-set! v 0 64 )
  ( bytevector-u8-set! v 1 128 )
  ( bytevector-u8-set! v 2 255 )
  ( bytevector-u8-ref v 1 )
)

;
; Bytevector library procedures
;
( bytevector 64 128 255 )

;
; Bytevector copying 
;

(define a #u8( 1 2 3 4 5)) 
(bytevector-copy a 2 4) ; => #u8(3 4)

(bytevector-copy a -2 4) ; => error
(bytevector-copy a 2 5) ; => error

(define a (bytevector 1 2 3 4 5))
(define b (bytevector 10 20 30 40 50)) 
(bytevector-copy! b 1 a 0 2)
b ; => #u8(10 1 2 40 50)

(bytevector-copy! b -1 a 0 2) ; => error at < 0
(bytevector-copy! b  4 a 0 2) ; => error (- (bytevector-length to) at) < (- end start).

;
; Bytevector appending
;
(bytevector-append #u8(0 1 2) #u8(3 4 5)) ; => #u8(0 1 2 3 4 5)

;
; String comversion
;
(utf8->string #u8(65)) ; => "A"
(string->utf8 "λ")     ; => #u8(#xCE #xBB)

;
; Byteector constants
;
#u8( 0 10 5 )                                                      ; vector of bytes

;
; R7RS 6.9
;

(make-bytevector 2 12) ; => #u8(12 12)

(bytevector 1 3 5 1 3 5) ; => #u8(1 3 5 1 3 5)

(bytevector) ; => #u8()

(let ((bv (bytevector 1 2 3 4))) 
  (bytevector-u8-set! bv 1 3) 
  bv
) ; ==> #u8(1 3 3 4)
