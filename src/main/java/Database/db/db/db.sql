Create  database mini_football_db
create user mini_football_db_manager with password '123456'
grant connect on database mini_football_db to mini_football_db_manager

\c mini_football_db
grant create on schema public to mini_football_db_manager
grant  default privileges in schema public
alter create, select, insert , update, delete on tables to mini_football_db_manager;
alter  default privileges in schema public
grant usage , select, update on sequences to mini_football_db_manager;