package com.compare.pcparts.store;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.store.items.*;
import com.compare.pcparts.store.miniitem.MiniStoreItem;
import com.compare.pcparts.store.miniitem.MiniStoreUrlItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class StoreLogImp implements StoreLog
{
	@Autowired
	StoreConfiguration config;

	@Autowired
	StoreItem storeItem;

	@Autowired
	StoreOperatingTimeItem sOTT;

	@Autowired
	StoreXPathItem storeXPathItem;

	@Autowired
	StoreContactDetailItem storeContactDetailItem;

	@Autowired
	StoreUrlItem storeUrlItem;

	@Autowired
	PcPartsMapper pcPartsMapper;

	@Override
	public void importStore()
	{
		storeItem.setStore_id(config.getId());
		storeItem.setStore_name(config.getName());
		storeItem.setUrl(config.getUrl());
		storeItem.setImage_ref(config.getImageref());
		storeItem.setPrices_including_vat(config.getPricesIncludingVAT());
		storeItem.setVat_percent(config.getVatPercent());
		pcPartsMapper.insertStore(storeItem);
		log.info("Import store finished.");
	}

	@Override
	public void importOperatingTime()
	{
		sOTT.setStore_id(config.getId());
		sOTT.setOperating_hours_weekdays(
				config.getOperatingHoursOpenning() + " " + config.getOperatingHoursOpenningPeriod() + " - " + config.getOperatingHoursClosing() +
						" " + config.getOperatingHoursClosingPeriod());
		sOTT.setOperating_hours_weekend(config.getOperatingHoursWeekendOpenning() + " " + config.getOperatingHoursWeekendOpenningPeriod() + " - " +
				config.getOperatingHoursWeekendClosing() + " " + config.getOperatingHoursWeekendClosingPeriod());
		sOTT.setOperating_days(config.getOperatingDaysOpenning() + " - " + config.getOperatingDaysClosing());
		pcPartsMapper.insertStoreOperatingTime(sOTT);
		log.info("Import time finished.");
	}

	@Override
	public void importContactDetail()
	{
		storeContactDetailItem.setStore_id(config.getId());
		storeContactDetailItem.setContact_address(config.getContactAddress());
		storeContactDetailItem.setContact_email(config.getContactEmail());
		storeContactDetailItem.setContact_number_1(config.getContactNumber1());
		storeContactDetailItem.setContact_number_1(config.getContactNumber2());
		storeContactDetailItem.setContact_number_1(config.getContactNumber3());
		pcPartsMapper.insertStoreContactDetail(storeContactDetailItem);
		log.info("Import contact detail finished.");
	}

	@Override
	public void importStoreItemsUrl()
	{
		int storeId = config.getId();
		List<String> cpu_url = config.getCpuUrl();
		List<String> computer_case_url = config.getComputercaseUrl();
		List<String> cooling_fan_url = config.getCoolingfanUrl();
		List<String> memory_url = config.getMemoryUrl();
		List<String> psu_url = config.getPsuUrl();
		List<String> storage_url = config.getStorageUrl();
		List<String> graphics_card_url = config.getGraphiccardUrl();
		//TODO: add for loop

		for(String element : cpu_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPage_url(element);
			storeUrlItem.setPart_name("cpu");
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : computer_case_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("computer_case");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : cooling_fan_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("cooling_fan");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : memory_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("memory");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : psu_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("psu");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : storage_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("storage");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}

		for(String element : graphics_card_url){
			storeUrlItem.setStore_id(storeId);
			storeUrlItem.setPart_name("graphics_card");
			storeUrlItem.setPage_url(element);
			pcPartsMapper.insertStoreItemsUrl(storeUrlItem);
		}
		log.info("Import URL finished.");
	}

	@Override
	public void importXPath()
	{
		storeXPathItem.setStore_id(config.getId());
		storeXPathItem.setXpath(config.getXpath());
		storeXPathItem.setProduct_name_xpath(config.getProductNameXpath());
		storeXPathItem.setProduct_price_xpath(config.getProductPriceXpath());
		storeXPathItem.setProduct_url_xpath(config.getProductUrlXpath());
		storeXPathItem.setAlt_xpath_1(config.getProductAltXpath1());
		storeXPathItem.setAlt_xpath_2(config.getProductAltXpath2());
		storeXPathItem.setAlt_xpath_3(config.getProductAltXpath3());
		pcPartsMapper.insertStoreXPath(storeXPathItem);
		log.info("Import XPath finished.");
	}

	//TODO: I do not trust the below

	private List<MiniStoreItem> miniStoreItemList;
	protected List<Integer> storeIdList;
	private List<String> storeNameList;
	private List<MiniStoreUrlItem> miniStoreUrlItemsList;
	@Override
	public void getMiniStore()
	{
		miniStoreItemList = pcPartsMapper.getMiniStoreInfo();
		for(MiniStoreItem item : miniStoreItemList){
			storeIdList.add( item.getId());
			storeNameList.add(item.getStoreName());
			log.info("id: "+storeIdList+"\n name: "+storeNameList);
		}
	}

	private List<StoreUrlItem> storeUrlItemsList;
	@Override
	public void getUrl()
	{
		for(int id : storeIdList)
		{
			miniStoreUrlItemsList = pcPartsMapper.getStoreUrl(id);
		}
	}

	@Override
	public void getXPath()
	{

	}

}
