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
   idPlane              INT NOT NULL AUTO_INCREMENT,
   MaxLoad              INT NOT NULL,
   CurrentLoad          INT NOT NULL,
   PRIMARY KEY (idPlane)
);

CREATE TABLE Airport
(
   idAirport            INT NOT NULL AUTO_INCREMENT,
   Name                 VARCHAR(45) NOT NULL,
   PRIMARY KEY (idAirport)
);

CREATE TABLE Customerы
(
   idCustomer           INT NOT NULL AUTO_INCREMENT,
   isAdmin              BOOL NOT NULL,   
   Name                 VARCHAR(45) NOT NULL,
   LastName             VARCHAR(45) NOT NULL,
   PersonID             VARCHAR(45) NOT NULL,
   PRIMARY KEY (idCustomer)
);

CREATE TABLE Flight
(
   idFlight             INT NOT NULL AUTO_INCREMENT,
   DateTime             DATETIME(6) NOT NULL,   
   Airport_Departure    VARCHAR(45) NOT NULL,
   Airport_Arrival      VARCHAR(45) NOT NULL,
   idPlane              INT NOT NULL,
   PRIMARY KEY (idFlight)
);

CREATE TABLE Ticket
(
   idTicket             INT NOT NULL AUTO_INCREMENT,
   idFlight             INT NOT NULL,
   idCustomer           INT NOT NULL,
   Price                INT NOT NULL,
   LuggagePrice         INT NOT NULL,
   RegistrationPriority BOOL NOT NULL,
   PRIMARY KEY (idTicket)
);

/*==============================================================*/
/*                      Constraints                             */
/*==============================================================*/
ALTER TABLE Flight ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (Airport_Source)
      REFERENCES Airport (idAirport) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_2 FOREIGN KEY (Airport_Destination)
      REFERENCES Airport (idAirport) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Flight ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (idPlane)
      REFERENCES Plane (idPlane) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (idFlight)
      REFERENCES Flight (idFlight) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Ticket ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (idCustomer)
      REFERENCES Customer (idCustomer) ON DELETE RESTRICT ON UPDATE RESTRICT;