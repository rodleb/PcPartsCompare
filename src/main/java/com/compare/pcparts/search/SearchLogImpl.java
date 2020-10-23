package com.compare.pcparts.search;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.webscrape.PcPartsItem;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Log4j2
@Component
public class SearchLogImpl implements SearchLog
{
	@Autowired
	SearchItem searchItem;
	@Autowired
	PcPartsMapper pcPartsMapper;

	@Override
	public List<PcPartsItem> searchFunction(String keyword, String itemType, String itemBrand, float minPrice, float maxPrice)
	{
		log.info("search func");
		searchItem.setKeyword(keyword);
		searchItem.setItemType(itemType);
		//searchItem.setItemBrand(itemBrand);
		//searchItem.setMinPrice(minPrice);
		//searchItem.setMaxPrice(maxPrice);
		return pcPartsMapper.searchItemDB(searchItem);
	}

	@Override
	public List<PcPartsItem> searchMapFunction(Map<String, String> allParams)
	{

		String keyword = "keyword";
		String itemType = "itemType";
		String minPrice = "minPrice";
		String itemBrand = "itemBrand";
		String maxPrice = "maxPrice";

		searchItem.setKeyword(null);
		searchItem.setItemType(null);
		searchItem.setItemBrand(null);
		searchItem.setMaxPrice(null);
		searchItem.setMinPrice(null);

		for(val e : allParams.entrySet())
		{

			String key = (String) e.getKey();
			String value = (String) e.getValue();

			if(key.equals(keyword))
			{
				log.info("value is: " + value);
				if(value != null) //&& value != "" && !value.isEmpty() && !value.equals(null))
				{
					searchItem.setKeyword(value);
					log.info(keyword + ": " + searchItem.getKeyword() + " : " + value);
					log.info("Keyword bbbbbbrrrrrrrrruuu: " + searchItem.getKeyword());
				}
				else if(value == null || value.isEmpty() || value.equals(null))
				{
					String space = " ";
					searchItem.setKeyword(space);
					log.info("entering empty space here............");
				}
			}
			else if(key.equals(itemType) && value != null)
			{
				searchItem.setItemType(value);
				log.info(itemType + ": " + searchItem.getItemType());

			}
			else if(key.equals(itemBrand) && value != null)
			{
				searchItem.setItemBrand(value);
				log.info(itemBrand + ": " + searchItem.getItemBrand());
			}
			else if(key.equals(minPrice) && value != null)
			{
				Float minPriceFloat = Float.parseFloat(value);
				searchItem.setMinPrice(minPriceFloat);
				log.info(minPrice + ": " + searchItem.getMaxPrice());
			}
			else if(key.equals(maxPrice) && value != null)
			{
				Float maxPriceFloat = Float.parseFloat(value);
				searchItem.setMaxPrice(maxPriceFloat);
				log.info(maxPrice + ": " + searchItem.getMaxPrice());
			}

		}
		log.info("Keyword: "+searchItem.getKeyword()+" ItemType: "+searchItem.getItemType());
		return pcPartsMapper.searchItemDB(searchItem);
	}
}
