create database `tpch`;
create user 'tpch'@'%' identified by 'tpchPa55';
grant all on `tpch`.* to 'tpch'@'%';
flush privileges;
