CREATE TABLE IF NOT EXISTS `bookings` (
    id INT NOT NULL AUTO_INCREMENT ,
    startDate date not null,
    endDate date not null,
    userId INT,
    propertyId INT,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES `users`(id) ON DELETE CASCADE,
    FOREIGN KEY (propertyId) REFERENCES `properties`(id) ON DELETE CASCADE
);