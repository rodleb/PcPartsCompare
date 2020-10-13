package com.compare.pcpartstest.mapper;

import com.compare.pcpartstest.pcparts.PcPartsItem;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PcPartsMapper
{

    int insertItem(PcPartsItem pcPartsItem);

    List<PcPartsItem> getAllItems();

}
