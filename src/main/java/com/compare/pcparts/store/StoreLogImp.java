package com.compare.pcparts.store;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreLogImp implements StoreLog
{
	@Autowired
	StoreConfiguration config;

	@Override
	public void importStore()
	{
		int storeId = config.getId();
		String store_name = config.getName();
		String url = config.getUrl();
		String image_ref = config.getImageref();
		Boolean prices_including_vat = config.getPricesIncludingVAT();
		Float vat_percent = config.getVatPercent();
	}

	@Override
	public void importOperatingTime()
	{
		int storeId = config.getId();
		String operating_hours_weekdays = config.getOperatingHoursOpenning()+" "+config.getOperatingHoursOpenningPeriod()+" - "+config.getOperatingHoursClosing()+" "+config.getOperatingHoursClosingPeriod();
		String operating_hours_weekend = config.getOperatingHoursWeekendOpenning()+" "+config.getOperatingHoursWeekendOpenningPeriod()+" - "+config.getOperatingHoursWeekendClosing()+" "+config.getOperatingHoursWeekendClosingPeriod();
		String operating_days = config.getOperatingDaysOpenning()+" - "+config.getOperatingDaysClosing();
	}

	@Override
	public void importContactDetail()
	{
		int storeId = config.getId();
		String contact_address = config.getContactAddress();
		String contact_email = config.getContactEmail();
		List contact_number = config.getContactNumber();
		//TODO: add for loop

	}

	@Override
	public void importStoreUrl()
	{
		int storeId = config.getId();
		List cpu_url = config.getCpuUrl();
		List computer_case_url = config.getComputercaseUrl();
		List cooling_fan_url = config.getCoolingfanUrl();
		List memory_url = config.getMemoryUrl();
		List psu_url = config.getPsuUrl();
		List storage_url = config.getStorageUrl();
		List graphics_card_url = config.getGraphiccardUrl();
		//TODO: add for loop

	}

	@Override
	public void importXPath()
	{
		int storeId = config.getId();
		String xpath = config.getXpath();
		String product_name_xpath = config.getProductNameXpath();
		String product_price_xpath = config.getProductPriceXpath();
		String product_url_xpath = config.getProductUrlXpath();

	}
}
