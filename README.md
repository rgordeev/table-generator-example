## Getting Started

### Run Application

* Run postgres in docker
```
docker-compose up 
```
* Run application from IDE or console

### Output

During application start you should see
```sql

create table id_gen (
   GEN_KEY varchar(255) not null,
    GEN_VALUE int8,
    primary key (GEN_KEY)
)
    
insert into id_gen(GEN_KEY, GEN_VALUE) values ('PERSON_ID',0)

create table person (
   id int4 not null,
    name varchar(255),
    primary key (id)
)
    
select
    tbl.GEN_VALUE 
from
    id_gen tbl 
where
    tbl.GEN_KEY=? for update
        of tbl

update
    id_gen 
set
    GEN_VALUE=?  
where
    GEN_VALUE=? 
    and GEN_KEY=?
select
    tbl.GEN_VALUE 
from
    id_gen tbl 
where
    tbl.GEN_KEY=? for update
        of tbl
update
    id_gen 
set
    GEN_VALUE=?  
where
    GEN_VALUE=? 
    and GEN_KEY=?

select
    tbl.GEN_VALUE 
from
    id_gen tbl 
where
    tbl.GEN_KEY=? for update
        of tbl

update
    id_gen 
set
    GEN_VALUE=?  
where
    GEN_VALUE=? 
    and GEN_KEY=?

insert 
into
    person
    (name, id) 
values
    (?, ?)
            
insert 
into
    person
    (name, id) 
values
    (?, ?)
            
insert 
into
    person
    (name, id) 
values
    (?, ?)

```

Database structure

```sql
create table id_gen
(
  gen_key   varchar(255) not null
    constraint id_gen_pkey
      primary key,
  gen_value bigint
);

alter table id_gen
  owner to postgres;

create table person
(
  id   integer not null
    constraint person_pkey
      primary key,
  name varchar(255)
);

alter table person
  owner to postgres;
```