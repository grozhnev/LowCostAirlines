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
  CustomerId             INT AUTO_INCREMENT,
  CustomerIsAdmin        BOOL,
  CustomerFirstName      VARCHAR(45),
  CustomerLastName       VARCHAR(45),
  CustomerPassportId     VARCHAR(45),
  PRIMARY KEY (CustomerId)
);

CREATE TABLE Ticket
(
  TicketId                 INT AUTO_INCREMENT,
  FlightId                 INT,
  CustomerId               INT,
  PriceForTicket           INT,
  PriceForLugagge          INT,
  PriorityForRegistration  BOOL,
  PRIMARY KEY (TicketId)
);

CREATE TABLE Flight
(
  FlightId             INT AUTO_INCREMENT,
  DateTime             DATETIME(6),
  AirportOfDeparture   INT,
  AirportOfArrival     INT,
  PlaneId              INT,
  PRIMARY KEY (FlightId)
);

CREATE TABLE Plane
(
   PlaneId              INT AUTO_INCREMENT,
   PlaneMaxLoad         INT,
   PlaneCurrentLoad     INT,
   PRIMARY KEY (PlaneId)
);

CREATE TABLE Airport
(
   AirportId       INT AUTO_INCREMENT,
   AIrportName     VARCHAR(45),
   PRIMARY KEY (AirportId)
);

/*==============================================================*/
/*                      Constraints                             */
/*==============================================================*/
ALTER TABLE Flight ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (AirportOfDeparture)
      REFERENCES Airport (AirportId);

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (AirportOfArrival)
      REFERENCES Airport (AirportId);

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (PlaneId)
      REFERENCES Plane (PlaneId);



ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (FlightId)
      REFERENCES Flight (FlightId);

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (CustomerId)
      REFERENCES Customer (CustomerId);