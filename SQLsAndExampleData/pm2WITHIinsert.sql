CREATE SCHEMA IF NOT EXISTS FancyCar;

USE FancyCar;

DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS UserSavedCars;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS CarListings;
DROP TABLE IF EXISTS Sellers;
DROP TABLE IF EXISTS SedansAndCoupes;
DROP TABLE IF EXISTS SUVsAndCrossovers;
DROP TABLE IF EXISTS Trucks;
DROP TABLE IF EXISTS CarModels;

CREATE TABLE CarModels (
	ModelId INT NOT NULL,
	Brand VARCHAR(255) NOT NULL,
	Model VARCHAR(255) NOT NULL,
	FuelType VARCHAR(255) NOT NULL,
	-- ## or if you can process the source and make it fit in one of these ##
	-- FuelType ENUM(
	-- 'GAS',
	-- 'DIESEL',
	-- 'ELECTRICAL',
	-- 'HYBRID'
	-- ) NOT NULL,
	CONSTRAINT pk_CarModels_ModelId PRIMARY KEY (ModelId)
);

CREATE TABLE SedansAndCoupes (
	ModelId INT NOT NULL,
	GasTankVolume DECIMAL(3,1),
	CONSTRAINT pk_SedansAndCoupes_ModelId PRIMARY KEY (ModelId),
	CONSTRAINT fk_SedansAndCoupes_ModelId FOREIGN KEY (ModelId)
		REFERENCES CarModels(ModelId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE SUVsAndCrossovers (
	ModelId INT NOT NULL,
	NumberOfSeats INT,
	CONSTRAINT pk_SUVsAndCrossovers_ModelId PRIMARY KEY (ModelId),
	CONSTRAINT fk_SUVsAndCrossovers_ModelId FOREIGN KEY (ModelId)
		REFERENCES CarModels(ModelId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Trucks (
	ModelId INT NOT NULL,
	BedType VARCHAR(255),
	BedLength DECIMAL(3,1) DEFAULT 00.0,
	CONSTRAINT pk_Trucks_ModelId PRIMARY KEY (ModelId),
	CONSTRAINT fk_Trucks_ModelId FOREIGN KEY (ModelId)
		REFERENCES CarModels(ModelId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Sellers (
	SellerId INT NOT NULL,
	Name VARCHAR(255) NOT NULL,
	Zip VARCHAR(255) NOT NULL,
	HasFranchise BOOLEAN NOT NULL,
	CONSTRAINT pk_Sellers_SellerId PRIMARY KEY (SellerId)
);

CREATE TABLE CarListings (
	VIN VARCHAR(255) NOT NULL,
	ModelId INT,
	SellerId INT,
	PictureUrl VARCHAR(1024),
	Year INT NOT NULL DEFAULT 1000,
	City VARCHAR(255),
	ExteriorColor VARCHAR(255),
	InteriorColor VARCHAR(255),
	Mileage INT,
	HasAccident BOOLEAN NOT NULL DEFAULT FALSE,
	IsCPO BOOLEAN NOT NULL DEFAULT FALSE,
	Price INT NOT NULL DEFAULT -1,
	CONSTRAINT pk_CarListings_VIN PRIMARY KEY (VIN),
	CONSTRAINT fk_CarListings_ModelId FOREIGN KEY (ModelId)
		REFERENCES CarModels(ModelId)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_CarListings_SellerId FOREIGN KEY (SellerId)
		REFERENCES Sellers(SellerId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Users (
	Username VARCHAR(255) NOT NULL,
	Password VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NOT NULL,
	Phone VARCHAR(255),
	CONSTRAINT pk_Users_Username PRIMARY KEY (Username)
);

CREATE TABLE UserSavedCars (
	UserName VARCHAR(255) NOT NULL,
	VIN VARCHAR(255) NOT NULL,
	CONSTRAINT pk_UserSavedCars_Username_VIN PRIMARY KEY (Username, VIN),
	CONSTRAINT fk_UserSavedCars_Username FOREIGN KEY (Username)
		REFERENCES Users(Username)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_UserSavedCars_VIN FOREIGN KEY (VIN)
		REFERENCES CarListings(VIN)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendations (
	RecommendationId INT AUTO_INCREMENT,
	Username VARCHAR(255) NOT NULL,
	VIN VARCHAR(255) NOT NULL,
	Created TIMESTAMP NOT NULL,
	CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
	CONSTRAINT fk_Recommendations_VIN FOREIGN KEY (VIN)
		REFERENCES CarListings(VIN)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Recommendations_Username FOREIGN KEY (Username)
		REFERENCES Users(Username)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reviews (
	ReviewId INT AUTO_INCREMENT,
	Username VARCHAR(255),
	VIN VARCHAR(255) NOT NULL,
	Created TIMESTAMP NOT NULL,
	Rating DECIMAL(2,1) NOT NULL,
	Content VARCHAR(255),
	CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
	CONSTRAINT fk_Reviews_VIN FOREIGN KEY (VIN)
		REFERENCES CarListings(VIN)
		ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Reviews_Username FOREIGN KEY (Username)
		REFERENCES Users(Username)
		ON UPDATE CASCADE ON DELETE SET NULL
);



# Insert data

USE FancyCar;
SET SESSION sql_mode = '';


LOAD DATA INFILE '/tmp/CarModels.csv' INTO TABLE CarModels
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/truck.csv' INTO TABLE Trucks
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;


LOAD DATA INFILE '/tmp/suv.csv' INTO TABLE SUVsAndCrossovers
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA INFILE '/tmp/sedan.csv' INTO TABLE SedansAndCoupes
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/seller.csv' IGNORE INTO TABLE Sellers
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
  
LOAD DATA INFILE '/tmp/carlist.csv' IGNORE INTO TABLE CarListings
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
INSERT INTO Users(Username, Password, FirstName, LastName, Email, Phone)
  VALUES('neusea','seattle','Paul','Peterson','ppeterson@email.com','4751234567'),
  ('neubos','boston','Mae','Li','mayl@mail.com','5554257123'),
        ('neuhusky','husky','Ruby','Shelby','rubys@mail.com',NULL);
        
INSERT INTO UserSavedCars(Username, VIN)
  VALUES('neusea','SALCJ2FX1LH858117'),
        ('neuhusky','ZACNJABB5KPJ92081');
        
INSERT INTO Recommendations(Username, VIN, Created)
  VALUES('neusea','SALCJ2FX1LH858117', '2021-10-13 21:55:01'),
  ('neuhusky','ZACNJABB5KPJ92081','2021-10-13 16:15:07');

INSERT INTO Reviews(Username, VIN, Created, Rating, Content)
  VALUES('neusea','SALCJ2FX1LH858117','2021-10-13 21:55:01','4.0', NULL),
        ('neuhusky','ZACNJABB5KPJ92081','2021-10-13 16:15:07','3.5','Price looks resonable');