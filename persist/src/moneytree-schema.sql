CREATE TABLE IF NOT EXISTS expense_category
(
	id VARCHAR(32) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS expense 
(
	id SERIAL PRIMARY KEY,
	test_text VARCHAR(32),
	category VARCHAR(32) REFERENCES expense_category(id),
);