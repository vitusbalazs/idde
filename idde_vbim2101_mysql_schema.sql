-- CREATE DATABASE idde_vbim2101;
USE idde_vbim2101;

CREATE TABLE IF NOT EXISTS Advertisements  (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    address VARCHAR(200),
    price INT,
    surfaceArea INT,
    rooms INT
);

CREATE TABLE IF NOT EXISTS Shops  (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    rating INT
);

/*
INSERT INTO Advertisements VALUES(default, "Lakas1 elado", "Cim1", 10, 10, 10);
INSERT INTO Advertisements VALUES(default, "Lakas2 elado", "Cim2", 20, 30, 40);
INSERT INTO Advertisements VALUES(default, "Lakas3 elado", "Cim3", 70, 60, 50);

INSERT INTO Shops VALUES(default, "Uzlet 1", "Cim1", 5);
INSERT INTO Shops VALUES(default, "Uzlet 2", "Cim2", 2);
INSERT INTO Shops VALUES(default, "Uzlet 3", "Cim3", 4);
*/

SELECT * FROM Advertisements;
SELECT * FROM Shops;

-- UPDATE Advertisements SET surfaceArea = 100, rooms = 2 WHERE ID = 1;

-- DELETE FROM Advertisements WHERE ID IN (4,5,6);