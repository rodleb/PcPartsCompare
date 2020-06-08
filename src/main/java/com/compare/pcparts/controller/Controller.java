package com.compare.pcparts.controller;

import com.compare.pcparts.mapper.PcAndPartsMapper;
import com.compare.pcparts.pcandparts.PcAndPartsItem;
import com.compare.pcparts.pcandparts.PcAndPartsLogImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    PcAndPartsLogImp pcAndPartsLogImp;
    public Controller(PcAndPartsLogImp pcAndPartsLogImp){this.pcAndPartsLogImp = pcAndPartsLogImp;}
    @GetMapping("/webscrap")
    public String WebScrapping() throws IOException
    {
        return pcAndPartsLogImp.ScrappingPcAndParts();

    }

    @GetMapping("/allcpu")
    public List<PcAndPartsItem> allCpu() throws IOException
    {
        return pcAndPartsLogImp.getAllCpuPcAndParts();

    }
}
