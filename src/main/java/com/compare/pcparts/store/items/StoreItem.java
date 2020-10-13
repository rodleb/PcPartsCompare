package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class StoreItem
{
	int id;
	String store_name;
	String url;
	String image_ref;
	String currency;
	Boolean prices_including_vat;
	Float vat_percent;
}