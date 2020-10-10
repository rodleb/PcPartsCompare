package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class StoreXPathItem
{
	int id;
	int store_id;
	String xpath;
	String product_name_xpath;
	String product_price_xpath;
	String product_url_xpath;
	String alt_xpath_1;
	String alt_xpath_2;
	String alt_xpath_3;
}
