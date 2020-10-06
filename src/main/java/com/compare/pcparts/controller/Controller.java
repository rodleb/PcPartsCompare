package com.compare.pcparts.controller;

import com.compare.pcparts.pcandparts.PcAndPartsLogImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Controller
{
	@Autowired
	PcAndPartsLogImp pcAndPartsLog;

	@GetMapping("/webscrap")
	public void WebScrapping() throws IOException
	{
		pcAndPartsLog.ScrappingPcAndParts();
	}
}
