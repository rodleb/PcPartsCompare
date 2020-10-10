package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class StoreOperatingTimeItem
{
	int id;
	int store_id;
	String operating_hours_weekdays;
	String operating_hours_weekend;
	String operating_days;
}
