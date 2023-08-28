CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY not null,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

 CREATE TABLE criticas (
    id BIGINT NOT NULL PRIMARY KEY,
    filme VARCHAR(150) NOT NULL,
    critica_text VARCHAR(3000) NOT NULL,
    datetime TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE POSTS (
   id bigint unsigned not null auto_increment primary key,
   filme VARCHAR(150) NOT NULL,
   critica_text VARCHAR(3000) NOT NULL,
   dateTime timestamp not null,
   user_id bigint not null,
   foreign key (user_id) references users(id)
)