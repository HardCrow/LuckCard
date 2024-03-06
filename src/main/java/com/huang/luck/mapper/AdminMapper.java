package com.huang.luck.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    String AdminGoodsNameCheck(@Param("goodsname") String GoodsNme);
}
