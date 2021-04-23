-- 1.a
SELECT DISTINCT Product.maker FROM Product NATURAL JOIN PC
EXCEPT (SELECT DISTINCT Product.maker FROM Product NATURAL JOIN Laptop);

-- 1.b
SELECT DISTINCT P1.maker FROM
(Product AS P1 NATURAL JOIN PC) LEFT JOIN (Product AS P2 NATURAL JOIN Laptop)
USING (maker) where P2.model IS NULL;

-- 1.c
SELECT DISTINCT maker FROM
(Product NATURAL JOIN PC) WHERE maker NOT IN
(SELECT maker FROM Product NATURAL JOIN Laptop);

-- 1.d
SELECT DISTINCT maker FROM
(Product AS P1 NATURAL JOIN PC) WHERE NOT EXISTS
(SELECT * FROM (Product AS P2 NATURAL JOIN Laptop) WHERE P1.maker = P2.maker);

-- 2.a
SELECT P1.model FROM
Printer AS P1 INNER JOIN Printer AS P2
ON P2.model = 3002 AND P1.price > P2.price;

-- 2.b
SELECT model FROM Printer
WHERE price >
(SELECT price FROM Printer WHERE model = 3002);

-- 2.c
SELECT P1.model FROM Printer AS P1
WHERE EXISTS(SELECT * FROM Printer AS P2 WHERE P2.model = 3002 AND P1.price > P2.price);

-- 3.a
SELECT P1.model FROM PC AS P1 LEFT JOIN PC AS P2
ON P1.speed > P2.speed WHERE P2.model IS NULL;

-- 3.b
SELECT model FROM PC
WHERE speed NOT IN (SELECT P1.speed FROM (PC AS P1), (PC AS P2) WHERE P1.speed > P2.speed);

-- 3.c
SELECT model FROM PC
WHERE speed <= ALL(SELECT speed FROM PC);

-- 3.d
SELECT model FROM PC AS P1
WHERE NOT EXISTS (SELECT * FROM PC AS P2 WHERE P1.price > P2.price);

-- 4.a
SELECT DISTINCT P1.maker FROM
(((PC AS A NATURAL JOIN Product AS P1)
INNER JOIN (PC AS B NATURAL JOIN Product AS P2)
ON P2.maker = P1.maker AND B.speed != A.speed AND B.speed >= 1.8 AND A.speed >= 1.8)
INNER JOIN (PC AS C NATURAL JOIN Product AS P3)
ON P3.maker = P1.maker AND C.speed != A.speed AND C.speed != B.speed AND C.speed >= 1.8);

-- 4.b
SELECT maker FROM
PC NATURAL JOIN Product WHERE speed >= 1.8
GROUP BY maker HAVING COUNT(DISTINCT speed) >= 3;

-- 4.c
SELECT DISTINCT T.maker FROM
  (SELECT Product.maker, COUNT(DISTINCT speed) AS cnt
   FROM PC NATURAL JOIN Product WHERE speed >= 1.8 GROUP BY maker) AS T
WHERE cnt >= 3;

-- 5.a
SELECT DISTINCT maker FROM
Printer INNER JOIN Product USING (model) GROUP BY maker
HAVING COUNT(DISTINCT Printer.type) = (SELECT COUNT(DISTINCT type) FROM Printer);

-- 5.b
SELECT maker FROM (SELECT DISTINCT maker FROM Product) AS Makers
WHERE NOT EXISTS (
  SELECT * FROM (SELECT DISTINCT type FROM Printer) AS Types
  WHERE NOT EXISTS (
    SELECT * FROM Printer INNER JOIN Product USING (model)
    WHERE Printer.type = Types.type AND Product.maker = Makers.maker));

-- 6
SELECT maker FROM (SELECT DISTINCT maker FROM Product) AS Makers
WHERE NOT EXISTS (
  SELECT * FROM (SELECT DISTINCT type FROM Product WHERE maker = 'A') AS ATypes
  WHERE NOT EXISTS (
    SELECT * FROM Product WHERE Product.type = ATypes.type AND Product.maker = Makers.maker));

-- 7.a
UPDATE PC SET price = price * 1.1 WHERE 'A' =
(SELECT maker FROM Product WHERE Product.model = PC.model);

-- 7.b
UPDATE PC SET price = price * 1.1 WHERE model IN
(SELECT model FROM Product WHERE Product.maker = 'A');

-- 7.c
UPDATE PC SET price = price * 1.1 WHERE EXISTS (
  SELECT * FROM Product WHERE Product.model = PC.model AND Product.maker = 'A'
);
