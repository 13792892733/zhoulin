package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Goodsprice;
import com.cxb.project_mgj.pojo.GoodspriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodspriceMapper {
    int countByExample(GoodspriceExample example);

    int deleteByExample(GoodspriceExample example);

    int deleteByPrimaryKey(Integer gpid);

    int insert(Goodsprice record);

    int insertSelective(Goodsprice record);

    List<Goodsprice> selectByExampleWithRowbounds(GoodspriceExample example, RowBounds rowBounds);

    List<Goodsprice> selectByExample(GoodspriceExample example);

    Goodsprice selectByPrimaryKey(Integer gpid);

    int updateByExampleSelective(@Param("record") Goodsprice record, @Param("example") GoodspriceExample example);

    int updateByExample(@Param("record") Goodsprice record, @Param("example") GoodspriceExample example);

    int updateByPrimaryKeySelective(Goodsprice record);

    int updateByPrimaryKey(Goodsprice record);
}