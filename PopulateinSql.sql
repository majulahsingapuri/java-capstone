
-- 1. View Customer + Their Accounts + Balance
SELECT create_customer('sridhar', 'password', 'sridhar', 'guzzu', 's99999999', 'sridhar@gmail.com', '2022-03-24'::DATE, 'ntuc', '99998888');
SELECT create_account(1,'SAVINGS');
SELECT create_account(1,'CURRENT');
SELECT create_account(1,'CURRENT');
UPDATE migrations_account SET balance = 10000 WHERE id = 1;
UPDATE migrations_account SET balance = 5000 WHERE id = 2;
SELECT first_name, last_name, account_no_id, account_type, balance FROM migrations_customer AS mc JOIN migrations_customeraccount AS mca ON mca.customer_id_id = mc.customer_id JOIN migrations_account AS ma ON ma.id = mca.account_no_id JOIN migrations_user AS mu ON mu.id = mc.customer_id WHERE customer_id = 1;

-- 2. View Customer + Their Transactions
SELECT create_customer('chandra', 'password', 'chandra', 'chandray', 's99999998', 'chandra@gmail.com', '2022-03-24'::DATE, 'ntuc', '99998887');
SELECT create_account(2,'SAVINGS');
UPDATE migrations_account SET balance = 999 WHERE id = 4;
INSERT INTO migrations_transaction(transaction_ref, transaction_type, date, amount, account_no_id, customer_id_id ) VALUES ('a81bc81b-dead-4e5d-abff-90865d1e13b1', 'DEBIT', '2004-10-19 10:23:54', 899, 4, 2);
UPDATE migrations_account SET balance = balance - 899 WHERE id = 4;
INSERT INTO migrations_transaction(transaction_ref, transaction_type, date, amount, account_no_id, customer_id_id ) VALUES ('a81bc81b-dead-4e5d-abff-90865d1e13b2', 'CREDIT', '2004-10-19 10:33:54', 899, 1, 1);
UPDATE migrations_account SET balance = balance + 899 WHERE id = 1;
SELECT first_name, last_name, amount, transaction_type, date, transaction_ref  FROM migrations_transaction AS mt JOIN migrations_customer AS mc ON mt.customer_id_id = mc.customer_id JOIN migrations_user AS mu ON mu.id = mc.customer_id WHERE customer_id = 1;

-- 3. View Logs Table
INSERT INTO migrations_log(user_ptr_id, date, data) VALUES (1, '2022-03-24' , '{"username": "sridhar", "task": "logging in", "error":"failed login"}'::json);
SELECT data, date FROM migrations_log;

-- 4. Display Users Table
SELECT * FROM migrations_user;

