package com.compare.pcparts.export;

import com.compare.pcparts.webscrape.PcPartsItem;

import java.io.IOException;
import java.util.List;

public interface PcPartsExportLog
{
	public List<PcPartsItem> getAllCpuPcAndParts() throws IOException;

}
