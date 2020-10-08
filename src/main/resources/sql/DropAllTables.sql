create database pcpartscompare;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

insert into store (id, store_name, url, image_ref, prices_including_vat, vat_percent)
 values(1,'pcandparts','pcandparts.com',null,null,null) ;

 insert into cpu
         (
         id,
         item_name,
         store_id,
         is_available,
         price,
         currency,
         url,
         image_ref)
          values (1,'qwer',1,true,'1234','cpuultra.com',null);