package com.shop.service;

import java.util.List;

import com.shop.model.BrandVO;
import com.shop.model.Criteria;

public interface BrandService {
	
	public void brandEnroll(BrandVO brand) throws Exception;

	public List<BrandVO> brandGetList(Criteria cri) throws Exception;
	
	public int brandGetTotal(Criteria cri) throws Exception; 
	
	public BrandVO brandGetDetail(int brandId) throws Exception;
	
	public int brandModify(BrandVO brand) throws Exception;
	
	public int brandDelete(int brandId);
}
