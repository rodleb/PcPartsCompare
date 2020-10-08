package com.compare.pcparts.pcandparts;

import java.io.IOException;
import java.util.List;

public interface PcPartsLog
{
	public void ScrappingPcParts() throws IOException;
	public List<PcPartsItem> getAllCpuPcAndParts() throws IOException;
}

