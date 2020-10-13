package com.compare.pcparts.export;

import com.compare.pcparts.mapper.PcPartsMapper;
import com.compare.pcparts.webscrape.PcPartsItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
@Log4j2
public class PcPartsExportLogImp implements PcPartsExportLog
{
	@Autowired
	PcPartsMapper pcPartsMapper;

	@Override
	public List<PcPartsItem> getAllCpuPcAndParts() throws IOException
	{
		return pcPartsMapper.getAllCpu();
	}
}
