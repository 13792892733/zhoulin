package com.cxb.project_mgj.mapper;

import com.cxb.project_mgj.pojo.Cart;
import com.cxb.project_mgj.pojo.CartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CartMapper {
    int countByExample(CartExample example);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer ctid);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExampleWithRowbounds(CartExample example, RowBounds rowBounds);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer ctid);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}