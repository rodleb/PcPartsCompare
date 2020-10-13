package com.compare.pcparts.webscrape;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;
@Setter
@Getter
@Component
public class PcPartsItem
{
    private int id;
    private String item_name;
    private int store_id;
    private String item_type;
    private boolean is_available;
    private String price;
    private String currency;
    private String url;
    private String image_ref= "null";
}