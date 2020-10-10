package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@Component

public class StoreUrlItem
{
	int id;
	int store_id;
	String part_name;
	String page_url;
}
