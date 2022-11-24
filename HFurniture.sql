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
	discount INT,
	image NVARCHAR (255) NOT NULL,
	description NVARCHAR (MAX) NOT NULL,
	brand_id INT NOT NULL,
	category_id INT NOT NULL
);
CREATE TABLE authorities (
	id int IDENTITY(1,1) PRIMARY KEY,
	customer_id int NOT NULL,
	role_id nvarchar(10) NOT NULL
);
CREATE TABLE customers (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	username VARCHAR (100) NOT NULL,
	password VARCHAR (255) NOT NULL,
	fullname NVARCHAR (255) NOT NULL,
	phone VARCHAR (15) NOT NULL,
	email VARCHAR (255) NOT NULL,
	birthday DATE,
	street NVARCHAR (255),
	city NVARCHAR (100),
	image NVARCHAR(255),
	token VARCHAR (20)
);
CREATE TABLE roles (
	id nvarchar(10) PRIMARY KEY,
	name nvarchar(50) NOT NULL
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
	username NVARCHAR (100) NOT NULL,
	password NVARCHAR (255) NOT NULL,
	fullname NVARCHAR (255) NOT NULL,
	email VARCHAR (255) NOT NULL UNIQUE,
	phone VARCHAR (15),
	active tinyint NOT NULL,
	store_id INT NOT NULL,
	manager_id INT
	-- Manage id: 1 = Director; 0 = Staff
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
	description NVARCHAR (MAX),
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
INSERT categories (id, name) VALUES (1, N'Highlander')
INSERT categories (id, name) VALUES (2, N'Spectra')
INSERT categories (id, name) VALUES (3, N'Firefly')
INSERT categories (id, name) VALUES (4, N'Expedition')
INSERT categories (id, name) VALUES (5, N'Cabriolet')
INSERT categories (id, name) VALUES (6, N'Crossfire')
INSERT categories (id, name) VALUES (7, N'Savana')
INSERT categories (id, name) VALUES (8, N'Corvette')
INSERT categories (id, name) VALUES (9, N'Impreza')
INSERT categories (id, name) VALUES (10, N'Bravada')
SET IDENTITY_INSERT categories OFF

SET IDENTITY_INSERT brands ON 
INSERT brands (id, name) VALUES (1, N'Volkswagen')
INSERT brands (id, name) VALUES (2, N'Lexus')
INSERT brands (id, name) VALUES (3, N'Cadillac')
INSERT brands (id, name) VALUES (4, N'Hyundai')
INSERT brands (id, name) VALUES (5, N'Mitsubishi')
INSERT brands (id, name) VALUES (6, N'Mitsubishi')
INSERT brands (id, name) VALUES (7, N'Volvo')
INSERT brands (id, name) VALUES (8, N'Buick')
INSERT brands (id, name) VALUES (9, N'Volkswagen')
INSERT brands (id, name) VALUES (10, N'Isuzu')
SET IDENTITY_INSERT brands OFF

SET IDENTITY_INSERT products ON 
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (1, N'Spermophilus richardsonii', 2000, CAST(16933060.00 AS Decimal(10, 2)), 0, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 9, 6)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (2, N'Macropus rufogriseus', 2011, CAST(32138296.00 AS Decimal(10, 2)), 0, 'anh2.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 9, 9)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (3, N'Butorides striatus', 2006, CAST(84596335.00 AS Decimal(10, 2)), 10, 'anh3.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 2, 9)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (4, N'Eolophus roseicapillus', 1997, CAST(12042220.00 AS Decimal(10, 2)), 12, 'anh4.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 3, 7)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (5, N'Climacteris melanura', 2011, CAST(5703366.00 AS Decimal(10, 2)), 7, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 7, 3)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (6, N'Bassariscus astutus', 2010, CAST(81106774.00 AS Decimal(10, 2)), 8, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 10, 2)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (7, N'Otocyon megalotis', 2009, CAST(82788178.00 AS Decimal(10, 2)), 15, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 9, 5)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (8, N'Coracias caudata', 2004, CAST(71063447.00 AS Decimal(10, 2)), 20, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 8, 1)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (9, N'Certotrichas paena', 1994, CAST(29597449.00 AS Decimal(10, 2)), 19, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 10, 8)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (10, N'Heloderma horridum', 1997, CAST(97769100.00 AS Decimal(10, 2)), 5, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 4, 6)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (11, N'Herpestes javanicus', 1997, CAST(86383925.00 AS Decimal(10, 2)), 7, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 2, 4)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (12, N'Paraxerus cepapi', 1960, CAST(37855270.00 AS Decimal(10, 2)), 0, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 8, 6)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (13, N'Anser caerulescens', 1993, CAST(74105793.00 AS Decimal(10, 2)), 0, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 2, 3)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (14, N'Philetairus socius', 1987, CAST(25692703.00 AS Decimal(10, 2)), 0, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 9, 6)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (15, N'Colobus guerza', 1955, CAST(90361142.00 AS Decimal(10, 2)), 7, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 4, 4)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (16, N'Leprocaulinus vipera', 1997, CAST(70613114.00 AS Decimal(10, 2)), 12, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 6, 1)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (17, N'Irania gutteralis', 2009, CAST(98645184.00 AS Decimal(10, 2)), 15, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 10, 1)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (18, N'Canis lupus lycaon', 2001, CAST(78187960.00 AS Decimal(10, 2)), 10, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 8, 5)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (19, N'Oryx gazella', 1989, CAST(50174632.00 AS Decimal(10, 2)), 10, 'anh1.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 2, 9)
INSERT products (id, name, model_year, list_price, discount, image, description, brand_id, category_id) VALUES (20, N'Vulpes chama', 1988, CAST(82433783.00 AS Decimal(10, 2)), 0, 'anh14.jpg', N'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', 6, 8)
SET IDENTITY_INSERT products OFF

SET IDENTITY_INSERT customers ON 
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (1, N'duyplus', N'$2a$10$NHTLzkkYLHMURXWFV7yUi.kcST9VEcIEw8JihKUNMF9YfCvBo6JXK', N'Colin Anthony', N'0580301436', N'duyplus0909@gmail.com', CAST(N'1999-08-21' AS Date), N'3188 Feugiat. Road', N'Bình Phu?c', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (2, N'michellewallace', N'$2a$10$2I.PRD6QwijlT5qsMSh2YO7RbzB0loCmX1y3mCrq5LJNUetRsRJv6', N'Michelle Wallace', N'0438305787', N'michellewallace@yahoo.com', CAST(N'2000-11-01' AS Date), N'9762 Dui Road', N'Ð?k Nông', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (3, N'wyomingjacobs', N'$2a$10$JGj7BiHIihLTo9Fa8oOS0u4sE8Cd491hVAF7cI05KitsIALZOdPjq', N'Wyoming Jacobs', N'0727425146', N'wyomingjacobs9494@yahoo.com', CAST(N'2001-04-19' AS Date), N'721 Eget Rd.', N'Bình Duong', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (4, N'amyvincent', N'$2a$10$a1.Lk61WVVH/J8/3pJ/GbOdugor0s0e21tMXK3wUfMRmfYrMVQLW6', N'Amy Vincent', N'0905432692', N'amyvincent@yahoo.com', CAST(N'1998-12-16' AS Date), N'705-772 Per Av.', N'Sóc Trang', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (5, N'selmawhitaker', N'$2a$10$Q5Dplj9TADom7tzRIAALseT9JBuF9Scsz5wBzWgJbWd.AMMDBhISa', N'Selma Whitaker', N'0806514932', N'selmawhitaker4705@gmail.com', CAST(N'1995-02-11' AS Date), N'7828 Vestibulum Ave', N'C?n Tho', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (6, N'mailesargent', N'$2a$10$idaMrVJoXXidJCDTMHEnf.NMY53BYKsjyToDob/faADg0ISKdyjuK', N'Maile Sargent', N'0186181326', N'mailesargent4847@google.com', CAST(N'2002-11-22' AS Date), N'975-4947 Iaculis Street', N'H?u Giang', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (7, N'kessiewelch', N'$2a$10$RO8xZkMsU2oPdX0GoM1jhuxjfTFM00dbYWsUie7snQYc3zR9EqFta', N'Kessie Welch', N'0732975361', N'kessiewelch@yahoo.com', CAST(N'2000-09-11' AS Date), N'863-951 Eleifend Rd.', N'B?c K?n', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (8, N'roaryfleming', N'$2a$10$wd3kQ60Sqo.Y0anIl6Qt1eFgEtFcsxqYNG5wnRb2eYrXjMxDaRD2u', N'Roary Fleming', N'0278246592', N'roaryfleming4304@gmail.com', CAST(N'2003-11-11' AS Date), N'P.O. Box 523, 849 Purus, Ave', N'Ninh Thu?n', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (9, N'nevadarich', N'$2a$10$uyfFZdy1i1MihBQDmby/l.aJHWDX1StHkGDIIXfMUPW2t6UPrO0ES', N'Nevada Rich', N'0628543441', N'nevadarich@gmail.com', CAST(N'1997-05-07' AS Date), N'8871 Aliquam Avenue', N'Phú Yên', N'avt.png', NULL)
INSERT customers (id, username, password, fullname, phone, email, birthday, street, city, image, token) VALUES (10, N'isabellepotts', N'$2a$10$RPZAn4lZ/UZKX5JAYRBrS.aIcAGpknIen66nfGS.aGkUmOxLV0tmS', N'Isabelle Potts', N'0228149711', N'isabellepotts@gmail.com', CAST(N'1994-06-21' AS Date), N'917-5078 Nisi. Street', N'Qu?ng Nam', N'avt.png', NULL)
SET IDENTITY_INSERT customers OFF

INSERT roles (id, name) VALUES (N'CUST', N'Customers')
INSERT roles (id, name) VALUES (N'STAF', N'Staffs')
INSERT roles (id, name) VALUES (N'DIRE', N'Directors')

SET IDENTITY_INSERT authorities ON 
INSERT authorities (id, customer_id, role_id) VALUES (1, 1, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (2, 2, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (3, 3, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (4, 4, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (5, 5, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (6, 6, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (7, 7, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (8, 8, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (9, 9, N'CUST')
INSERT authorities (id, customer_id, role_id) VALUES (10, 10, N'CUST')
SET IDENTITY_INSERT authorities OFF

SET IDENTITY_INSERT stores ON 
INSERT stores (id, name, phone, email, street, city, state, zip_code) VALUES (1, N'Store 1', N'0812142332', N'store1@hfurniture.com', N'32 Trần Duy Hung', N'Hà Nội', N'Vietnam', N'862643')
INSERT stores (id, name, phone, email, street, city, state, zip_code) VALUES (2, N'Store 2', N'0716567033', N'store2@hfurniture.com', N'181 Trần Hung Ðạo', N'Phan Thiết', N'Vietnam', N'276058')
INSERT stores (id, name, phone, email, street, city, state, zip_code) VALUES (3, N'Store 3', N'0254424682', N'store3@hfurniture.com', N'45 Nguyễn Ðình Chiểu', N'Tây Ninh', N'Vietnam', N'382737')
INSERT stores (id, name, phone, email, street, city, state, zip_code) VALUES (4, N'Store 4', N'0964324615', N'store4@hfurniture.com', N'81 Phan Ðình Phùng', N'Biên Hoà', N'Vietnam', N'288526')
INSERT stores (id, name, phone, email, street, city, state, zip_code) VALUES (5, N'Store 5', N'0738988834', N'store5@hfurniture.com', N'140 Huỳnh Văn Bánh', N'Hồ Chí Minh', N'Vietnam', N'949459')
SET IDENTITY_INSERT stores OFF

SET IDENTITY_INSERT staffs ON 
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (1, N'admin', N'$2a$10$qetL7eLsXAFxsj/JczqLK.MfzJL/JzV/8Mk1hTNbFY1pJ4xj2flAe', N'Nguyễn Van Admin', N'duyplusdz@gmail.com', N'0123456789', 1, 3, 0)
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (2, N'hoangduy', N'$2a$10$OU0eEm5twzgIuYHdcy9rUu7V0UwpukTLFMBPnHT4zS2Sr9hNP6Jiu', N'Nguyễn Hoàng Duy', N'alirandall7363@gmail.com', N'0919993715', 1, 3, 1)
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (3, N'ducduy', N'$2a$10$8F/G5tVvFet4CAg8tvRlcOI48zCig8I6OtOHJ.ajWZiWyidZJu4YS', N'Nguyễn Ðức Duy', N'driscolljacobs9852@gmail.com', N'0716567033', 2, 1, 1)
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (4, N'huyphi', N'$2a$10$YZ4852jiESEi9y/U2R0fDOfZE165pNuLcISvIhu1OSc23C1lJx7N6', N'Dương Huy Phi', N'tashanieves7977@gmail.com', N'0454424682', 3, 4, 1)
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (5, N'honglinh', N'$2a$10$6HfVpFlvOJXKhY2Qa2Z.i.fPSauyFaHwMvJbAu37YE0o1K3wK4iyq', N'Ngô Hồng Linh', N'yenknight@outlook.com', N'0064324615', 1, 3, 1)
INSERT staffs (id, username, password, fullname, email, phone, active, store_id, manager_id) VALUES (6, N'khangtran', N'$2a$10$QWHBRffdsdZ2aA68IoXdzOTzBRLfozuXLkBtHkkNpAkDAVZW7eibe', N'Trần Khang', N'chiquitacox@yahoo.com', N'0338988834', 2, 5, 1)
SET IDENTITY_INSERT staffs OFF

SET IDENTITY_INSERT orders ON 
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (1, 4, CAST(N'2022-11-20 20:14:00.000' AS DateTime), CAST(N'2022-11-22 19:49:00.000' AS DateTime), 3, 2, 3)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (2, 2, CAST(N'2022-11-23 00:17:00.000' AS DateTime), CAST(N'2022-11-24 19:34:00.000' AS DateTime), 2, 3, 6)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (3, 2, CAST(N'2022-11-01 03:03:00.000' AS DateTime), CAST(N'2022-11-05 14:23:00.000' AS DateTime), 3, 1, 9)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (4, 2, CAST(N'2022-11-14 06:01:00.000' AS DateTime), CAST(N'2022-11-10 01:07:00.000' AS DateTime), 3, 4, 5)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (5, 3, CAST(N'2022-11-03 22:13:00.000' AS DateTime), CAST(N'2022-11-13 01:02:00.000' AS DateTime), 3, 1, 7)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (6, 4, CAST(N'2022-11-08 01:43:00.000' AS DateTime), CAST(N'2022-11-18 22:48:00.000' AS DateTime), 4, 5, 8)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (7, 3, CAST(N'2022-11-10 16:51:00.000' AS DateTime), CAST(N'2022-12-21 14:39:00.000' AS DateTime), 4, 5, 2)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (8, 3, CAST(N'2022-12-23 11:35:00.000' AS DateTime), CAST(N'2022-12-04 08:38:00.000' AS DateTime), 1, 2, 4)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (9, 3, CAST(N'2022-11-16 01:30:00.000' AS DateTime), CAST(N'2022-11-24 21:58:00.000' AS DateTime), 4, 2, 8)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (10, 2, CAST(N'2022-12-05 19:12:00.000' AS DateTime), CAST(N'2022-12-07 14:52:00.000' AS DateTime), 5, 2, 7)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (11, 4, CAST(N'2022-12-20 20:14:00.000' AS DateTime), CAST(N'2022-12-22 19:49:00.000' AS DateTime), 3, 2, 3)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (12, 2, CAST(N'2022-12-23 00:17:00.000' AS DateTime), CAST(N'2022-12-27 19:34:00.000' AS DateTime), 2, 3, 6)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (13, 2, CAST(N'2022-12-01 03:03:00.000' AS DateTime), CAST(N'2022-12-06 14:23:00.000' AS DateTime), 3, 1, 9)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (14, 2, CAST(N'2022-11-14 06:01:00.000' AS DateTime), CAST(N'2022-11-10 01:07:00.000' AS DateTime), 3, 4, 5)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (15, 3, CAST(N'2022-11-03 22:13:00.000' AS DateTime), CAST(N'2022-11-13 01:02:00.000' AS DateTime), 3, 1, 7)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (16, 4, CAST(N'2022-11-08 01:43:00.000' AS DateTime), CAST(N'2022-11-18 22:48:00.000' AS DateTime), 4, 5, 8)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (17, 3, CAST(N'2022-11-10 16:51:00.000' AS DateTime), CAST(N'2022-11-21 14:39:00.000' AS DateTime), 4, 5, 2)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (18, 3, CAST(N'2022-12-23 11:35:00.000' AS DateTime), CAST(N'2022-12-25 08:38:00.000' AS DateTime), 1, 2, 4)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (19, 3, CAST(N'2022-11-16 01:30:00.000' AS DateTime), CAST(N'2022-11-24 21:58:00.000' AS DateTime), 4, 2, 8)
INSERT orders (id, status, order_date, shipped_date, store_id, staff_id, customer_id) VALUES (20, 2, CAST(N'2022-12-21 19:12:00.000' AS DateTime), CAST(N'2022-12-23 14:52:00.000' AS DateTime), 5, 2, 7)
SET IDENTITY_INSERT orders OFF

SET IDENTITY_INSERT order_details ON 
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (1, 20, CAST(95341098.00 AS Decimal(10, 2)), CAST(22.00 AS Decimal(4, 2)), NULL, 6, 4)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (2, 62, CAST(49133320.00 AS Decimal(10, 2)), CAST(14.00 AS Decimal(4, 2)), NULL, 8, 9)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (3, 24, CAST(28056384.00 AS Decimal(10, 2)), CAST(19.00 AS Decimal(4, 2)), NULL, 11, 9)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (4, 46, CAST(1989046.00 AS Decimal(10, 2)), CAST(25.00 AS Decimal(4, 2)), NULL, 18, 19)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (5, 8, CAST(93968244.00 AS Decimal(10, 2)), CAST(13.00 AS Decimal(4, 2)), NULL, 4, 2)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (6, 94, CAST(76516669.00 AS Decimal(10, 2)), CAST(26.00 AS Decimal(4, 2)), NULL, 9, 13)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (7, 87, CAST(42893123.00 AS Decimal(10, 2)), CAST(24.00 AS Decimal(4, 2)), NULL, 17, 5)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (8, 46, CAST(55214364.00 AS Decimal(10, 2)), CAST(29.00 AS Decimal(4, 2)), NULL, 14, 18)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (9, 84, CAST(15686824.00 AS Decimal(10, 2)), CAST(26.00 AS Decimal(4, 2)), NULL, 17, 8)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (10, 100, CAST(23749781.00 AS Decimal(10, 2)), CAST(18.00 AS Decimal(4, 2)), NULL, 12, 5)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (11, 20, CAST(95341098.00 AS Decimal(10, 2)), CAST(22.00 AS Decimal(4, 2)), NULL, 6, 4)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (12, 62, CAST(49133320.00 AS Decimal(10, 2)), CAST(14.00 AS Decimal(4, 2)), NULL, 8, 9)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (13, 24, CAST(28056384.00 AS Decimal(10, 2)), CAST(19.00 AS Decimal(4, 2)), NULL, 11, 9)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (14, 46, CAST(1989046.00 AS Decimal(10, 2)), CAST(25.00 AS Decimal(4, 2)), NULL, 18, 19)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (15, 8, CAST(93968244.00 AS Decimal(10, 2)), CAST(13.00 AS Decimal(4, 2)), NULL, 4, 2)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (16, 94, CAST(76516669.00 AS Decimal(10, 2)), CAST(26.00 AS Decimal(4, 2)), NULL, 20, 13)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (17, 87, CAST(42893123.00 AS Decimal(10, 2)), CAST(24.00 AS Decimal(4, 2)), NULL, 17, 5)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (18, 46, CAST(55214364.00 AS Decimal(10, 2)), CAST(29.00 AS Decimal(4, 2)), NULL, 14, 18)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (19, 84, CAST(15686824.00 AS Decimal(10, 2)), CAST(26.00 AS Decimal(4, 2)), NULL, 17, 8)
INSERT order_details (id, quantity, list_price, discount, description, order_id, product_id) VALUES (20, 100, CAST(23749781.00 AS Decimal(10, 2)), CAST(18.00 AS Decimal(4, 2)), NULL, 12, 5)
SET IDENTITY_INSERT order_details OFF

SET IDENTITY_INSERT stocks ON 
INSERT stocks (id, quantity, store_id, product_id) VALUES (1, 69, 2, 15)
INSERT stocks (id, quantity, store_id, product_id) VALUES (2, 50, 3, 13)
INSERT stocks (id, quantity, store_id, product_id) VALUES (3, 81, 1, 9)
INSERT stocks (id, quantity, store_id, product_id) VALUES (4, 45, 4, 16)
INSERT stocks (id, quantity, store_id, product_id) VALUES (5, 29, 1, 20)
INSERT stocks (id, quantity, store_id, product_id) VALUES (6, 23, 5, 5)
INSERT stocks (id, quantity, store_id, product_id) VALUES (7, 77, 5, 9)
INSERT stocks (id, quantity, store_id, product_id) VALUES (8, 38, 2, 3)
INSERT stocks (id, quantity, store_id, product_id) VALUES (9, 15, 2, 7)
INSERT stocks (id, quantity, store_id, product_id) VALUES (10, 80, 2, 18)
SET IDENTITY_INSERT stocks OFF
GO
ALTER TABLE authorities  WITH CHECK ADD  CONSTRAINT FK_authorities_roles FOREIGN KEY(role_id)
REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE
ALTER TABLE authorities  WITH CHECK ADD  CONSTRAINT FK_authorities_customers FOREIGN KEY(customer_id)
REFERENCES customers (id) ON UPDATE CASCADE ON DELETE CASCADE
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