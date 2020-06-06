package com.compare.pcparts.pcandparts;

import java.io.IOException;
import java.util.List;

public interface PcAndPartsLog {
    public String ScrappingPcAndParts() throws IOException;

    public List<PcAndPartsItem> getAllCpuPcAndParts() throws IOException;

}
