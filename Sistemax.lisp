(print "Bienvenido al sistema experto de Pizza")
(print "Que prefiere  
1.Masa Delgada  
2.Masa Gruesa")
(defvar *Ele1* (read))
	(if ( = *Ele1* 1)
	(print "Eligio Masa Delgada") 	
	)
    (if ( = *Ele1* 2)
	(print "Eligio Masa Gruesa") 	
	)




	(if (= *Ele1* 1)
	(progn
	(print"Que prefiere
	
1.Salsa Roja
2.Salas Blanca")
(defvar *Ele2* (read)))
	)



    (if (= *Ele1* 2)
	(progn
	(print"Que prefiere
	
1.Salsa Roja
2.Salas Blanca")
(defvar *Ele2* (read)))
	)




  
	(if (= *Ele2* 1)
	(print "Eligio Salsa Roja")
		)

    (if (= *Ele2* 2)
	(print "Eligio Salsa Blanca")
		)


	(if (= *Ele2* 1)
	(progn
	(print "Que prefiere
1.Con Carne
2.Sin Care")
(defvar *Ele3* (read)))
		)



(if (= *Ele2* 2)
	(progn
	(print "Que prefiere
1.Con Carne
2.Sin Carne")
(defvar *Ele3* (read)))
		)





	(if (= *Ele3* 1)
	(print "Eligio Con Carne")
		)
	(if (= *Ele3* 2)
	(print "Eligio Sin Carne")
	)