create database pcpartscompare;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

insert into store (id, storename, imageref, operatinghours, contactaddress, contactemail, contactnumber1, contactnumber2, contactnumber3)
 values(1,'pcandparts.com',null,null,null,null,null,null,null) ;

 insert into cpu
         (
         id,
         itemname,
         storeid,
         isavailable,
         price,
         url,
         imageref)
          values (1,'qwer',1,true,'1234','cpuultra.com',null);