CREATE TABLE IF NOT EXISTS `blocks` (
    id INT NOT NULL AUTO_INCREMENT ,
    startDate date not null,
    endDate date not null,
    ownerId INT,
    propertyId INT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE `blocks`
    ADD FOREIGN KEY (ownerId)
    references `users`(id);

ALTER TABLE `blocks`
    ADD FOREIGN KEY (propertyId)
    references `properties`(id);