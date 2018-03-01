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
    version  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Countries (
    code     INTEGER PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
    version  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Organizations (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    fullname   VARCHAR(250) NOT NULL,
    inn        VARCHAR(10) NOT NULL,
    kpp        VARCHAR(9) NOT NULL,
    address    VARCHAR(250) NOT NULL,
    phone      VARCHAR(20),
    is_active   BIT NOT NULL DEFAULT 1,
    version  INTEGER NOT NULL  DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Offices (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    org_id      INTEGER NOT NULL,
    name       VARCHAR(50) NOT NULL,
    phone      VARCHAR(20),
    address    VARCHAR(250) NOT NULL,
    is_active   BIT NOT NULL DEFAULT 1,
    version  INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (org_id) REFERENCES Organizations(id)
);

CREATE TABLE IF NOT EXISTS Users (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    login      VARCHAR(50),
    password   VARCHAR(50),
    firstname  VARCHAR(50) NOT NULL,
    secondname VARCHAR(50) NOT NULL,
    middlename VARCHAR(50),
    position_statement  VARCHAR(50) NOT NULL,
    phone      VARCHAR(20),
    office_id   INTEGER NOT NULL,
    doc_code    INTEGER NOT NULL,
    doc_number  VARCHAR(150) NOT NULL,
    doc_date    DATE NOT NULL,
    countries_code INTEGER NOT NULL,
    version  INTEGER NOT NULL DEFAULT 0,
    is_identified    BIT NOT NULL DEFAULT 1,
    FOREIGN KEY (office_id) REFERENCES Offices(id),
    FOREIGN KEY (doc_code) REFERENCES Docs(code),
    FOREIGN KEY (countries_code) REFERENCES Countries(code)
);



