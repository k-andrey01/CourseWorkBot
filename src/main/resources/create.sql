create table organization(
"id" serial primary key,
"name" text default '' not null);

create table "type"(
"id" serial primary key,
"name" text default '' not null); 

create table links(
"id" serial primary key,
"org_name" integer references "organization"("id"),
"type_id" integer references "type"("id"),
"text" text default '' not null);
create table food(
"id" serial primary key,
"name" text default '' not null,
"address" text default '' not null,
"site" text default '');

create table place(
"id" serial primary key,
"name" text default '' not null,
"address" text default '' not null,
"phone" text default '');

create table term(
"id" serial primary key,
"name" text default '' not null,
"text" text default '' not null)
