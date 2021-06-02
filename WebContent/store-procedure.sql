use ecommerce;
DELIMITER $$
CREATE PROCEDURE add_product(IN pname varchar(50), IN pprice decimal(10,2))
INSERT INTO eproduct (name,price) values(pname, pprice);
$$DELIMITER ;