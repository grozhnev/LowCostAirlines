/*==============================================================*/
/*                      Dropping tables                         */
/*==============================================================*/

DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Plane;
DROP TABLE IF EXISTS Airport;

/*==============================================================*/
/*                      Creating tables                         */
/*==============================================================*/

CREATE TABLE Customer
(
  Id             INT AUTO_INCREMENT,
  IsAdmin        BOOL,
  FirstName      VARCHAR(45),
  LastName       VARCHAR(45),
  PassportId     VARCHAR(45),
  PRIMARY KEY (Id)
);

CREATE TABLE Ticket
(
  Id                 INT AUTO_INCREMENT,
  FlightId                 INT,
  CustomerId               INT,
  PriceForTicket           INT,
  PriceForLuggage          INT,
  PriorityForRegistration  BOOL,
  PRIMARY KEY (Id)
);

CREATE TABLE Flight
(
  Id                   INT AUTO_INCREMENT,
  TimeOfDeparture      DATETIME(10),
  AirportOfDeparture   INT,
  AirportOfArrival     INT,
  PlaneId              INT,
  PRIMARY KEY (Id)
);

CREATE TABLE Plane
(
   id              INT AUTO_INCREMENT,
   maxLoad         INT,
   currentLoad     INT,
   PRIMARY KEY (id)
);

CREATE TABLE Airport
(
   Id       INT AUTO_INCREMENT,
   Name     VARCHAR(45),
   PRIMARY KEY (Id)
);

/*==============================================================*/
/*                      Constraints                             */
/*==============================================================*/
ALTER TABLE Flight ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (AirportOfDeparture)
      REFERENCES Airport (Id);

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (AirportOfArrival)
      REFERENCES Airport (Id);

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (PlaneId)
      REFERENCES Plane (id);



ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (FlightId)
      REFERENCES Flight (Id);

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (CustomerId)
      REFERENCES Customer (Id);