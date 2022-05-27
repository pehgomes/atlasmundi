alter table profile drop column if exists last_latLong;
alter table profile add column if not exists latitude float8;
alter table profile add column if not exists longitude float8;