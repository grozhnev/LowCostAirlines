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
 
INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd)
 VALUES('Jack', 'Jackson', '123-123-123', 'memeguy228@gmail.com', 'qwerty');
INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd)
 VALUES('John', 'Johnson', '321-321-321', 'de_ting_goes_skra@mail.ru', 'qwerty123');

 INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd)
 VALUES('Robert', 'Plant', '700-000-000', 'robert@gmail.com', 'Vacals1');
INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd)
 VALUES('Jimmy', 'Page', '555-555-555', 'page@mail.ru', 'LesPaul');



 
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2015-11-05 14:29:36', '1', '2', '1');
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2016-11-06 13:03:21', '2', '3', '2');
 INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2017-10-25 10:09:00', '1', '2', '1');
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2017-11-06 21:30:40', '2', '3', '2');
  INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2017-12-25 10:09:00', '3', '4', '1');
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2017-12-06 21:30:40', '4', '3', '2');


 
INSERT INTO Ticket (FlightID, CustomerID, Price, LuggagePrice, RegistrationPriority)
 VALUES('1', '1', '100', '10', '1');
INSERT INTO Ticket (FlightID, CustomerID, Price, LuggagePrice, RegistrationPriority)
 VALUES('2', '2', '50', '100', '0');