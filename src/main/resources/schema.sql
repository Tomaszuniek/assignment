CREATE TABLE USERS (
    ID INT PRIMARY KEY,
    POINTS INT
);

CREATE TABLE TRANSACTIONS (
    ID INT PRIMARY KEY,
    AMOUNT INT,
    USER_ID INT,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);