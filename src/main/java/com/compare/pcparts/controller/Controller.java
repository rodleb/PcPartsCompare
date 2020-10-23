package com.compare.pcparts.controller;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.search.SearchItem;
import com.compare.pcparts.search.SearchLog;
import com.compare.pcparts.store.ImportStoreLog;
import com.compare.pcparts.webscrape.WebscrapeLog;
import com.compare.pcparts.webscrape.WebscrapeLogImp;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
	SearchLog searchLog;
	@Autowired
	PcPartsMapper pcPartsMapper;

	@GetMapping("/webscrap")
	public void WebScrapping() throws IOException
	{
		webscrapeLog.getStore();
	}
	@GetMapping("/store/clear")
	public String updateItems(){
		try
		{
			pcPartsMapper.updateItemNames();
			pcPartsMapper.updateBrand();
		}catch(Exception e){
			log.error("Error clearing."+e);
			return "Clearing cannot be done at the moment, try later.";
		}
		return "Clearing is done";
	}

	@GetMapping("/importstore")
	public String ImportStore() throws Exception
	{

		try
		{
			storeLog.importStore();
			storeLog.importContactDetail();
			storeLog.importOperatingTime();
			storeLog.importXPath();
			storeLog.importStoreItemsUrl();


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
	public List searchResults(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "") String itemType,
			@RequestParam(defaultValue = "") String itemBrand, @RequestParam(defaultValue = "") Float minPrice,
			@RequestParam(defaultValue = "") Float maxPrice) throws UnsupportedEncodingException
	{
		log.info("Shit start from here");
		return searchLog.searchFunction(keyword, itemType, itemBrand, minPrice, maxPrice);
	}


	@GetMapping("/getItem")
	@ResponseBody
	public List getItem(@RequestParam Map<String,String> param) throws UnsupportedEncodingException
	{
		return searchLog.searchMapFunction(param);
	}



	//testing stuff here
	@PostMapping("/test")
	public String test(@RequestParam("id") String name) throws IOException
	{
		return "ID: " + name;
		//maybe return something here

	}

	//TODO: we can use this to make the page open for category item types
	@GetMapping(value = "/category/{itemType}")
	public String method7(@PathVariable("itemType") String itemType)
	{
		return "item type =" + itemType;
	}

	//TODO: we can use this to make the url go back to homepage
	@RequestMapping("*")
	@ResponseBody
	public String fallbackMethod()
	{
		return "fallback method";
	}

}
