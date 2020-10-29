create database pcpartscompare;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


SELECT item_name, initcap(substring(item_name, 1, position(' ' in item_name))) as brand
FROM public.items group by item_name ;


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