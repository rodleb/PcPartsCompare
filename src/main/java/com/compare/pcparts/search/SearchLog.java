package com.compare.pcparts.search;

import com.compare.pcparts.webscrape.PcPartsItem;

import java.util.List;
import java.util.Map;

public interface SearchLog
{
public List<PcPartsItem> searchFunction(String keyword, String itemType, String itemBrand, float minPrice, float maxPrice);
public List<PcPartsItem> searchMapFunction(Map<String,String> allParams);
public List<PcPartsItem> searchItemType(String itemType);
}
