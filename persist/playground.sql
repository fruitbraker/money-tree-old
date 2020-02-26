INSERT INTO expense_category(expense_category_id) VALUES ('EXPENSE_CATEGORY_1');
INSERT INTO expense_category(expense_category_id) VALUES ('EXPENSE_CATEGORY_2');

INSERT INTO vendor(vendor_id, vendor_name) VALUES (1, 'VENDOR_1');
INSERT INTO vendor(vendor_id, vendor_name) VALUES (2, 'VENDOR_2');

INSERT INTO metadata(metadata_id, date_created, date_modified, notes) VALUES (1, now(), now(), 'TEST NOTE 1');
INSERT INTO metadata(metadata_id, date_created, date_modified, notes) VALUES (2, now(), now(), 'TEST NOTE 2');

INSERT INTO expense(expense_id, transaction_date, transaction_amount, vendor_id, expense_category, metadata, hide) VALUES (1, now(), 1.23, 1, 'EXPENSE_CATEGORY_1', 1, false);
INSERT INTO expense(expense_id, transaction_date, transaction_amount, vendor_id, expense_category, metadata, hide) VALUES (2, now(), 4.56, 2, 'EXPENSE_CATEGORY_2', 2, true);

SELECT * FROM expense_category;
SELECT * FROM vendor;
SELECT * FROM metadata;
SELECT * from expense;
