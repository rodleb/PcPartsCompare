package com.compare.pcparts.controller;

import com.compare.pcparts.export.PcPartsExportLog;
import com.compare.pcparts.store.ImportStoreLog;
import com.compare.pcparts.store.StoreConfiguration;
import com.compare.pcparts.webscrape.WebscrapeLog;
import com.compare.pcparts.webscrape.WebscrapeLogImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RestController
public class Controller {
    @Autowired
    WebscrapeLog webscrapeLog;
    @Autowired
    StoreConfiguration config;
    @Autowired
    ImportStoreLog storeLogImp;
    @Autowired
    PcPartsExportLog pcPartsExportLog;

    public Controller(WebscrapeLogImp webscrapeLog){this.webscrapeLog = webscrapeLog;}
    @GetMapping("/webscrap")
    public void WebScrapping() throws IOException
    {
        webscrapeLog.getStore();
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

    //testing stuff here
    @GetMapping("/test")
    public void test()  throws IOException
    {
        //maybe return something here

    }
}
