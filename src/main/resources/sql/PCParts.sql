create TABLE store (
    id SERIAL PRIMARY KEY ,
    store_name varchar NOT NULL ,
    url varchar,
    image_ref varchar,
    prices_including_vat bool,
    vat_percent numeric
);

create TABLE store_operating_time (
    id SERIAL PRIMARY KEY,
    store_id int NOT NULL,
    operating_hours_weekdays varchar,
    operating_hours_weekend varchar,
    operating_days varchar,
    foreign key (store_id) references store(id)
);

create TABLE store_contact_detail (
    id SERIAL PRIMARY KEY,
    store_id int NOT NULL,
    contact_address varchar,
    contact_email varchar,
    contact_number varchar,
    foreign key (store_id) references store(id)
);

create TABLE store_items_url (
    id SERIAL PRIMARY key,
    store_id int NOT NULL,
    part_name varchar NOT NULL,
    page_url varchar,
    foreign key (store_id) references store(id)
);

create TABLE store_xpath (
    id SERIAL PRIMARY KEY,
    store_id int NOT NULL,
    xpath varchar,
    product_name_xpath varchar,
    product_price_xpath varchar,
    product_url_xpath varchar,
    alt_xpath_1 varchar,
    alt_xpath_2 varchar,
    alt_xpath_3 varchar,
    foreign key (store_id) references store(id)
);

create TABLE cpu (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE graphics_card (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE memory (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE motherboard (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE power_supply_unit (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE storage (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE cooling_fan (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE computer_cases (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);

create TABLE accessories (
    id SERIAL PRIMARY KEY ,
    item_name char(300) NOT NULL ,
    store_id int not null,
    is_available bool default true,
    price varchar(500),
    currency varchar(10),
    url varchar(500) unique not null,
    image_ref varchar(500),
    foreign key (store_id) references store(id)
);


