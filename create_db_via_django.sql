BEGIN;
--
-- Create model Account
--
CREATE TABLE "migrations_account" ("id" bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, "balance" numeric(19, 2) NOT NULL, "account_type" text NOT NULL);
--
-- Create model User
--
CREATE TABLE "migrations_user" ("id" bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, "username" varchar NOT NULL UNIQUE, "password" varchar NOT NULL, "first_name" varchar NOT NULL, "last_name" varchar NOT NULL);
--
-- Create model Admin
--
CREATE TABLE "migrations_admin" ("user_ptr_id" bigint NOT NULL UNIQUE, "admin_id" integer NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY);
--
-- Create model Customer
--
CREATE TABLE "migrations_customer" ("user_ptr_id" bigint NOT NULL UNIQUE, "customer_id" integer NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, "nric" varchar(9) NOT NULL UNIQUE, "email" varchar(254) NOT NULL UNIQUE, "date_of_birth" timestamp with time zone NOT NULL, "address" text NOT NULL, "phone_no" varchar NOT NULL UNIQUE);
--
-- Create model Teller
--
CREATE TABLE "migrations_teller" ("user_ptr_id" bigint NOT NULL UNIQUE, "teller_id" integer NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY);
--
-- Create model Transaction
--
CREATE TABLE "migrations_transaction" ("id" bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, "transaction_ref" uuid NOT NULL UNIQUE, "transaction_type" text NOT NULL, "date" timestamp with time zone NOT NULL, "amount" numeric(10, 2) NOT NULL, "account_no_id" bigint NOT NULL);
--
-- Create model CustomerAccount
--
CREATE TABLE "migrations_customeraccount" ("id" bigint NOT NULL PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, "account_no_id" bigint NOT NULL);
--
-- Create constraint valid_account_type on model account
--
ALTER TABLE "migrations_account" ADD CONSTRAINT "valid_account_type" CHECK ("account_type" IN ('CURRENT', 'SAVINGS'));
--
-- Add field customer_id to transaction
--
ALTER TABLE "migrations_transaction" ADD COLUMN "customer_id_id" integer NOT NULL CONSTRAINT "migrations_transacti_customer_id_id_d37cd9aa_fk_migration" REFERENCES "migrations_customer"("customer_id") DEFERRABLE INITIALLY DEFERRED; SET CONSTRAINTS "migrations_transacti_customer_id_id_d37cd9aa_fk_migration" IMMEDIATE;
--
-- Add field customer_id to customeraccount
--
ALTER TABLE "migrations_customeraccount" ADD COLUMN "customer_id_id" integer NOT NULL CONSTRAINT "migrations_customera_customer_id_id_d5f2c36a_fk_migration" REFERENCES "migrations_customer"("customer_id") DEFERRABLE INITIALLY DEFERRED; SET CONSTRAINTS "migrations_customera_customer_id_id_d5f2c36a_fk_migration" IMMEDIATE;
--
-- Create constraint valid_transaction_type on model transaction
--
ALTER TABLE "migrations_transaction" ADD CONSTRAINT "valid_transaction_type" CHECK ("transaction_type" IN ('DEBIT', 'CREDIT'));
CREATE INDEX "migrations_user_username_ca7c746f_like" ON "migrations_user" ("username" varchar_pattern_ops);
ALTER TABLE "migrations_admin"
ADD CONSTRAINT "migrations_admin_user_ptr_id_5c496ef7_fk_migrations_user_id" FOREIGN KEY ("user_ptr_id") REFERENCES "migrations_user" ("id") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "migrations_customer"
ADD CONSTRAINT "migrations_customer_user_ptr_id_9e5a77dc_fk_migrations_user_id" FOREIGN KEY ("user_ptr_id") REFERENCES "migrations_user" ("id") DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX "migrations_customer_nric_7c085f58_like" ON "migrations_customer" ("nric" varchar_pattern_ops);
CREATE INDEX "migrations_customer_email_4e9146df_like" ON "migrations_customer" ("email" varchar_pattern_ops);
CREATE INDEX "migrations_customer_phone_no_5f9b64dc_like" ON "migrations_customer" ("phone_no" varchar_pattern_ops);
ALTER TABLE "migrations_teller"
ADD CONSTRAINT "migrations_teller_user_ptr_id_7b2a3d16_fk_migrations_user_id" FOREIGN KEY ("user_ptr_id") REFERENCES "migrations_user" ("id") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "migrations_transaction"
ADD CONSTRAINT "migrations_transacti_account_no_id_479041b0_fk_migration" FOREIGN KEY ("account_no_id") REFERENCES "migrations_account" ("id") DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX "migrations_transaction_account_no_id_479041b0" ON "migrations_transaction" ("account_no_id");
ALTER TABLE "migrations_customeraccount"
ADD CONSTRAINT "migrations_customera_account_no_id_2cb4d181_fk_migration" FOREIGN KEY ("account_no_id") REFERENCES "migrations_account" ("id") DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX "migrations_customeraccount_account_no_id_2cb4d181" ON "migrations_customeraccount" ("account_no_id");
CREATE INDEX "migrations_transaction_customer_id_id_d37cd9aa" ON "migrations_transaction" ("customer_id_id");
CREATE INDEX "migrations_customeraccount_customer_id_id_d5f2c36a" ON "migrations_customeraccount" ("customer_id_id");
COMMIT;