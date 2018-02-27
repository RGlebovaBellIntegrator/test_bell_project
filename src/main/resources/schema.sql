CREATE TABLE IF NOT EXISTS Person (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    house_id   INTEGER,
    age        INTEGER  NOT NULL
);

CREATE TABLE IF NOT EXISTS House (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    address    VARCHAR(50) NOT NULL
);

CREATE INDEX IX_Person_House_Id ON Person (house_id);
ALTER TABLE Person ADD FOREIGN KEY (house_id) REFERENCES House(id);


--Таблицы для домашнего задания

CREATE TABLE IF NOT EXISTS Docs (
    code     INTEGER PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
);

CREATE TABLE IF NOT EXISTS Countries (
    code     INTEGER PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
);

CREATE TABLE IF NOT EXISTS Organizations (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    fullname   VARCHAR(250) NOT NULL,
    inn        VARCHAR(10) NOT NULL,
    kpp        VARCHAR(9) NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      VARCHAR(20),
    isActive   BIT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS Offices (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    ordId      INTEGER NOT NULL,
    name       VARCHAR(50) NOT NULL,
    phone      VARCHAR(20),
    address    VARCHAR(250) NOT NULL,
    isActive   BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (ordId) REFERENCES Organizations(id)
);

CREATE TABLE IF NOT EXISTS Users (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    login      VARCHAR(50),
    password   VARCHAR(50),
    firstName  VARCHAR(50) NOT NULL,
    secondName VARCHAR(50) NOT NULL,
    middleName VARCHAR(50),
    position_statement  VARCHAR(50) NOT NULL,
    phone      VARCHAR(20),
    officeId   INTEGER NOT NULL,
    docCode    INTEGER NOT NULL,
    docNumber  VARCHAR(150) NOT NULL,
    docDate    DATE NOT NULL,
    citizenshipCode  INTEGER NOT NULL,
    isIdentified    BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (officeId) REFERENCES Offices(id),
    FOREIGN KEY (docCode) REFERENCES Docs(code),
    FOREIGN KEY (citizenshipCode) REFERENCES Countries(code)
);


