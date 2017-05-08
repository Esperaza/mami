package com.mami.mapper;

import com.mami.bean.DangerFood;

public interface DangerFoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DangerFood record);

    int insertSelective(DangerFood record);

    DangerFood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DangerFood record);

    int updateByPrimaryKeyWithBLOBs(DangerFood record);

    int updateByPrimaryKey(DangerFood record);
}