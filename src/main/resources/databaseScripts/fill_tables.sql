/*==============================================================*/
/*                  Filling tables with data                    */
/*==============================================================*/

INSERT INTO Plane (id, MaxLoad, CurrentLoad)
 VALUES('1','50','0');
INSERT INTO Plane (id, MaxLoad, CurrentLoad)
 VALUES('2','50','0');
INSERT INTO Plane (id, MaxLoad, CurrentLoad)
 VALUES('3','50','0');
 
INSERT INTO Airport (idAirport, Name)
 VALUES('1','Berlin');
INSERT INTO Airport (idAirport, Name)
 VALUES('2','London');
INSERT INTO Airport (idAirport, Name)
 VALUES('3','Paris');
INSERT INTO Airport (idAirport, Name)
 VALUES('4','Rome');
 
INSERT INTO Customer (idCustomer, isAdmin, Name, LastName, PersonID)
 VALUES('1','0', 'Jack', 'Jackson', '123-123-123');
INSERT INTO Customer (idCustomer, isAdmin, Name, LastName, PersonID)
 VALUES('2','0', 'John', 'Johnson', '321-321-321');
 
INSERT INTO Flight (id, DateTime, Airport_Source, Airport_Destination, id)
 VALUES('1','2015-11-05 14:29:36', '1', '2', '1');
INSERT INTO Flight (id, DateTime, Airport_Source, Airport_Destination, id)
 VALUES('2','2015-11-06 13:03:21', '3', '4', '2');
 
INSERT INTO Ticket (idTicket, id, idCustomer, Price, LuggagePrice, RegistrationPriority)
 VALUES('1','1', '1', '100', '10', '1');
INSERT INTO Ticket (idTicket, id, idCustomer, Price, LuggagePrice, RegistrationPriority)
 VALUES('2','2', '2', '50', '100', '0');