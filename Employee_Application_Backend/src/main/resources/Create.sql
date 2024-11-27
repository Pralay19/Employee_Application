CREATE TABLE departments (depid BIGINT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(255) NOT NULL UNIQUE,
                          capacity INT NOT NULL,current_capacity INT DEFAULT 13);
