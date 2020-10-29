package com.compare.pcparts.controller;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.search.SearchItem;
import com.compare.pcparts.search.SearchLog;
import com.compare.pcparts.store.ImportStoreLog;
import com.compare.pcparts.webscrape.PcPartsItem;
import com.compare.pcparts.webscrape.WebscrapeLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Log4j2
@RestController
public class Controller
{
	@Autowired
	WebscrapeLog webscrapeLog;
	@Autowired
	ImportStoreLog storeLog;
	@Autowired
	SearchItem searchItem;
	@Autowired
	SearchLog searchLog;
	@Autowired
	PcPartsMapper pcPartsMapper;

	@GetMapping("/webscrap")
	public void WebScrapping() throws IOException
	{
		webscrapeLog.getStore();
	}

	@GetMapping("/brand/{itemBrand}")
	public List<PcPartsItem> searchBrand(@PathVariable("itemBrand") String itemBrand) throws IOException
	{
		return searchLog.searchBrands(itemBrand);
	}

	@GetMapping("/importstore")
	public String ImportStore() throws Exception
	{

		try
		{
			storeLog.importStore();
			storeLog.importContactDetail();
			storeLog.importOperatingTime();
			storeLog.importStoreItemsUrl();
			storeLog.importXPath();

		}
		catch(Exception e)
		{
			log.error("Error at input configuration. \nException: " + e);
			return "Store have not been imported, due to an error in configuration.";
		}

		return "Store have been Imported Successfully";
	}

	@GetMapping("/results")
	@ResponseBody
	public List getItem(@RequestParam String keyword, String itemType, String itemBrand, Float minPrice, Float maxPrice)
			throws UnsupportedEncodingException
	{
		return searchLog.searchFunction(keyword, itemType, itemBrand, minPrice, maxPrice);
	}

	//testing stuff here
	@GetMapping("/test")
	public void test() throws IOException
	{
		//maybe return something here

	}

	//TODO: we can use this to make the page open for category item types
	@GetMapping(value="/category/{itemType}")
	public List<PcPartsItem> categoryItemType(@PathVariable("itemType") String itemType){
		return searchLog.searchItemType(itemType);
	}

	//TODO: we can use this to make the url go back to homepage
	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod()
	{
		return "fallback method";
	}
	//Todo: recommended to be removed
	@GetMapping("/clean/name")
	public String  cleanName(){
		pcPartsMapper.updateItemNames();
		return "Cleaned";
	}

}
