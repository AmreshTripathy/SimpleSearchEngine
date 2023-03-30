SHOW DATABASES;

CREATE DATABASE search_engine_database;

DROP DATABASE search_engine_database;

USE search_engine_database;

CREATE DATABASE simple_search_engine_java;

USE simple_search_engine_java;

CREATE TABLE pages(
   pageTitle VARCHAR(200),
   pageLink TEXT,
   pageText MEDIUMTEXT
);

SELECT * FROM pages;

SELECT pageTitle,pageLink,(LENGTH(LOWER(pageText))-LENGTH(REPLACE(LOWER(pageText),'java','')))/LENGTH('java') AS count_occurence 
FROM pages ORDER BY 
count_occurence DESC LIMIT 30;

CREATE TABLE `history`(
   keyword TEXT,
   link TEXT
);

SELECT * FROM `history`;

