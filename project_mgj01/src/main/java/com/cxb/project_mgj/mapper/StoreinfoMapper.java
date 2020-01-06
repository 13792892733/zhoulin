package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Storeinfo;
import com.cxb.project_mgj.pojo.StoreinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StoreinfoMapper {
    int countByExample(StoreinfoExample example);

    int deleteByExample(StoreinfoExample example);

    int deleteByPrimaryKey(Integer stid);

    int insert(Storeinfo record);

    int insertSelective(Storeinfo record);

    List<Storeinfo> selectByExampleWithRowbounds(StoreinfoExample example, RowBounds rowBounds);

    List<Storeinfo> selectByExample(StoreinfoExample example);

    Storeinfo selectByPrimaryKey(Integer stid);

    int updateByExampleSelective(@Param("record") Storeinfo record, @Param("example") StoreinfoExample example);

    int updateByExample(@Param("record") Storeinfo record, @Param("example") StoreinfoExample example);

    int updateByPrimaryKeySelective(Storeinfo record);

    int updateByPrimaryKey(Storeinfo record);
}