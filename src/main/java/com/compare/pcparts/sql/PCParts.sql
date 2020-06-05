
create TABLE Store (
                       id SERIAL PRIMARY KEY ,
                       storename varchar(30)NOT NULL UNIQUE ,
                       imageref varchar,
                       operatinghours varchar,
                       contactaddress varchar,
                       contactemail varchar,
                       contactnumber1 varchar,
                       contactnumber2 varchar,
                       contactnumber3 varchar
);

create TABLE CPU (
                              id SERIAL PRIMARY KEY ,
                              itemname char(30) NOT NULL unique ,
                              storeID int not null,
                              isavailable bool default true not null,
                              price varchar(10) default '0',
                              url varchar,
                              imageref varchar,
                              foreign key (storeID) references Store(id)
);

create TABLE GraphicCard (
                             id SERIAL PRIMARY KEY ,
                             itemname char(30) NOT NULL unique ,
                             storeID int not null,
                             isavailable bool default true not null,
                             price varchar(10) default '0',
                             url varchar,
                             imageref varchar,
                             foreign key (storeID) references Store(id)
);

create TABLE Ram (
                     id SERIAL PRIMARY KEY ,
                     itemname char(30) NOT NULL unique ,
                     storeID int not null,
                     isavailable bool default true not null,
                     price varchar(10) default '0',
                     url varchar,
                     imageref varchar,
                     foreign key (storeID) references Store(id)

);

create TABLE MotherBoard (
                             id SERIAL PRIMARY KEY ,
                             itemname char(30) NOT NULL unique ,
                             storeID int not null,
                             isavailable bool default true not null,
                             price varchar(10) default '0',
                             url varchar,
                             imageref varchar,
                             foreign key (storeID) references Store(id)
);

create TABLE PSU (
                     id SERIAL PRIMARY KEY ,
                     itemname char(30) NOT NULL unique ,
                     storeID int not null,
                     isavailable bool default true not null,
                     price varchar(10) default '0',
                     url varchar,
                     imageref varchar,
                     foreign key (storeID) references Store(id)
);

create TABLE Storage (
                         id SERIAL PRIMARY KEY ,
                         itemname char(30) NOT NULL unique ,
                         storeID int not null,
                         isavailable bool default true not null,
                         price varchar(10) default '0',
                         url varchar,
                         imageref varchar,
                         foreign key (storeID) references Store(id)
);

create TABLE CoolingFan (
                            id SERIAL PRIMARY KEY ,
                            itemname char(30) NOT NULL unique ,
                            storeID int not null,
                            isavailable bool default true not null,
                            price varchar(10) default '0',
                            url varchar,
                            imageref varchar,
                            foreign key (storeID) references Store(id)
);

create TABLE ComputerCase (
                              id SERIAL PRIMARY KEY ,
                              itemname char(30) NOT NULL unique ,
                              storeID int not null,
                              isavailable bool default true not null,
                              price varchar(10) default '0',
                              url varchar,
                              imageref varchar,
                              foreign key (storeID) references Store(id)
);

create TABLE Accessories (
                             id SERIAL PRIMARY KEY ,
                             itemname char(30) NOT NULL unique ,
                             storeID int not null,
                             isavailable bool default true not null,
                             price varchar(10) default '0',
                             url varchar,
                             imageref varchar,
                             foreign key (storeID) references Store(id)
);


