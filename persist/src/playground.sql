set schema 'mtdev';

INSERT INTO expense_category(expense_category_id) VALUES ('EXPENSE_CATEGORY_1');
INSERT INTO expense_category(expense_category_id) VALUES ('EXPENSE_CATEGORY_2');
INSERT INTO expense_category(expense_category_id) VALUES ('TEST');

INSERT INTO vendor(vendor_name) VALUES ('VENDOR_1');
INSERT INTO vendor(vendor_name) VALUES ('VENDOR_2');
INSERT INTO vendor(vendor_name) VALUES ('VENDOR_TEST_INSERT');

INSERT INTO metadata(date_created, date_modified, notes) VALUES (now(), now(), 'TEST NOTE 1');
INSERT INTO metadata(date_created, date_modified, notes) VALUES (now(), now(), 'TEST NOTE 2');

INSERT INTO expense(transaction_date, transaction_amount, vendor_id, expense_category, metadata_id, hide) VALUES (now(), 9.99, 1, 'EXPENSE_CATEGORY_1', 1, false);
INSERT INTO expense(transaction_date, transaction_amount, vendor_id, expense_category, metadata_id, hide) VALUES (now(), 10.43, 2, 'EXPENSE_CATEGORY_2', 2, true);

SELECT * FROM expense_category;
SELECT * FROM vendor;
SELECT * FROM metadata;
SELECT * from expense;
