create TABLE Store (
    id SERIAL PRIMARY KEY ,
    storename varchar NOT NULL ,
    imageref varchar,
    operatinghoursweekdays varchar,
    operatinghoursweekend varchar,
    operatingdays varchar,
    priceswithvat bool,
    vatpercent numeric,
    contactaddress varchar,
    contactemail varchar,
    contactnumber1 varchar,
    contactnumber2 varchar,
    contactnumber3 varchar
);

create TABLE CPU (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE GraphicCard (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE Ram (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE MotherBoard (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE PSU (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE Storage (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE CoolingFan (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE ComputerCase (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);

create TABLE Accessories (
    id SERIAL PRIMARY KEY ,
    itemname char(300) NOT NULL ,
    storeID int not null,
    isavailable bool default true,
    price varchar(500),
    url varchar(500) unique not null,
    imageref varchar(500),
    foreign key (storeID) references Store(id)
);


