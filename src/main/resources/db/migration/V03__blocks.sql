CREATE TABLE IF NOT EXISTS `blocks` (
    id INT NOT NULL AUTO_INCREMENT ,
    startDate date not null,
    endDate date not null,
    ownerId INT,
    propertyId INT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (ownerId) REFERENCES `users`(id) ON DELETE CASCADE,
    FOREIGN KEY (propertyId) REFERENCES `properties`(id) ON DELETE CASCADE
);