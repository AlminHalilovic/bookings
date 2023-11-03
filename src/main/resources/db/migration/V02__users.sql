CREATE TABLE IF NOT EXISTS `users` (
    id INT NOT NULL AUTO_INCREMENT ,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `roles` (
    id INT NOT NULL AUTO_INCREMENT ,
    name varchar(255) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS `users_roles` (
    userId int not null,
    roleId int not null,
    primary key (userId, roleId),
    constraint fk_user
        foreign key (userId) references `users` (id),
        constraint fk_role
                foreign key (roleId) references `roles` (id)
);

ALTER TABLE `properties`
    ADD FOREIGN KEY (ownerId)
    references `users`(id);