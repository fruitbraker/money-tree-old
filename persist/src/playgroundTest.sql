insert into expense_category(expense_category_id) values ('EXPENSE_CATEGORY_TEST');
INSERT INTO vendor(vendor_name) VALUES ('VENDOR_TEST');
INSERT INTO metadata(date_created, date_modified, notes) VALUES (now(), now(), 'TEST_NOTE');
INSERT INTO expense(transaction_date, transaction_amount, vendor_id, expense_category, metadata_id, hide) VALUES (now(), 1.23, 1, 'EXPENSE_CATEGORY_TEST', 1, true);