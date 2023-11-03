CREATE TABLE IF NOT EXISTS `properties` (
    id INT NOT NULL AUTO_INCREMENT ,
    ownerId INT,
    name varchar(255),
    location varchar(255),
    PRIMARY KEY (id)
);