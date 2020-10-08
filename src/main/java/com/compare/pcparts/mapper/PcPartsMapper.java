package com.compare.pcparts.mapper;

import com.compare.pcparts.pcandparts.PcPartsItem;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PcPartsMapper
{
    int insertCpu(PcPartsItem pcPartsItem);

    List<PcPartsItem> getAllCpu();

}
