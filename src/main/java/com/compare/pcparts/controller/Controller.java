package com.compare.pcparts.controller;

import com.compare.pcparts.search.SearchItem;
import com.compare.pcparts.search.SearchLog;
import com.compare.pcparts.store.ImportStoreLog;
import com.compare.pcparts.webscrape.WebscrapeLog;
import com.compare.pcparts.webscrape.WebscrapeLogImp;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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

	//public Controller(WebscrapeLogImp webscrapeLog){this.webscrapeLog = webscrapeLog;}

	@GetMapping("/webscrap")
	public void WebScrapping() throws IOException
	{
		webscrapeLog.getStore();
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

	@GetMapping("/getitem")
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
}
