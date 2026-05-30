'abc                                              ; quoted symbol
'"abc"                                            ; quoted string
'123                                              ; quoted integer
'123.456                                          ; quoted real
'( abc "abc" 123 123.456 )                        ; quoted simple list
'( abc ( "abc" 123 ) 123.456 )                    ; quoted nested list
( car '( a b c ) )                                ; quote inside a list
'( '( a b c ) )                                   ; nested quoted list
