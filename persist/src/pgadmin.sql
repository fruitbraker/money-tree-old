set schema 'playground';

CREATE TABLE IF NOT EXISTS expense_category
(
    expense_category_id VARCHAR(32) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS expense 
(
    expense_id SERIAL PRIMARY KEY,
    test_text VARCHAR(32),
    category VARCHAR(32) REFERENCES expense_category(expense_category_id)
);

INSERT INTO expense_category VALUES ('foo1'), ('foo2'), ('foo3');
INSERT INTO expense_category VALUES ('foo6');

INSERT INTO expense VALUES (2, 'test test', 'foo6');

SELECT * FROM expense_category;
SELECT * FROM expense;
SELECT * FROM expense, expense_category;
