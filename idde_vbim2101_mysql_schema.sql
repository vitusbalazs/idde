-- CREATE DATABASE idde_vbim2101;
USE idde_vbim2101;

DROP TABLE IF EXISTS Advertisements;
DROP TABLE IF EXISTS Owners;

CREATE TABLE IF NOT EXISTS Owners (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(200),
    age INT
);

CREATE TABLE IF NOT EXISTS Advertisements  (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    address VARCHAR(200),
    price INT,
    surfaceArea INT,
    rooms INT,
    owner INT,
    FOREIGN KEY (owner) REFERENCES Owners(ID)
);

INSERT INTO Owners VALUES(default, "Jakab Sarolta", "jgysarolta@gmail.com", 20);
INSERT INTO Owners VALUES(default, "Vitus Balazs", "vitusbalazs01@yahoo.com", 21);
INSERT INTO Owners VALUES(default, "Galambos Lajos", "lagzi@lajcsi.hu", 61);

INSERT INTO Advertisements VALUES(default, "Lakas1 elado", "Cim1", 10, 10, 10, 1);
INSERT INTO Advertisements VALUES(default, "Lakas2 elado", "Cim2", 20, 30, 40, 2);
INSERT INTO Advertisements VALUES(default, "Lakas3 elado", "Cim3", 70, 60, 50, 1);

SELECT * FROM Advertisements;
SELECT * FROM Owners;

INSERT INTO Advertisements VALUES (default, "m", "n", 1, 2, 3, 1);

-- UPDATE Advertisements SET surfaceArea = 100, rooms = 2 WHERE ID = 1;

-- DELETE FROM Advertisements WHERE ID IN (4,5,6);

-- SELECT * FROM Advertisements 
-- JOIN Owners ON Owners.id = Advertisements.owner
-- WHERE Owners.age = 21