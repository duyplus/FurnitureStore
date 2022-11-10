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
	state NVARCHAR (25),
	zip_code VARCHAR (5)
);
CREATE TABLE stores (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	phone VARCHAR (25),
	email VARCHAR (255),
	street NVARCHAR (255),
	city NVARCHAR (255),
	state NVARCHAR (10),
	zip_code VARCHAR (5)
);
CREATE TABLE staffs (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	fullname NVARCHAR (255) NOT NULL,
	email VARCHAR (255) NOT NULL UNIQUE,
	phone VARCHAR (15),
	active tinyint NOT NULL,
	store_id INT NOT NULL,
	manager_id INT
	-- Manage ID: 0 = Director; 1 = Staff
);
CREATE TABLE orders (
	id INT IDENTITY (1, 1) PRIMARY KEY,
	status tinyint NOT NULL,
	-- Status: 1 = Pending; 2 = Processing; 3 = Rejected; 4 = Completed
	order_date DATE NOT NULL,
	shipped_date DATE,
	customer_id INT NOT NULL,
	store_id INT NOT NULL,
	staff_id INT NOT NULL
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
	store_id INT,
	product_id INT,
	quantity INT,
	PRIMARY KEY (store_id, product_id)
);
GO
-- products
ALTER TABLE products ADD CONSTRAINT FK_products_categories FOREIGN KEY (category_id)
REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE products ADD CONSTRAINT FK_products_brands FOREIGN KEY (brand_id)
REFERENCES brands (id) ON DELETE CASCADE ON UPDATE CASCADE
-- staffs
ALTER TABLE staffs ADD CONSTRAINT FK_staffs_stores FOREIGN KEY (store_id)
REFERENCES stores (id) ON DELETE CASCADE ON UPDATE CASCADE
ALTER TABLE staffs ADD CONSTRAINT FK_staffs_staffs FOREIGN KEY (manager_id)
REFERENCES staffs (id) ON DELETE NO ACTION ON UPDATE NO ACTION
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