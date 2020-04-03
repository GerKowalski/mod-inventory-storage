-- add deleted_date field to the tables
-- Create views to filter rows with no deleted date

alter table ${myuniversity}_${mymodule}.instance add "deleted_date" timestamp;
alter table ${myuniversity}_${mymodule}.item add "deleted_date" timestamp;
alter table ${myuniversity}_${mymodule}.holdings_record add "deleted_date" timestamp;
--
CREATE OR REPLACE VIEW instance_view AS SELECT * from instance where deleted_date IS NULL;
CREATE OR REPLACE VIEW holdings_record_view AS SELECT * from holdings_record where deleted_date IS NULL;
CREATE OR REPLACE VIEW item_view AS SELECT * from item where deleted_date IS NULL;
