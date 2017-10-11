/*==============================================================*/
/*                  Filling tables with data                    */
/*==============================================================*/

INSERT INTO Plane (MaxLoad, CurrentLoad)
 VALUES('50','0');
INSERT INTO Plane (MaxLoad, CurrentLoad)
 VALUES('50','0');
INSERT INTO Plane (MaxLoad, CurrentLoad)
 VALUES('50','0');
 
INSERT INTO Airport (Name)
 VALUES('Berlin');
INSERT INTO Airport (Name)
 VALUES('London');
INSERT INTO Airport (Name)
 VALUES('Paris');
INSERT INTO Airport (Name)
 VALUES('Rome');
 
INSERT INTO Customer (FirstName, LastName, PersonID)
 VALUES('Jack', 'Jackson', '123-123-123');
INSERT INTO Customer (FirstName, LastName, PersonID)
 VALUES('John', 'Johnson', '321-321-321');
 
INSERT INTO Flight (DateTime, Airport_Source, Airport_Destination, idPlane)
 VALUES('2015-11-05 14:29:36', '1', '2', '1');
INSERT INTO Flight (DateTime, Airport_Source, Airport_Destination, idPlane)
 VALUES('2015-11-06 13:03:21', '3', '4', '2');
 
INSERT INTO Ticket (idFlight, idCustomer, Price, LuggagePrice, RegistrationPriority)
 VALUES('1', '1', '100', '10', '1');
INSERT INTO Ticket (idFlight, idCustomer, Price, LuggagePrice, RegistrationPriority)
 VALUES('2', '2', '50', '100', '0');