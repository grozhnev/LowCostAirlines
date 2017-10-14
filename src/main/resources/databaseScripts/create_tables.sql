/*==============================================================*/
/*                      Dropping tables                         */
/*==============================================================*/

DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Plane;
DROP TABLE IF EXISTS Airport;
DROP TABLE IF EXISTS Customer;

/*==============================================================*/
/*                      Creating tables                         */
/*==============================================================*/
CREATE TABLE Plane
(
   PlaneID              INT AUTO_INCREMENT,
   MaxLoad              INT NOT NULL,
   CurrentLoad          INT NOT NULL,
   PRIMARY KEY (PlaneID)
);

CREATE TABLE Airport
(
   AirportID            INT NOT NULL AUTO_INCREMENT,
   Name                 VARCHAR(45) NOT NULL,
   PRIMARY KEY (AirportID)
);

CREATE TABLE Customer
(
   CustomerID           INT NOT NULL AUTO_INCREMENT,
   FirstName            VARCHAR(45) NOT NULL,
   LastName             VARCHAR(45) NOT NULL,
   Passport             VARCHAR(45) NOT NULL,
   Email                VARCHAR(45) NOT NULL,
   Passwd               VARCHAR(45) NOT NULL,
   PRIMARY KEY (CustomerID)
);

CREATE TABLE Flight
(
   FlightID             INT NOT NULL AUTO_INCREMENT,
   DateTime             DATETIME(6),
   AirportSource        INT NOT NULL,
   AirportDestination   INT NOT NULL,
   PlaneID              INT NOT NULL,
   PRIMARY KEY (FlightID)
);

CREATE TABLE Ticket
(
   TicketID             INT AUTO_INCREMENT,
   FlightID             INT NOT NULL,
   CustomerID           INT NOT NULL,
   Price                INT NOT NULL,
   LuggagePrice         INT NOT NULL,
   RegistrationPriority BOOL NOT NULL,
   PRIMARY KEY (TicketID)
);

/*==============================================================*/
/*                      Constraints                             */
/*==============================================================*/
ALTER TABLE Flight ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (AirportSource)
      REFERENCES Airport (AirportID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (AirportDestination)
      REFERENCES Airport (AirportID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (PlaneID)
      REFERENCES Plane (PlaneID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (FlightID)
      REFERENCES Flight (FlightID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (CustomerID)
      REFERENCES Customer (CustomerID) ON UPDATE CASCADE ON DELETE CASCADE;