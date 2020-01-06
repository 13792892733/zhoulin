package com.cxb.project_mgj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.cxb.project_mgj.pojo.Goodsimage;

public interface MyGoods {
	
	List<Map> getAll(RowBounds rowBound);
	
	List<String> getByLike(String like);
	
	Map getAllByPrice(Integer gdid);
}
