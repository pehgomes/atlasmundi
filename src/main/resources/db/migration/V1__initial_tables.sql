create table if not exists profile (
    profile_id uuid not null,
    birth_date timestamp with time zone NOT NULL,
    login varchar(50) ,
    name varchar(100) ,
    password varchar(255),
    phone_number varchar(100),
    tax_id varchar(14) not null,
    CONSTRAINT profile_id_pk PRIMARY KEY (profile_id),
    CONSTRAINT tax_id_uk UNIQUE (tax_id));
