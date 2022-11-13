USE master
GO
CREATE DATABASE FurnitureStore
GO
USE FurnitureStore
GO
CREATE TABLE categories (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL
);
CREATE TABLE brands (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL
);
CREATE TABLE products (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	model_year SMALLINT NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL,
	brand_id INT NOT NULL,
	category_id INT NOT NULL
);
CREATE TABLE customers (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	fullname NVARCHAR (255) NOT NULL,
	phone VARCHAR (15) NOT NULL,
	email VARCHAR (255) NOT NULL,
	street NVARCHAR (255),
	city NVARCHAR (100),
	state NVARCHAR (30),
	zip_code VARCHAR (6)
);
CREATE TABLE stores (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	phone VARCHAR (15),
	email VARCHAR (255),
	street NVARCHAR (255),
	city NVARCHAR (255),
	state NVARCHAR (30),
	zip_code VARCHAR (6)
);
CREATE TABLE staffs (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	fullname NVARCHAR (255) NOT NULL,
	email VARCHAR (255) NOT NULL UNIQUE,
	phone VARCHAR (15),
	active tinyint NOT NULL,
	store_id INT NOT NULL,
	manager_id INT
	-- Manage ID: 1 = Director; 0 = Staff
);
CREATE TABLE orders (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	status tinyint NOT NULL,
	-- Status: 1 = Pending; 2 = Processing; 3 = Rejected; 4 = Completed
	order_date DATETIME NOT NULL,
	shipped_date DATETIME,
	store_id INT NOT NULL,
	staff_id INT NOT NULL,
	customer_id INT NOT NULL
);
CREATE TABLE order_details (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	quantity INT NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL,
	discount DECIMAL (4, 2) NOT NULL DEFAULT 0,
	order_id INT NOT NULL,
	product_id INT NOT NULL
);
CREATE TABLE stocks (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	quantity INT,
	store_id INT,
	product_id INT
);
GO

SET IDENTITY_INSERT categories ON
insert into categories (id, name) values (1, 'Highlander')
insert into categories (id, name) values (2, 'Spectra')
insert into categories (id, name) values (3, 'Firefly')
insert into categories (id, name) values (4, 'Expedition')
insert into categories (id, name) values (5, 'Cabriolet')
insert into categories (id, name) values (6, 'Crossfire')
insert into categories (id, name) values (7, 'Savana')
insert into categories (id, name) values (8, 'Corvette')
insert into categories (id, name) values (9, 'Impreza')
insert into categories (id, name) values (10, 'Bravada')
SET IDENTITY_INSERT categories OFF

SET IDENTITY_INSERT brands ON
insert into brands (id, name) values (1, 'Volkswagen');
insert into brands (id, name) values (2, 'Lexus');
insert into brands (id, name) values (3, 'Cadillac');
insert into brands (id, name) values (4, 'Hyundai');
insert into brands (id, name) values (5, 'Mitsubishi');
insert into brands (id, name) values (6, 'Mitsubishi');
insert into brands (id, name) values (7, 'Volvo');
insert into brands (id, name) values (8, 'Buick');
insert into brands (id, name) values (9, 'Volkswagen');
insert into brands (id, name) values (10, 'Isuzu');
SET IDENTITY_INSERT brands OFF

SET IDENTITY_INSERT products ON
insert into products (id, name, model_year, list_price, brand_id, category_id) values (1, 'Spermophilus richardsonii', 2000, 16933060, 9, 6);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (2, 'Macropus rufogriseus', 2011, 32138296, 9, 9);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (3, 'Butorides striatus', 2006, 84596335, 2, 9);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (4, 'Eolophus roseicapillus', 1997, 12042220, 3, 7);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (5, 'Climacteris melanura', 2011, 5703366, 7, 3);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (6, 'Bassariscus astutus', 2010, 81106774, 10, 2);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (7, 'Otocyon megalotis', 2009, 82788178, 9, 5);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (8, 'Coracias caudata', 2004, 71063447, 8, 1);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (9, 'Certotrichas paena', 1994, 29597449, 10, 8);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (10, 'Heloderma horridum', 1997, 97769100, 4, 6);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (11, 'Herpestes javanicus', 1997, 86383925, 2, 4);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (12, 'Paraxerus cepapi', 1960, 37855270, 8, 6);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (13, 'Anser caerulescens', 1993, 74105793, 2, 3);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (14, 'Philetairus socius', 1987, 25692703, 9, 6);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (15, 'Colobus guerza', 1955, 90361142, 4, 4);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (16, 'Leprocaulinus vipera', 1997, 70613114, 6, 1);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (17, 'Irania gutteralis', 2009, 98645184, 10, 1);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (18, 'Canis lupus lycaon', 2001, 78187960, 8, 5);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (19, 'Oryx gazella', 1989, 50174632, 2, 9);
insert into products (id, name, model_year, list_price, brand_id, category_id) values (20, 'Vulpes chama', 1988, 82433783, 6, 8);
SET IDENTITY_INSERT products OFF

SET IDENTITY_INSERT customers ON
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(1,'Colin Anthony','0580301436','colinanthony@google.com','3188 Feugiat. Road','Bình Phước','South Korea','254403');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(2,'Michelle Wallace','0438305787','michellewallace@yahoo.com','9762 Dui Road','Đắk Nông','United States','333058');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(3,'Wyoming Jacobs','0727425146','wyomingjacobs9494@yahoo.com','721 Eget Rd.','Bình Dương','United States','747188');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(4,'Amy Vincent','0905432692','amyvincent@yahoo.com','705-772 Per Av.','Sóc Trăng','Vietnam','784465');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(5,'Selma Whitaker','0806514932','selmawhitaker4705@google.com','7828 Vestibulum Ave','Cần Thơ','United States','786231');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(6,'Maile Sargent','0186181326','mailesargent4847@google.com','975-4947 Iaculis Street','Hậu Giang','China','831743');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(7,'Kessie Welch','0732975361','kessiewelch@yahoo.com','863-951 Eleifend Rd.','Bắc Kạn','Vietnam','844292');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(8,'Roary Fleming','0278246592','roaryfleming4304@google.com','P.O. Box 523, 849 Purus, Ave','Ninh Thuận','Vietnam','724449');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(9,'Nevada Rich','0628543441','nevadarich@google.com','8871 Aliquam Avenue','Phú Yên','United States','261704');
INSERT INTO customers (id,fullname,phone,email,street,city,state,zip_code) VALUES(10,'Isabelle Potts','0228149711','isabellepotts@google.com','917-5078 Nisi. Street','Quảng Nam','South Korea','951461');
SET IDENTITY_INSERT customers OFF

SET IDENTITY_INSERT stores ON
INSERT INTO stores (id,name,phone,email,street,city,state,zip_code) VALUES(1,'Ali Randall','0412142332','alirandall7363@google.com','142-467 Lectus Rd.','Bắc Kạn','China','862643');
INSERT INTO stores (id,name,phone,email,street,city,state,zip_code) VALUES(2,'Driscoll Jacobs','0716567033','driscolljacobs9852@outlook.com','580-6204 Aliquet Street','Vĩnh Phúc','United States','276058');
INSERT INTO stores (id,name,phone,email,street,city,state,zip_code) VALUES(3,'Tasha Nieves','0454424682','tashanieves7977@google.com','P.O. Box 651, 4952 Sed St.','Thừa Thiên–Huế','South Korea','382737');
INSERT INTO stores (id,name,phone,email,street,city,state,zip_code) VALUES(4,'Yen Knight','0064324615','yenknight@outlook.com','P.O. Box 470, 769 At St.','Bạc Liêu','South Korea','288526');
INSERT INTO stores (id,name,phone,email,street,city,state,zip_code) VALUES(5,'Chiquita Cox','0338988834','chiquitacox@yahoo.com','1961 Dignissim. Rd.','Yên Bái','Vietnam','949459');
SET IDENTITY_INSERT stores OFF

SET IDENTITY_INSERT staffs ON
INSERT INTO staffs (id,fullname,phone,email,active,store_id,manager_id) VALUES(1,'Ali Randall','0412142332','alirandall7363@google.com',1,3,0);
INSERT INTO staffs (id,fullname,phone,email,active,store_id,manager_id) VALUES(2,'Driscoll Jacobs','0716567033','driscolljacobs9852@outlook.com',2,1,1);
INSERT INTO staffs (id,fullname,phone,email,active,store_id,manager_id) VALUES(3,'Tasha Nieves','0454424682','tashanieves7977@google.com',3,4,1);
INSERT INTO staffs (id,fullname,phone,email,active,store_id,manager_id) VALUES(4,'Yen Knight','0064324615','yenknight@outlook.com',1,3,0);
INSERT INTO staffs (id,fullname,phone,email,active,store_id,manager_id) VALUES(5,'Chiquita Cox','0338988834','chiquitacox@yahoo.com',2,5,1);
SET IDENTITY_INSERT staffs OFF

SET IDENTITY_INSERT orders ON
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(1,4,'2022-12-20 20:14','2022-11-22 19:49',3,2,3)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(2,2,'2022-12-23 00:17','2022-11-18 19:34',2,3,6)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(3,2,'2022-12-01 03:03','2022-12-26 14:23',3,1,9)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(4,2,'2022-11-14 06:01','2022-11-10 01:07',3,4,5)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(5,3,'2022-11-03 22:13','2022-11-13 01:02',3,1,7)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(6,4,'2022-11-08 01:43','2022-11-18 22:48',4,5,8)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(7,3,'2022-11-10 16:51','2022-12-21 14:39',4,5,2)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(8,3,'2022-12-23 11:35','2022-12-04 08:38',1,2,4)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(9,3,'2022-11-16 01:30','2022-11-24 21:58',4,2,8)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(10,2,'2022-12-29 19:12','2022-11-07 14:52',5,2,7)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(11,4,'2022-12-20 20:14','2022-11-22 19:49',3,2,3)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(12,2,'2022-12-23 00:17','2022-11-18 19:34',2,3,6)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(13,2,'2022-12-01 03:03','2022-12-26 14:23',3,1,9)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(14,2,'2022-11-14 06:01','2022-11-10 01:07',3,4,5)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(15,3,'2022-11-03 22:13','2022-11-13 01:02',3,1,7)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(16,4,'2022-11-08 01:43','2022-11-18 22:48',4,5,8)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(17,3,'2022-11-10 16:51','2022-12-21 14:39',4,5,2)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(18,3,'2022-12-23 11:35','2022-12-04 08:38',1,2,4)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(19,3,'2022-11-16 01:30','2022-11-24 21:58',4,2,8)
INSERT INTO orders (id,status,order_date,shipped_date,store_id,staff_id,customer_id) VALUES(20,2,'2022-12-29 19:12','2022-11-07 14:52',5,2,7)
SET IDENTITY_INSERT orders OFF

SET IDENTITY_INSERT order_details ON
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(1,20,95341098,22,6,4)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(2,62,49133320,14,8,9)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(3,24,28056384,19,11,9)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(4,46,1989046,25,18,19)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(5,8,93968244,13,4,2)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(6,94,76516669,26,9,13)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(7,87,42893123,24,17,5)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(8,46,55214364,29,14,18)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(9,84,15686824,26,17,8)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(10,100,23749781,18,12,5)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(11,20,95341098,22,6,4)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(12,62,49133320,14,8,9)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(13,24,28056384,19,11,9)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(14,46,1989046,25,18,19)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(15,8,93968244,13,4,2)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(16,94,76516669,26,20,13)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(17,87,42893123,24,17,5)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(18,46,55214364,29,14,18)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(19,84,15686824,26,17,8)
INSERT INTO order_details (id,quantity,list_price,discount,order_id,product_id) VALUES(20,100,23749781,18,12,5)
SET IDENTITY_INSERT order_details OFF

SET IDENTITY_INSERT stocks ON
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(1,69,2,15)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(2,50,3,13)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(3,81,1,9)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(4,45,4,16)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(5,29,1,20)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(6,23,5,5)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(7,77,5,9)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(8,38,2,3)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(9,15,2,7)
INSERT INTO stocks (id,quantity,store_id,product_id) VALUES(10,80,2,18)
SET IDENTITY_INSERT stocks OFF
GO
-- products
ALTER TABLE products ADD CONSTRAINT FK_products_categories FOREIGN KEY (category_id)
REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE products ADD CONSTRAINT FK_products_brands FOREIGN KEY (brand_id)
REFERENCES brands (id) ON DELETE CASCADE ON UPDATE CASCADE
-- staffs
ALTER TABLE staffs ADD CONSTRAINT FK_staffs_stores FOREIGN KEY (store_id)
REFERENCES stores (id) ON DELETE CASCADE ON UPDATE CASCADE
-- orders
ALTER TABLE orders ADD CONSTRAINT FK_orders_customers FOREIGN KEY (customer_id)
REFERENCES customers (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE orders ADD CONSTRAINT FK_orders_stores FOREIGN KEY (store_id)
REFERENCES stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE orders ADD CONSTRAINT FK_orders_staffs FOREIGN KEY (staff_id)
REFERENCES staffs (id) ON DELETE NO ACTION ON UPDATE NO ACTION
-- order_details
ALTER TABLE order_details ADD CONSTRAINT FK_orderdetails_orders FOREIGN KEY (order_id)
REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE order_details ADD CONSTRAINT FK_orderdetails_products FOREIGN KEY (product_id)
REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE
-- stocks
ALTER TABLE stocks ADD CONSTRAINT FK_stocks_stores FOREIGN KEY (store_id)
REFERENCES stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE stocks ADD CONSTRAINT FK_stocks_products FOREIGN KEY (product_id)
REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE
GO
USE master
GO
ALTER DATABASE FurnitureStore SET READ_WRITE 
GO