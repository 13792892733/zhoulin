package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Goodsimage;
import com.cxb.project_mgj.pojo.GoodsimageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsimageMapper {
    int countByExample(GoodsimageExample example);

    int deleteByExample(GoodsimageExample example);

    int deleteByPrimaryKey(Integer gimgid);

    int insert(Goodsimage record);

    int insertSelective(Goodsimage record);

    List<Goodsimage> selectByExampleWithRowbounds(GoodsimageExample example, RowBounds rowBounds);

    List<Goodsimage> selectByExample(GoodsimageExample example);

    Goodsimage selectByPrimaryKey(Integer gimgid);

    int updateByExampleSelective(@Param("record") Goodsimage record, @Param("example") GoodsimageExample example);

    int updateByExample(@Param("record") Goodsimage record, @Param("example") GoodsimageExample example);

    int updateByPrimaryKeySelective(Goodsimage record);

    int updateByPrimaryKey(Goodsimage record);
}