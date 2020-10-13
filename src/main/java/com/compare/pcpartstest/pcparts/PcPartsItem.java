package com.compare.pcpartstest.pcparts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Getter
@Setter
@Component
public class PcPartsItem
{
    private int id;
    private String item_name;
    private String price;
    private String url;
}
