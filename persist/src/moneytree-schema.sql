CREATE SCHEMA IF NOT EXISTS mtdev;

SET SCHEMA 'mtdev';

CREATE TABLE IF NOT EXISTS expense_category
(
    expense_category_id VARCHAR(32) PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS income_category
(
	income_category_id VARCHAR(32) PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS vendor
(
	vendor_id BIGINT PRIMARY KEY NOT NULL,
	vendor_name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS metadata
(
	metadata_id BIGINT PRIMARY KEY NOT NULL,
	date_created TIMESTAMP DEFAULT now() NOT NULL,
    date_modified TIMESTAMP DEFAULT now() NOT NULL,
    notes VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS expense
(
    expense_id BIGINT PRIMARY KEY NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    transaction_amount MONEY NOT NULL,
    vendor_id BIGINT REFERENCES vendor(vendor_id) NOT NULL,
    expense_category VARCHAR(32) REFERENCES expense_category(expense_category_id) NOT NULL,
    metadata BIGINT REFERENCES metadata(metadata_id) NOT NULL,
    hide boolean DEFAULT false NOT NULL
);

CREATE TABLE IF NOT EXISTS budget_target
(
	budget_target_id BIGINT PRIMARY KEY NOT NULL,
	expense_category VARCHAR(32) REFERENCES expense_category(expense_category_id) NOT NULL,
	amount BIGINT NOT NULL,
	metadata BIGINT REFERENCES metadata(metadata_id) NOT NULL
);

CREATE TABLE IF NOT EXISTS income
(
	income_id BIGINT PRIMARY KEY NOT NULL,
	income_source VARCHAR(32) NOT NULL,
	income_category VARCHAR(32) REFERENCES income_category(income_category_id) NOT NULL,
    transaction_amount MONEY NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
	metadata BIGINT REFERENCES metadata(metadata_id) NOT NULL
);