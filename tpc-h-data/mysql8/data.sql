use tpch;

load data infile '/docker-entrypoint-initdb.d/data/region.tbl'
    into table region fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/nation.tbl'
    into table nation fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/customer.tbl'
    into table customer fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/part.tbl'
    into table part fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/supplier.tbl'
    into table supplier fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/partsupp.tbl'
    into table partsupp fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/orders.tbl'
    into table orders fields terminated by '|';
load data infile '/docker-entrypoint-initdb.d/data/lineitem.tbl'
    into table lineitem fields terminated by '|';
