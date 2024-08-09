FROM mysql:8-oracle

ENV MYSQL_ROOT_PASSWORD=root

COPY mysql8/privileges.sql /docker-entrypoint-initdb.d/1.sql
COPY mysql8/schema.sql /docker-entrypoint-initdb.d/2.sql
COPY mysql8/data.sql /docker-entrypoint-initdb.d/3.sql
COPY src/main/resources/data /docker-entrypoint-initdb.d/data

ADD mysql8/my.cnf /etc/mysql/conf.d

EXPOSE 3306
