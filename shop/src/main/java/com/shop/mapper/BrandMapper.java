package com.shop.mapper;

import java.util.List;

import com.shop.model.BrandVO;
import com.shop.model.Criteria;

public interface BrandMapper {
	public void brandEnroll(BrandVO brand);
	
	public List<BrandVO> brandGetList(Criteria cri);
	
	public int brandGetTotal(Criteria cri);
	
	public BrandVO brandGetDetail(int brandId);
	
	public int brandModify(BrandVO brand);
	
	public int brandDelete(int brandId);	
}
