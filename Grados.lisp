(print "Ingrese la temperatura")
(defvar *grados* (read))
(defun temperaturas(a)
( + 32 ( * a 1.8 ) )
)

(print (temperaturas *grados*))