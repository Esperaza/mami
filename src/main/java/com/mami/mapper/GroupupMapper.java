package com.mami.mapper;

import com.mami.bean.Groupup;

public interface GroupupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Groupup record);

    int insertSelective(Groupup record);

    Groupup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groupup record);

    int updateByPrimaryKeyWithBLOBs(Groupup record);

    int updateByPrimaryKey(Groupup record);
}