package com.compare.pcparts.mapper;

import com.compare.pcparts.pcandparts.PcPartsItem;

import com.compare.pcparts.store.items.StoreContactDetailItem;
import com.compare.pcparts.store.items.StoreItem;
import com.compare.pcparts.store.items.StoreOperatingTimeItem;
import com.compare.pcparts.store.items.StoreUrlItem;
import com.compare.pcparts.store.items.StoreXPathItem;
import com.compare.pcparts.store.miniitem.MiniStoreItem;
import com.compare.pcparts.store.miniitem.MiniStoreUrlItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PcPartsMapper
{
    void insertCpu(PcPartsItem pcPartsItem);
    List<PcPartsItem> getAllCpu();

    void insertStore(StoreItem storeItem);
    void insertStoreContactDetail(StoreContactDetailItem storeContactDetailItem);
    void insertStoreItemsUrl(StoreUrlItem storeUrlItem);
    void insertStoreOperatingTime(StoreOperatingTimeItem storeOperatingTimeItem);
    void insertStoreXPath(StoreXPathItem storeXPathItem);

    List<StoreItem> getStoreInfo();
    List<StoreContactDetailItem> getStoreContactInfo();
    List<StoreOperatingTimeItem> getStoreOperatingTime();

    List<MiniStoreItem> getMiniStoreInfo();
    List<StoreXPathItem> getStoreXPath(int id);
    List<MiniStoreUrlItem> getStoreUrl(int id);

}
