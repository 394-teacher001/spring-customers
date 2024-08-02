-- データベース・データベースユーザの初期化
DROP DATABASE IF EXISTS db_customers;
DROP ROLE IF EXISTS usr_customers;
-- データベースユーザ・データベースの生成
CREATE ROLE usr_customers WITH PASSWORD 'himitu' LOGIN;
CREATE DATABASE db_customers OWNER usr_customers ENCODING 'utf8';