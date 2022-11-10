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

-- INSERT INTO Advertisements VALUES(default, "Lakas1 elado", "Cim1", 10, 10, 10);
-- INSERT INTO Advertisements VALUES(default, "Lakas2 elado", "Cim2", 20, 30, 40);
-- INSERT INTO Advertisements VALUES(default, "Lakas3 elado", "Cim3", 70, 60, 50);

SELECT * FROM Advertisements;

UPDATE Advertisements SET surfaceArea = 100, rooms = 2 WHERE ID = 1;

-- DELETE FROM Advertisements WHERE ID IN (4,5,6);