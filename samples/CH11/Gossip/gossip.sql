DROP SCHEMA IF EXISTS gossip;
CREATE SCHEMA gossip;
USE gossip;
CREATE TABLE t_account (
  name VARCHAR(15) NOT NULL,
  password VARCHAR(32) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
) CHARSET=UTF8;
CREATE TABLE t_blah (
    name VARCHAR(15) NOT NULL,
    date TIMESTAMP NOT NULL,
    txt TEXT NOT NULL,
    FOREIGN KEY (name) REFERENCES t_account(name) 
) CHARSET=UTF8;
CREATE TABLE t_account_role (
    name VARCHAR(15) NOT NULL,
    role VARCHAR(15) NOT NULL,
    PRIMARY KEY (name, role)
) CHARSET=UTF8;