DROP TABLE IF EXISTS ACCOUNTS; 

CREATE TABLE ACCOUNTS ( num VARCHAR(250) PRIMARY KEY, balance double ); 

INSERT INTO ACCOUNTS (num, balance) VALUES ('1',1000.00), ('2',1000.00);