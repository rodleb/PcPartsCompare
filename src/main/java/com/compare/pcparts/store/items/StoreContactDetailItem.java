package com.compare.pcparts.store.items;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StoreContactDetailItem
{
	int id;
	int store_id;
	String contact_address;
	String contact_email;
	String contact_number_1;
	String contact_number_2;
	String contact_number_3;

}
