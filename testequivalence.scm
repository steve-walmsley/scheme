;
; eq? ( R5RS 6.1 p19 )
;
(write "test eq?" )
(eq? 'a 'a)                ; => #t
(eq? '(a) '(a))            ; => unspecified
(eq? (list 'a) (list 'a))  ; => #f
(eq? "a" "a")              ; => unspecified
(eq? "" "")                ; => unspecified
(eq? '() '())              ; => #t
(eq? 2 2)                  ; => unspecified
;(eq? #\A #\A)             ; => unspecified
(eq? car car)              ; => #t
(let ((n (+ 2 3)))
  (eq? n n ))              ; => unspecified
(let ((x '(a)))
  (eq? x x ))              ; => #t
(let ((x '#()))
  (eq? x x ))              ; => #t
(let ((p (lambda (x) x)))
  (eq? p p ))              ; => #t;
;
; eqv? ( R5RS 6.1 pp17-18 )
;
;
; Equivalent objects
;
(write "test eqv? with equivalent objects" )
(eqv? #t #t)                            ; obj1 and obj2 are both #t
(eqv? #f #f)                            ; obj1 and obj2 are both #f
(eqv? 'aardvaark 'aardvaark )           ; obj1 and obj2 are symbols with the same string representation
(eqv? 1234 1234 )                       ; obj1 and obj2 are both the same integer
(eqv? 1234.0 1234.0 )                   ; obj1 and obj2 are both the same real
(eqv? '() '())                          ; obj1 and obj2 are both the empty list
(let* ((obj1 (cons 'aardvaark 'badger))
       (obj2 obj1))
  (eqv? obj1 obj2))                     ; obj1 and obj2 both refer to the same pair
(let* ((obj1 "aardvaark")
       (obj2 obj1))
  (eqv? obj1 obj2))                     ; obj1 and obj2 both refer to the same string
(let* ((obj1 (lambda(x) (+ x 1)))
       (obj2 obj1))
  (eqv? obj1 obj2))                     ; obj1 and obj2 both refer to the same procedure
;
; Non-equivalent objects
;
(write "test eqv? with non-equivalent objects" )
(eqv? #t 1234 )                         ; obj1 is a boolean and obj2 is a number
(eqv? 'aardvaark "aardvaark" )          ; obj1 is a symbol and obj2 is a string
(eqv? #t #f)                            ; obj1 is #t and obj2 is #f
(eqv? 'aardvaark 'badger )              ; obj1 and obj2 are symbols with different string representation
(eqv? 1234 5678 )                       ; obj1 and obj2 are different integers
(eqv? 1234.0 5678.0 )                   ; obj1 and obj2 are different reals
(eqv? '() '( aardvaark badger ) )       ; obj1 is the empty list and obj2 is not
(let ((obj1 (cons 'aardvaark 'badger))
      (obj2 (cons 'camel 'dingo)))
  (eqv? obj1 obj2))                     ; obj1 and obj2 are different pairs
(let ((obj1 "aardvaark")
      (obj2 "badger"))
  (eqv? obj1 obj2))                     ; obj1 and obj2 are different strings
(let ((obj1 (lambda(x) (+ x 1)))
      (obj2 (lambda(x) (+ x 2))))
  (eqv? obj1 obj2))                     ; obj1 and obj2 are procedures which return different values
;
; R5RS p18 examples with specified results
;
(write "eqv? examples with specified results" )
(eqv? 'a 'a)                     ; => #t
(eqv? 'a 'b)                     ; => #f 
(eqv? 2 2)                       ; => #t
(eqv? '() '())                   ; => #t
(eqv? 1000000000 1000000000)     ; => #t
(eqv? ( cons 1 2 ) ( cons 1 2 )) ; => #f 
(eqv? ( lambda() 1 ) 
      ( lambda() 2 ) )           ; => #f 
(eqv? #f 'nill )                 ; => #f 
(let ((p (lambda(x) x)))
  (eqv? p p))                    ; => #t
;
; R5RS p18 examples with unspecified results
;
(write "eqv? examples with unspecified results" )
(eqv? "" "" )                    ; => unspecified
(eqv? (lambda(x) x) 
      (lambda(x) x))             ; => unspecified
(eqv? (lambda(x) x) 
      (lambda(y) y))             ; => unspecified
;
; R5RS p18 procedures with local state
;
(write "eqv? procedures with local state" )

(define gen-counter
  (lambda ()
    (let ((n 0))
      (lambda () (set! n (+ n 1)) n))))
(let ((g (gen-counter)))
  (eqv? g g))                                       ; => #t
(eqv? (gen-counter) (gen-counter))                  ; => #f

(define gen-loser
  (lambda ()
    (let ((n 0))
      (lambda () (set! n (+ n 1)) 27))))
(let ((g (gen-loser)))
  (eqv? g g))                                       ; => #t
(eqv? (gen-loser) (gen-loser))                      ; => unspecified

(letrec ((f (lambda () (if (eqv? f g) 'both 'f)))
         (g (lambda () (if (eqv? f g) 'both 'g))))
  (eqv? f g))                                       ; => unspecified

(letrec ((f (lambda () (if (eqv? f g) 'f 'both)))
         (g (lambda () (if (eqv? f g) 'g 'both))))
  (eqv? f g))                                       ; => #f
;
; R5RS p18 constant objects
;
(write "eqv? constant objects" )
(eqv? '(a) '(a))          ; => unspecified
(eqv? "a" "a")            ; => unspecified
(eqv? '(b) (cdr '(a b)))  ; => unspecified
(let ((x '(a)))
  (eqv? x x))             ; => #t
;
; equal? ( R5RS 6.1 p19 )
;
(write "test equal?" )
(write "(equal? 'a 'a)" )
(equal? 'a 'a)                ; => #t
(write "(equal? '(a) '(a))" )
(equal? '(a) '(a))            ; => #t
(write "(equal? '(a (b) c) '(a (b) c))" )
(equal? '(a (b) c)
        '(a (b) c))           ; => #t
(write "(equal? \"abc\" \"abc\")" )
(equal? "abc" "abc")          ; => #t
(write "(equal? 2 2)" )
(equal? 2 2)                  ; => #t
(equal? (make-vector 5 'a)
        (make-vector 5 'a))   ; => #t
(write "(equal? (lambda (x) x) (lambda (y) y))" )
(equal? (lambda (x) x)
        (lambda (y) y))       ; => unspecified

