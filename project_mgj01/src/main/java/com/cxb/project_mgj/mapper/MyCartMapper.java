package com.cxb.project_mgj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface MyCartMapper {

	public List<Map> getCartInfoesByUserID(Integer userid);

}


