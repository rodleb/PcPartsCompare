package com.compare.pcparts.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class SearchItem
{
	String keyword;
	String itemType;
	String itemBrand;
	Float minPrice;
	Float maxPrice;
}