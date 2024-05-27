USE food_delivery;

DROP TABLE IF exists Venues;
DROP TABLE IF exists Users;
DROP TABLE IF exists Orders;
DROP TABLE IF exists Deliveries;
DROP TABLE IF exists MenuItems;
DROP TABLE IF exists OrderItems;
DROP TABLE IF exists Roles;
DROP TABLE IF exists OrderStatus;

CREATE TABLE Roles (
	Id INT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    CONSTRAINT UQ_ROLE_NAME unique(`Name`)
);

CREATE TABLE OrderStatus (
	Id INT PRIMARY KEY,
    `Name` VARCHAR(255),
    CONSTRAINT UQ_ORDER_STATUS_NAME unique(`Name`)
);

CREATE TABLE Users (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    RoleId INT NOT NULL,
    `Name` VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    CONSTRAINT UQ_USER_EMAIL unique(Email),
    CONSTRAINT UQ_USER_NAME unique(`Name`),
    CONSTRAINT FK_USER_ROLE foreign key (RoleId) references Roles(Id)
);

CREATE TABLE Venues (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    Phone_number VARCHAR(15) NOT NULL,
    UserId INT NOT NULL,
    CONSTRAINT UQ_VENUE_NAME unique(`Name`),
    CONSTRAINT UQ_VENUE_PHONE unique(Phone_number),
    CONSTRAINT FK_VENUE_USER foreign key(UserId) references Users(Id)
);

CREATE TABLE Orders (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    UserId INT NOT NULL,
    VenueId INT NOT NULL,
    `Date` TIMESTAMP DEFAULT current_timestamp,
    OrderStatusId INT NOT NULL,
    CONSTRAINT FK_ORDER_USER FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_ORDER_VENUE FOREIGN KEY (VenueId) REFERENCES Venues(Id),
    CONSTRAINT FK_ORDER_STATUS FOREIGN KEY (OrderStatusId) REFERENCES OrderStatus(Id)    
);

CREATE TABLE MenuItems (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    VenueId INT NOT NULL,
    `Name` VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    `Description` VARCHAR(255),
    CONSTRAINT FK_MENU_ITEMS_VENUE FOREIGN KEY (VenueId) REFERENCES Venues(Id),
    CONSTRAINT UQ_MENU_ITEMS_NAME unique(`Name`)
);

CREATE TABLE OrderItems (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    OrderId INT NOT NULL,
    ItemId INT NOT NULL,
    Quantity INT NOT NULL,
    CONSTRAINT FK_ORDER_ITEM_ORDER FOREIGN KEY (OrderId) REFERENCES Orders(Id),
    CONSTRAINT FK_ORDER_ITEM_ITEM FOREIGN KEY (ItemId) REFERENCES MenuItems(Id)
);

INSERT INTO Roles VALUES 
(1, 'Client'),
(2, 'Owner');

INSERT INTO OrderStatus VALUES
(1, 'Pending'),
(2, 'Done');