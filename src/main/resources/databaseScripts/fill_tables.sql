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
VALUES('John', 'Johnson', '321-321-321', 'qq', 'ww');
INSERT INTO Customer (FirstName, LastName, Passport, Email, Passwd)
VALUES('John', 'Johnson', '321-321-321', 'aa', 'ss');

 
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2015-11-05 14:29:36', '1', '2', '1');
INSERT INTO Flight (DateTime, AirportSource, AirportDestination, PlaneID)
 VALUES('2016-11-06 13:03:21', '2', '3', '2');
 
INSERT INTO Ticket (FlightID, CustomerID, Price, LuggagePrice, RegistrationPriority)
 VALUES('1', '1', '100', '10', '1');
INSERT INTO Ticket (FlightID, CustomerID, Price, LuggagePrice, RegistrationPriority)
 VALUES('2', '2', '50', '100', '0');