CREATE TYPE IF NOT EXISTS bank_acc_type AS ENUM ('Current', 'Savings');
CREATE TYPE IF NOT EXISTS bank_transaction_type AS ENUM ('Credit', 'Debit');

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(30) NOT NULL,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS admins (
    admin_id INT
) INHERITS(users);

CREATE TABLE IF NOT EXISTS tellers (
    teller_id INT
) INHERITS(users);

CREATE TABLE IF NOT EXISTS customers (
    cust_id SERIAL PRIMARY KEY,
    nric VARCHAR(8) NOT NULL UNIQUE,
    email VARCHAR(50),
    date_of_birth DATE,
    address VARCHAR(250),
    phone_no VARCHAR(25)
) INHERITS(users);

CREATE TABLE IF NOT EXISTS accounts (
    account_no SERIAL PRIMARY KEY,
    balance DECIMAL(19,4),
    acc_type bank_acc_type
);

CREATE TABLE IF NOT EXISTS cust_acc (
    cust_acc_id SERIAL PRIMARY KEY,
    account_no INT,
    cust_id INT,
    FOREIGN KEY(cust_id) REFERENCES customers(cust_id),
    FOREIGN KEY(account_no) REFERENCES accounts(account_no)
);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    transaction_ref INT,
    transaction_type bank_transaction_type,
    date TIMESTAMP,
    amount DECIMAL(10,2),
    account_no INT,
    cust_id INT,
    FOREIGN KEY(cust_id) REFERENCES customers(cust_id),
    FOREIGN KEY(account_no) REFERENCES accounts(account_no)
);