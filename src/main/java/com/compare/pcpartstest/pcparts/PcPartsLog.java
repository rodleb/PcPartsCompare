package com.compare.pcpartstest.pcparts;

import java.io.IOException;
import java.util.List;

public interface PcPartsLog
{
    public String ScrappingPcAndParts() throws IOException;

    public List<PcPartsItem> getAllItemsDB() throws IOException;

}
