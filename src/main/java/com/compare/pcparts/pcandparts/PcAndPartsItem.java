package com.compare.pcparts.pcandparts;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PcAndPartsItem
{
    private int id;
    private String itemName;
    private String price;
    private String url;
    private int storeId;
    private boolean isAvailable = true;
    private String imageRef= "null";
}
