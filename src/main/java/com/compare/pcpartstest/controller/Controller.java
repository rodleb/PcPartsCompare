package com.compare.pcpartstest.controller;

import com.compare.pcpartstest.pcparts.PcPartsItem;
import com.compare.pcpartstest.pcparts.PcPartsLogImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    PcPartsLogImp pcPartsLogImp;
    public Controller(PcPartsLogImp pcPartsLogImp){this.pcPartsLogImp = pcPartsLogImp;}
    @GetMapping("/webscrap")
    public String WebScrapping() throws IOException
    {
        return pcPartsLogImp.ScrappingPcAndParts();

    }

    @GetMapping("/allitems")
    public List<PcPartsItem> allCpu() throws IOException
    {
        return pcPartsLogImp.getAllItemsDB();

    }
}
