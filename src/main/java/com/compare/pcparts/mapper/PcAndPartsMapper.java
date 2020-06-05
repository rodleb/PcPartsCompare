package com.compare.pcparts.mapper;

import com.compare.pcparts.pcandparts.PcAndPartsItem;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface PcAndPartsMapper
{
    /*
    @Insert("insert into accessories(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertAccessories(Item item);
    @Insert("insert into computercase(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertComputerCase(Item item);
    @Insert("insert into coolingfan(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertCoolingfan(Item item);
    @Insert("insert into cpu(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertGraphicCard(Item item);
    @Insert("insert into motherboard(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertMotherboard(Item item);
    @Insert("insert into psu(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertPsu(Item item);
    @Insert("insert into ram(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertRam(Item item);
    @Insert("insert into storage(id,itemname, storeid, isavailable, price, url, imageref) values(default,#{itemname} ,#{storeid}, #{isavailable},#{price},#{url},#{imageref})")
    void insertStorage(Item item);

    @Insert("insert into store(id,storename, imageref,operatinghours, contactaddress, contactemail, contactnumber1,contactnumber2,contactnumber3) values(default,#{storename} , #{imageref},#{operatinghours},#{contactaddress},#{contactemail},#{contactnumber1},#{cotactnumber2},#{contactnumber3})")
    void insertStore(StoreInfo storeInfo);

   // @Select(value = "select * from cpu")
   // List<Item> findAllcpu();
*/

    void insertCpu(PcAndPartsItem pcAndPartsItem);

}
