CREATE TABLE ROLES
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CREATED_AT timestamp NULL,
    `DESCRIPTION` VARCHAR(20) NULL,
    CONSTRAINT PK_ROLES PRIMARY KEY (ID)
);


CREATE TABLE USERS
(
    ID BIGINT AUTO_INCREMENT NOT NULL,
    CREATED_AT timestamp NULL,
    FULLNAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    STATUS VARCHAR(255) NOT NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (ID)
);

CREATE TABLE USER_ROLE
(
    ROLE_ID BIGINT NOT NULL,
    USER_ID BIGINT NOT NULL,
    CONSTRAINT PK_USER_ROLE PRIMARY KEY (ROLE_ID, USER_ID)
);

ALTER TABLE USER_ROLE
    ADD CONSTRAINT FK_USER_ROLE_ON_ROLE FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID);

ALTER TABLE USER_ROLE
    ADD CONSTRAINT FK_USER_ROLE_ON_USER FOREIGN KEY (USER_ID) REFERENCES USERS (ID);





