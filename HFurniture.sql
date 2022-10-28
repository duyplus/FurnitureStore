USE master
go
CREATE DATABASE HFurniture
go
USE HFurniture
go
CREATE TABLE Categories (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL
);

CREATE TABLE Brands (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL
);

CREATE TABLE Products (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	brand_id INT NOT NULL,
	category_id INT NOT NULL,
	model_year SMALLINT NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL
);

CREATE TABLE Customers (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	fullname NVARCHAR (255) NOT NULL,
	phone VARCHAR (15) NOT NULL,
	email VARCHAR (255) NOT NULL,
	street NVARCHAR (255),
	city NVARCHAR (100),
	state NVARCHAR (25),
	zip_code VARCHAR (5)
);

CREATE TABLE Stores (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	phone VARCHAR (25),
	email VARCHAR (255),
	street NVARCHAR (255),
	city NVARCHAR (255),
	state NVARCHAR (10),
	zip_code VARCHAR (5)
);

CREATE TABLE Staffs (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	fullname NVARCHAR (255) NOT NULL,
	email VARCHAR (255) NOT NULL UNIQUE,
	phone VARCHAR (15),
	active tinyint NOT NULL,
	store_id INT NOT NULL,
	manager_id INT
	-- Manage ID: null = Director; 1 = Staff
);

CREATE TABLE Orders (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	status tinyint NOT NULL,
	-- Status: 1 = Pending; 2 = Processing; 3 = Rejected; 4 = Completed
	order_date DATE NOT NULL,
	shipped_date DATE,
	customer_id INT NOT NULL,
	store_id INT NOT NULL,
	staff_id INT NOT NULL
);

CREATE TABLE OrderDetails (
	id INT PRIMARY KEY,
	quantity INT NOT NULL,
	list_price DECIMAL (10, 2) NOT NULL,
	discount DECIMAL (4, 2) NOT NULL DEFAULT 0,
	order_id INT NOT NULL,
	product_id INT NOT NULL
);

CREATE TABLE Stocks (
	store_id INT,
	product_id INT,
	quantity INT,
	PRIMARY KEY (store_id, product_id)
);
go
-- Products
ALTER TABLE Products ADD CONSTRAINT FK_Products_Categories FOREIGN KEY (category_id)
REFERENCES Categories (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE Products ADD CONSTRAINT FK_Products_Brands FOREIGN KEY (brand_id)
REFERENCES Brands (id) ON DELETE CASCADE ON UPDATE CASCADE
-- Staffs
ALTER TABLE Staffs ADD CONSTRAINT FK_Staffs_Stores FOREIGN KEY (store_id)
REFERENCES Stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE Staffs ADD CONSTRAINT FK_Staffs_Staffs FOREIGN KEY (manager_id)
REFERENCES Staffs (id) ON DELETE NO ACTION ON UPDATE NO ACTION
-- Orders
ALTER TABLE Orders ADD CONSTRAINT FK_Orders_Customers FOREIGN KEY (customer_id)
REFERENCES Customers (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE Orders ADD CONSTRAINT FK_Orders_Stores FOREIGN KEY (store_id)
REFERENCES Stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE Orders ADD CONSTRAINT FK_Orders_Staffs FOREIGN KEY (staff_id)
REFERENCES Staffs (id) ON DELETE NO ACTION ON UPDATE NO ACTION
-- OrderDetails
ALTER TABLE OrderDetails ADD CONSTRAINT FK_OrderDetails_Orders FOREIGN KEY (id)
REFERENCES Orders (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE OrderDetails ADD CONSTRAINT FK_OrderDetails_Products FOREIGN KEY (product_id)
REFERENCES Products (id) ON DELETE CASCADE ON UPDATE CASCADE
-- Stocks
ALTER TABLE Stocks ADD CONSTRAINT FK_Stocks_Stores FOREIGN KEY (store_id)
REFERENCES Stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE Stocks ADD CONSTRAINT FK_Stocks_Products FOREIGN KEY (product_id)
REFERENCES Products (id) ON DELETE CASCADE ON UPDATE CASCADE