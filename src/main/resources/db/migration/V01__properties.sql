CREATE TABLE IF NOT EXISTS `properties` (
    id INT NOT NULL AUTO_INCREMENT ,
    ownerId INT,
    name varchar(255),
    location varchar(255),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);