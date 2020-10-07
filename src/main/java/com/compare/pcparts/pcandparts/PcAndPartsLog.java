package com.compare.pcparts.pcandparts;

import java.io.IOException;
import java.util.List;

public interface PcAndPartsLog {
    public void ScrappingPcAndParts() throws IOException;

    public List<PcAndPartsItem> getAllCpuPcAndParts() throws IOException;

}
