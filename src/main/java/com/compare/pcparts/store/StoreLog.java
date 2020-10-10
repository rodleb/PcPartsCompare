package com.compare.pcparts.store;

import com.compare.pcparts.store.items.*;

import java.util.List;

public interface StoreLog
{
	public void importStore();
	public void importOperatingTime();
	public void importContactDetail();
	public void importStoreItemsUrl();
	public void importXPath();

	public void getMiniStore();
	public void getUrl();
	public void getXPath();

}
