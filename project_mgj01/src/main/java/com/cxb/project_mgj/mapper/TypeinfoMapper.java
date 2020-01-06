package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Typeinfo;
import com.cxb.project_mgj.pojo.TypeinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TypeinfoMapper {
    int countByExample(TypeinfoExample example);

    int deleteByExample(TypeinfoExample example);

    int deleteByPrimaryKey(Integer tpid);

    int insert(Typeinfo record);

    int insertSelective(Typeinfo record);

    List<Typeinfo> selectByExampleWithRowbounds(TypeinfoExample example, RowBounds rowBounds);

    List<Typeinfo> selectByExample(TypeinfoExample example);

    Typeinfo selectByPrimaryKey(Integer tpid);

    int updateByExampleSelective(@Param("record") Typeinfo record, @Param("example") TypeinfoExample example);

    int updateByExample(@Param("record") Typeinfo record, @Param("example") TypeinfoExample example);

    int updateByPrimaryKeySelective(Typeinfo record);

    int updateByPrimaryKey(Typeinfo record);
}