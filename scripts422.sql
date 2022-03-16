--Описание структуры: у каждого человека есть машина. Причем несколько человек могут пользоваться одной машиной.
-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.
-- Также не забудьте добавить таблицам первичные ключи и связать их.

CREATE TABLE person
(
    id                  NUMERIC(15, 0) PRIMARY KEY,
    name                VARCHAR(60)               NOT NULL,
    age                 INTEGER CHECK ( age > 0 ) NOT NULL,
    drivingLicenseCheck BOOLEAN DEFAULT false,
    carId               NUMERIC(15, 0) REFERENCES car (id)

);

CREATE TABLE car
(
    id    NUMERIC(15, 0) PRIMARY KEY,
    brand VARCHAR(60)    NOT NULL,
    model VARCHAR(60)    NOT NULL,
    cost  NUMERIC(15, 4) NOT NULL
);
