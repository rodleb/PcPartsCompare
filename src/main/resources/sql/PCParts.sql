
create TABLE test.test_items (
 id SERIAL PRIMARY KEY ,
 item_name varchar NOT NULL unique ,
 price varchar,
 url varchar UNIQUE
);