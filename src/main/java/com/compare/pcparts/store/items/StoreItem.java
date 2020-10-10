package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class StoreItem
{
	int store_id;
	String store_name;
	String url;
	String image_ref;
	Boolean prices_including_vat;
	Float vat_percent;
}
