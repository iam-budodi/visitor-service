
    create sequence visitor_id_sq start with 1 increment by 1;

    create table visitors (
        lastUpdated timestamp(6),
        visitor_id bigint not null,
        phone_number varchar(13) not null constraint valid_phone_no check (length(phone_number)=13),
        id_number varchar(25) not null constraint valid_id_number check ((id_type = 'NATIONAL_ID' AND length(id_number) = 20) OR (id_type = 'DRIVER_LICENSE' AND length(id_number) = 10) OR (id_type = 'PASSPORT' AND length(id_number) = 9)),
        first_name varchar(64) not null,
        last_name varchar(64) not null,
        middle_name varchar(64),
        email varchar(100) not null,
        id_type varchar(255) not null check (id_type in ('PASSPORT','NATIONAL_ID','DRIVER_LICENSE')),
        primary key (visitor_id),
        constraint visitor_id_uq unique (id_type, id_number),
        unique (id_number)
    );

    create index visitor_idx 
       on visitors (phone_number desc, id_type, id_number desc);
