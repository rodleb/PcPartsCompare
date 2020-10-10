package com.compare.pcparts.controller;

import com.compare.pcparts.store.StoreConfiguration;
import com.compare.pcparts.pcandparts.PcPartsItem;
import com.compare.pcparts.pcandparts.PcPartsLogImp;
import com.compare.pcparts.store.StoreLogImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
@Log4j2
@RestController
public class Controller {
    @Autowired
    PcPartsLogImp pcPartsLogImp;
    @Autowired
    StoreConfiguration config;
    @Autowired
    StoreLogImp storeLogImp;

    public Controller(PcPartsLogImp pcPartsLogImp){this.pcPartsLogImp = pcPartsLogImp;}
    @GetMapping("/webscrap")
    public void WebScrapping() throws IOException
    {
         pcPartsLogImp.ScrappingPcParts();
    }
    @GetMapping("/importstore")
    public  String ImportStore() throws Exception
    {
        try
        {
            storeLogImp.importStore();
            storeLogImp.importContactDetail();
            storeLogImp.importOperatingTime();
            storeLogImp.importStoreItemsUrl();
            storeLogImp.importXPath();

        }catch(Exception e){
            log.error("Error at input configuration. \nException: "+e);
            return "Store have not been imported, due to an error in configuration.";
        }

        return "Store have been Imported Successfully";
    }

    @GetMapping("/allcpu")
    public List<PcPartsItem> AllCpu() throws IOException
    {
        return pcPartsLogImp.getAllCpuPcAndParts();

    }
    @GetMapping("/test")
    public void test()
    {
        storeLogImp.getMiniStore();
    }
}
