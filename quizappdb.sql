CREATE DATABASE quizdb;
USE quizdb;

CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, 
    role ENUM('user', 'admin') NOT NULL
    );

CREATE TABLE questions (
	question_id INT AUTO_INCREMENT PRIMARY KEY,
	question_text TEXT NOT NULL,
    option_1 VARCHAR(255) NOT NULL,
    option_2 VARCHAR(255) NOT NULL,
    option_3 VARCHAR(255) NOT NULL,
    option_4 VARCHAR(255) NOT NULL,
    correct_option INT NOT NULL CHECK (correct_option BETWEEN 1 AND 4),
    created_by INT,
    FOREIGN KEY (created_by) REFERENCES users(user_id)
    );

CREATE TABLE quiz_attempts (
	attempt_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    score INT,
    attempted_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    time_taken INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

USE quizdb;
-- add some sample question directly from the mysql workbench
INSERT INTO questions (question_text, option_1, option_2, option_3, option_4, correct_option, created_by) VALUES
('What is the purpose of a PRIMARY KEY in a database table?', 'To create a unique column', 'To create an index', 'To uniquely identify each record in a table', 'To store metadata', 3, 1),
('Which SQL command is used to delete a table from a database?', 'DROP TABLE', 'DELETE TABLE', 'REMOVE TABLE', 'CLEAR TABLE', 1, 1),
('In MySQL, what type of JOIN returns all rows from both tables as long as there is a match between columns?', 'INNER JOIN', 'OUTER JOIN', 'LEFT JOIN', 'RIGHT JOIN', 2, 1),
('Which command is used to remove all records from a table in SQL, including space allocated for the records?', 'TRUNCATE', 'DELETE', 'DROP', 'REMOVE', 1, 1),
('What SQL keyword is used to sort the result-set by one or more columns?', 'SORT', 'ORDER BY', 'GROUP BY', 'ORGANIZE', 2, 1),
('What does the COUNT() function do in SQL?', 'Counts rows matching a condition', 'Calculates the sum of values', 'Returns the average of values', 'Counts distinct values', 1, 1),
('Which data type in MySQL can hold large amounts of text?', 'VARCHAR', 'TEXT', 'INT', 'DATE', 2, 1),
('In a relational database, a relation is also known as a:', 'Column', 'Table', 'Row', 'Field', 2, 1),
('Which command is used to update data in a table?', 'UPDATE', 'MODIFY', 'CHANGE', 'ALTER', 1, 1),
('In MySQL, which operator is used to search for a specified pattern in a column?', 'SEARCH', 'LIKE', 'FIND', 'PATTERN', 2, 1),
('What does ACID stand for in the context of databases?', 'Atomicity, Consistency, Isolation, Durability', 'Accuracy, Completeness, Integration, Durability', 'Atomicity, Cohesion, Isolation, Distribution', 'Accuracy, Consistency, Integrity, Durability', 1, 1),
('Which of the following is not a type of index in MySQL?', 'FULLTEXT', 'BTREE', 'HASH', 'SEQUENTIAL', 4, 1),
('In MySQL, what is the default port number?', '3306', '8080', '1521', '1433', 1, 1),
('What is a foreign key?', 'A key that uniquely identifies each record in a table', 'A key that links one table to another', 'A unique identifier for a database', 'A key used for encryption', 2, 1),
('Which clause is used to filter results based on a specified condition?', 'FILTER BY', 'ORDER BY', 'WHERE', 'GROUP BY', 3, 1),
('In MySQL, which function is used to get the current date and time?', 'CURRENT()', 'NOW()', 'TODAY()', 'DATE()', 2, 1),
('How do you select all columns from a table named “employees”?', 'SELECT employees FROM *', 'SELECT * FROM employees', 'SELECT ALL FROM employees', 'SELECT employees.*', 2, 1),
('Which SQL clause is used to group records that have the same values?', 'GROUP BY', 'ORDER BY', 'FILTER', 'AGGREGATE', 1, 1),
('What is the purpose of the AUTO_INCREMENT attribute in MySQL?', 'To automatically reset values to zero', 'To automatically update a value based on a formula', 'To automatically increment a unique ID for each new record', 'To prevent duplicate entries', 3, 1),
('Which MySQL function returns the name of the current user?', 'USER()', 'CURRENT_USER()', 'SESSION_USER()', 'DB_USER()', 2, 1),
('In MySQL, which keyword is used to limit the number of rows returned by a query?', 'LIMIT', 'ROWNUM', 'MAX', 'COUNT', 1, 1),
('Which MySQL clause is used to create an alias for a table or column?', 'RENAME', 'ALIAS', 'AS', 'LIKE', 3, 1),
('What is a view in SQL?', 'A copy of a table', 'A virtual table based on the result of a SELECT query', 'A table with an index', 'A duplicate table with constraints', 2, 1),
('Which SQL command is used to add a new row in a table?', 'INSERT INTO', 'ADD ROW', 'NEW RECORD', 'CREATE ROW', 1, 1),
('What does the CONCAT() function do?', 'Calculates sum of values', 'Concatenates two or more strings', 'Counts records', 'Deletes data', 2, 1),
('Which storage engine in MySQL supports transactions?', 'InnoDB', 'MyISAM', 'MEMORY', 'CSV', 1, 1),
('Which SQL function returns the largest value in a numeric column?', 'MAX()', 'MIN()', 'SUM()', 'COUNT()', 1, 1),
('What is the default isolation level in MySQL?', 'READ COMMITTED', 'REPEATABLE READ', 'READ UNCOMMITTED', 'SERIALIZABLE', 2, 1),
('How do you remove a column from a table in MySQL?', 'ALTER TABLE ... REMOVE COLUMN', 'DELETE COLUMN ... FROM', 'ALTER TABLE ... DROP COLUMN', 'DROP TABLE COLUMN', 3, 1),
('Which of the following is a valid aggregate function in SQL?', 'SELECT()', 'GROUP()', 'SUM()', 'VIEW()', 3, 1),
('Which MySQL clause is used to return only unique values?', 'ONLY', 'UNIQUE', 'DISTINCT', 'EXCLUSIVE', 3, 1),
('Which MySQL data type is best for storing monetary values?', 'INT', 'VARCHAR', 'DECIMAL', 'TEXT', 3, 1),
('What is the primary purpose of indexes in a database?', 'To store data', 'To improve query performance', 'To encrypt data', 'To store backups', 2, 1),
('In SQL, which statement is used to create a new table?', 'CREATE TABLE', 'NEW TABLE', 'ADD TABLE', 'BUILD TABLE', 1, 1),
('Which clause is used to specify conditions for filtering records in SQL?', 'WHERE', 'ORDER BY', 'GROUP BY', 'FILTER BY', 1, 1),
('What does the SQL term “JOIN” refer to?', 'Combining columns', 'Combining rows', 'Combining tables', 'Combining databases', 3, 1),
('In SQL, which function calculates the sum of a numeric column?', 'SUM()', 'ADD()', 'TOTAL()', 'AGGREGATE()', 1, 1),
('Which operator is used to check for NULL values in SQL?', '= NULL', 'NULL', 'IS NULL', 'EQUALS NULL', 3, 1),
('How do you add a new column to an existing table in SQL?', 'ALTER TABLE ... ADD COLUMN', 'ADD COLUMN ... TO TABLE', 'NEW COLUMN ... IN TABLE', 'CREATE COLUMN ... IN TABLE', 1, 1),
('What does DDL stand for?', 'Data Description Language', 'Data Definition Language', 'Data Deletion Language', 'Data Detection Language', 2, 1),
('What does the SQL keyword “BETWEEN” do?', 'Specifies a range to filter records', 'Adds a condition to a query', 'Sorts records', 'Groups records by a condition', 1, 1),
('Which MySQL command is used to make an existing table copy with the same structure?', 'COPY TABLE', 'CLONE TABLE', 'CREATE TABLE ... AS SELECT', 'DUPLICATE TABLE', 3, 1),
('In MySQL, which command is used to modify the data in a table?', 'MODIFY', 'UPDATE', 'CHANGE', 'ALTER', 2, 1),
('How can you delete duplicate records in SQL?', 'DELETE DUPLICATE', 'REMOVE DUPLICATE', 'DELETE GROUP', 'Using GROUP BY with a DELETE subquery', 4, 1),
('Which statement is true about FOREIGN KEYS?', 'They are used to link multiple databases', 'They are used to link records between tables', 'They provide unique values for each record', 'They are used to encrypt data', 2, 1);


