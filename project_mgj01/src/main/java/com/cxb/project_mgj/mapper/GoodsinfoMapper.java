package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Goodsinfo;
import com.cxb.project_mgj.pojo.GoodsinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsinfoMapper {
    int countByExample(GoodsinfoExample example);

    int deleteByExample(GoodsinfoExample example);

    int deleteByPrimaryKey(Integer gdid);

    int insert(Goodsinfo record);

    int insertSelective(Goodsinfo record);

    List<Goodsinfo> selectByExampleWithRowbounds(GoodsinfoExample example, RowBounds rowBounds);

    List<Goodsinfo> selectByExample(GoodsinfoExample example);

    Goodsinfo selectByPrimaryKey(Integer gdid);

    int updateByExampleSelective(@Param("record") Goodsinfo record, @Param("example") GoodsinfoExample example);

    int updateByExample(@Param("record") Goodsinfo record, @Param("example") GoodsinfoExample example);

    int updateByPrimaryKeySelective(Goodsinfo record);

    int updateByPrimaryKey(Goodsinfo record);
}