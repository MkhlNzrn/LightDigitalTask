
CREATE TABLE IF NOT EXISTS orgs (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      org_name VARCHAR(255) NOT NULL,
                      inn VARCHAR(20) NOT NULL
);
CREATE TABLE IF NOT EXISTS participant (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             full_name VARCHAR(255),
                             age INT,
                             pcr_test_passed TINYINT(1),
                             event_id BIGINT,
                             balance INT
);
CREATE TABLE IF NOT EXISTS users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       roles VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS contract_requests (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 requester_name VARCHAR(255),
                                                 request_description VARCHAR(255),
                                                 status VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS event (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255),
                                     contract_id BIGINT,
                                     coast INT
);


