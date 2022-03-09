CREATE  database stock_db

create user if not exists 'stock_user'@'localhost' identified by 'password'
grant all privileges on stock_db.* to 'stock_user'@'localhost';
flush privileges;